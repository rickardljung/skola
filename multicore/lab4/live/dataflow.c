#include <stdbool.h>
#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include <inttypes.h>
#include <pthread.h>
#include "dataflow.h"
#include "error.h"
#include "list.h"
#include "set.h"

typedef struct vertex_t	vertex_t;
typedef struct task_t	task_t;
typedef struct thread_data thread_data;

/* cfg_t: a control flow graph. */
struct cfg_t {
	size_t			nvertex;	/* number of vertices		*/
	size_t			nsymbol;	/* width of bitvectors		*/
	vertex_t*		vertex;		/* array of vertex		*/
};

/* vertex_t: a control flow graph vertex. */
struct vertex_t {
	size_t			index;		/* can be used for debugging	*/
	set_t*			set[NSETS];	/* live in from this vertex	*/
	set_t*			prev;		/* alternating with set[IN]	*/
	size_t			nsucc;		/* number of successor vertices */
	vertex_t**		succ;		/* successor vertices 		*/
	list_t*			pred;		/* predecessor vertices		*/
	bool			listed;		/* on worklist			*/
	pthread_mutex_t vertex_mux;
};

//struct thread_data {
	//list_t*			worklist;
//};

list_t* worklist;

pthread_mutex_t inout_mutex = PTHREAD_MUTEX_INITIALIZER;

static void clean_vertex(vertex_t* v);
static void init_vertex(vertex_t* v, size_t index, size_t nsymbol, size_t max_succ);

cfg_t* new_cfg(size_t nvertex, size_t nsymbol, size_t max_succ)
{
	size_t		i;
	cfg_t*		cfg;

	cfg = calloc(1, sizeof(cfg_t));
	if (cfg == NULL)
		error("out of memory");

	cfg->nvertex = nvertex;
	cfg->nsymbol = nsymbol;

	cfg->vertex = calloc(nvertex, sizeof(vertex_t));
	if (cfg->vertex == NULL)
		error("out of memory");

	for (i = 0; i < nvertex; i += 1)
		init_vertex(&cfg->vertex[i], i, nsymbol, max_succ);

	return cfg;
}

static void clean_vertex(vertex_t* v)
{
	int		i;

	for (i = 0; i < NSETS; i += 1)
		free_set(v->set[i]);
	free_set(v->prev);
	free(v->succ);
	free_list(&v->pred);
}

static void init_vertex(vertex_t* v, size_t index, size_t nsymbol, size_t max_succ)
{
	int		i;

	v->index	= index;
	v->succ		= calloc(max_succ, sizeof(vertex_t*));

	if (v->succ == NULL)
		error("out of memory");
	
	for (i = 0; i < NSETS; i += 1)
		v->set[i] = new_set(nsymbol);

	v->prev = new_set(nsymbol);
}

void free_cfg(cfg_t* cfg)
{
	size_t		i;

	for (i = 0; i < cfg->nvertex; i += 1)
		clean_vertex(&cfg->vertex[i]);
	free(cfg->vertex);
	free(cfg);
}

void connect(cfg_t* cfg, size_t pred, size_t succ)
{
	vertex_t*	u;
	vertex_t*	v;

	u = &cfg->vertex[pred];
	v = &cfg->vertex[succ];

	u->succ[u->nsucc++ ] = v;
	insert_last(&v->pred, u);
}

bool testbit(cfg_t* cfg, size_t v, set_type_t type, size_t index)
{
	return test(cfg->vertex[v].set[type], index);
}

void setbit(cfg_t* cfg, size_t v, set_type_t type, size_t index)
{
	set(cfg->vertex[v].set[type], index);
}

vertex_t* mux_remove_first(pthread_mutex_t* mutex)
{
	vertex_t* u;
	//printf("väntar på remove_mutex\n");
	pthread_mutex_lock(mutex);

	// if (worklist != NULL) {
	// 	vertex_t* temp = worklist->data;
	// 	//printf("tar låset för: %zu \n", temp->index);
	// 	pthread_mutex_lock(&(temp->vertex_mux));
	// 	u = remove_first(&worklist);
	// 	//thread_mutex_unlock(&(temp->vertex_mux));
	// 	pthread_mutex_unlock(mutex);
	// 	return u;
	// } else {
	// 	pthread_mutex_unlock(mutex);
	// 	return NULL;
	// }
	u = remove_first(&worklist);
	pthread_mutex_unlock(mutex);
	return u;
}

