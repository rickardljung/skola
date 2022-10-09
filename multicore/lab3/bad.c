#include <assert.h>
#include <limits.h>
#include <pthread.h>
#include <stddef.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/times.h>
#include <sys/time.h>
#include <unistd.h>
#include <string.h>

static int cmp(const void* ap, const void* bp);

struct sort_struct {
	double* a;
	int n;
	int		(*cmp)(const void*, const void*); // Behaves like strcmp
};

int started_threads;
pthread_t* threads;

static double sec(void)
{
	struct timeval	tv;
	gettimeofday(&tv, NULL);
	
	return tv.tv_sec + 1e-6 * tv.tv_usec;
}

void* sort_func(void* data) {
	struct sort_struct* temp= (struct sort_struct*)data;
	qsort(temp->a, temp->n, sizeof(temp->a[0]), temp->cmp);
	printf("SORTED!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
	return NULL;
}

void par_sort(
	double*		base,	// Array to sort.
	size_t		n,	// Number of elements in base.
	size_t		s,	// Size of each element.
	int		(*cmp)(const void*, const void*)) // Behaves like strcmp
{
	
	double* tmp_base = base;
	pthread_t sort_thread;
	
	static int nbr_splits = -1;
	nbr_splits++;

	if (nbr_splits >= 2) {
	
		if (n > 10) {	
			struct sort_struct sort_data;
			sort_data.a = base;
			sort_data.n = n;
			sort_data.cmp = cmp;
			if (pthread_create(&sort_thread, NULL, sort_func, &sort_data)) {
				printf("Could not create thread\n");
				exit(1);
			}
			threads[started_threads] = sort_thread;
			started_threads++;
		} else {
			qsort(base, n, s, cmp);			
		}		

		if (nbr_splits > 2) { 
			nbr_splits = 0;
		}
	} else { 
		
		double pivot = tmp_base[0];
		double* base_sort = malloc(n * sizeof(double)); 
		
		int n1 = 0;
		int n2 = n - 1;
		int i;
		for (i = 1; i < n; i++) {
			if (tmp_base[i] <= pivot) {
				base_sort[n1] = tmp_base[i];
				n1++;
			}
			else {
				base_sort[n2] = tmp_base[i];
				n2--;
			}
		}
		base_sort[n1] = pivot;		
		n1++;

		memcpy(tmp_base, base_sort, s * n);
	
		printf("\npivot: %lf\n", pivot);
	
		printf("LOW, sorting index 0 to %d\n", n1);	
		for (i = 0; i < n1; i++)
			printf("%lf\n", tmp_base[i]);
		par_sort((void*)tmp_base, n1, sizeof(double), cmp);
		
		printf("HIGH, sorting index %d to %zu\n", n1, n);	
		for (i = n1; i < n; i++)
			printf("%lf\n", tmp_base[i]);
		par_sort((void*)(tmp_base + n1), n - n1, sizeof(double), cmp);
		free(base_sort);
	}
}

static int cmp(const void* ap, const void* bp)
{	
	/* you need to modify this function to compare doubles. */
	double a = *(double*) ap;
	double b = *(double*) bp;

	double diff = a - b;
	if (diff > 0)
		return 1;
	else if (diff < 0)
		return -1;
	else
		return 0;
}

int main(int ac, char** av)
{
	int		n = 1000;
	int		i;
	double*		a;
	double		start, end;

	if (ac > 1)
		sscanf(av[1], "%d", &n);

	srand(getpid());

	a = malloc(n * sizeof a[0]);
	for (i = 0; i < n; i++)
		a[i] = rand();

	start = sec();

#ifdef PARALLEL
	threads = malloc(4 * sizeof(pthread_t));
	par_sort(a, n, sizeof(double), cmp);
	sleep(2);
#else
	qsort(a, n, sizeof(double), cmp);
#endif
	end = sec();
	
	printf("\nDONE:\n");
	for (i = 0; i < n; i++) {
		printf("%lf\n", a[i]);
	}
	printf("%1.2f s\n", end - start);

	free(a);

	return 0;
}
