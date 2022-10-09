#include <stdlib.h>
#include <stdio.h>
#include "list.h"

list_t* new_list(char* string)
{
	list_t*		p;

	p = malloc(sizeof(list_t));

	if (p == NULL) {
		fprintf(stderr, "out of memory");
		abort();
	}

	p->succ = p->pred = p;
	p->string = string;

	return p;
}

void insert_first(list_t** head, list_t* p)
{
	list_t*		h = *head;

	if (h == NULL) {
		*head = p;
		return;
	}

	p->succ = h;
	p->pred = h->pred;
	h->pred->succ = p;
	h->pred = p;

	*head = p;
}

void insert_last(list_t** head, list_t* p)
{
	list_t*		h = *head;

	if (h == NULL) {
		*head = p;
		return;
	}

	p->succ = h;
	p->pred = h->pred;
	h->pred->succ = p;
	h->pred = p;
}

void reverse(list_t** head)
{
	list_t*		h = *head;
	list_t*		p;
	list_t*		q;
	list_t*		r;

	if (h == NULL)
		return;

	p = h->succ;

	while(p != (*head)) {
		q = p->succ;
		p->succ = p->pred;
		p->pred = q;
		p = q;
	}

	*head = p;
}

void free_list(list_t** head)
{
	list_t*		h = *head;
	list_t*		p;
	list_t*		q;

	if (h == NULL)
		return;

	p = h;	
	p->pred->succ = NULL;

	while (p != NULL) {
		q = p->succ;
		free(p);
		p = q;
	}

	*head = NULL;
}

void print(list_t* h)
{
	list_t*		p;

	if (h == NULL)
		return;

	p = h;
	do {
		printf("%s ", p->string);
		p = p->succ;
	} while (p != h);

	putchar('\n');
}

int main(void)
{
	list_t* head = new_list("Hej");
	insert_last(&head, new_list("på"));
	insert_last(&head, new_list("dig"));
	insert_last(&head, new_list("din"));
	insert_last(&head, new_list("lille"));
	insert_last(&head, new_list("korv"));

	print(head);

	reverse(&head);

	print(head);

}