void mux_insert_last(vertex_t* v, pthread_mutex_t* mutex)
{
	pthread_mutex_lock(mutex);
	insert_last(&worklist, v);
	pthread_mutex_unlock(mutex);
}

void* thread_func(void* data)
{

	vertex_t*	u;
	vertex_t*	v;
	//vertex_t* 	v_temp;
	set_t*		prev;
	size_t		j;
	//list_t*	worklist;
	list_t*		p;
	list_t*		h;

	while ((u = mux_remove_first(&inout_mutex)) != NULL) {
		pthread_mutex_lock(&(u->vertex_mux));
		size_t taken = u->index;
		//printf("längd på worklist: %zu\n", length(worklist));
		//pthread_mutex_lock(&(u->vertex_mux));
		u->listed = false;

		reset(u->set[OUT]);
		for (j = 0; j < u->nsucc; ++j) {
			if (taken == u->index) {
				or(u->set[OUT], u->set[OUT], u->succ[j]->set[IN]);
			} else {
				pthread_mutex_lock(&(u->succ[j]->vertex_mux));
				or(u->set[OUT], u->set[OUT], u->succ[j]->set[IN]);
				pthread_mutex_unlock(&(u->succ[j]->vertex_mux));
			}
		}

		prev = u->prev;
		u->prev = u->set[IN];
		u->set[IN] = prev;

		/* in our case liveness information... */
		propagate(u->set[IN], u->set[OUT], u->set[DEF], u->set[USE]);

		if (u->pred != NULL && !equal(u->prev, u->set[IN])) {
			p = h = u->pred;
			pthread_mutex_unlock(&(u->vertex_mux));
			do {
				v = p->data;			
				pthread_mutex_lock(&(v->vertex_mux));
				if (!v->listed) {
					v->listed = true;
					mux_insert_last(v, &inout_mutex);
				}
				p = p->succ;
				pthread_mutex_unlock(&(v->vertex_mux));
			} while (p != h);
		} else {
			pthread_mutex_unlock(&(u->vertex_mux)); 
		}
	}
	//printf("Klar i thread_func\n");
	return NULL;
}

void liveness(cfg_t* cfg)
{
	vertex_t*	u;
	size_t		i;
	//list_t*		worklist;

	worklist = NULL;
	
	for (i = 0; i < cfg->nvertex; ++i) {
		u = &cfg->vertex[i];
		u->vertex_mux = (pthread_mutex_t)PTHREAD_MUTEX_INITIALIZER;
		insert_last(&worklist, u);
		u->listed = true;
	}	

	pthread_t threads[4];
	for (i = 0; i < 4; i++) {
		pthread_t thread;
		if (!pthread_create(&thread, NULL, thread_func, NULL)) {
			threads[i] = thread;
		} else {
			printf("could not create thread nr: %zu\n", i);
			exit(1);
		}
	}
	//printf("trådar startade\n");
	for (i = 0; i < 4; i++) {
		if (pthread_join(threads[i], NULL)) {
			printf("Failed to join thrad nr: %zu\n",i);
			exit(1);
		}
	}
	//free(data);
}

void print_sets(cfg_t* cfg, FILE *fp)
{
	size_t		i;
	vertex_t*	u;

	for (i = 0; i < cfg->nvertex; ++i) {
		u = &cfg->vertex[i]; 
		fprintf(fp, "use[%zu] = ", u->index);
		print_set(u->set[USE], fp);
		fprintf(fp, "def[%zu] = ", u->index);
		print_set(u->set[DEF], fp);
		fputc('\n', fp);
		fprintf(fp, "in[%zu] = ", u->index);
		print_set(u->set[IN], fp);
		fprintf(fp, "out[%zu] = ", u->index);
		print_set(u->set[OUT], fp);
		fputc('\n', fp);
	}
}