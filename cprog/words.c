#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

#define SIZE (100)

typedef struct word_t word_t;

struct word_t {
	size_t count;
	char* a;
	word_t* next;
};

word_t* new_word(char* a)
{
	word_t* p;
	p = malloc(sizeof(word_t)); // check if NULL
	p->count = 1;
	p->a = a;
	p->next = NULL;
	return p;
}

void insert(word_t** head, char* a)
{
	word_t* p  = *head;

	if(p == NULL) {
		printf("head tilldelas: %s\n", a);
		*head = new_word(a);
		printf("HEADD!: %s\n",(*head)->a);
		return;
	}

	printf("head: %s\n", (*head)->a);

	while(p->next != NULL) {
		printf("inne i loop\n");
		printf("p->a:%s\n", p->a);
		if(strcmp(a, p->a) == 0) {
			printf("loop, samma!\n");
			p->count = p->count + 1;
			return;
		} 
		p = p->next;
		printf("loop\n");
	}

	printf("lägger till nytt element\n");
	p->next = new_word(a);
	//printf("%s\n", (*head)->next->a);
}

void print(word_t* head)
{
	word_t* p = head;

	if(p == NULL)
		return;

	do {
		printf("%zu \t %s\n", p->count, p->a);
		p = p->next;
	} while(p != NULL);
}

void free_list(word_t* head)
{
	word_t* p = head;
	word_t* q;

	while(p != NULL) {
		q = p->next;
		free(p->a);
		free(p);
		p = q;
	}
}

int main(void)
{
	word_t* head = NULL;
	int input = 0;
	size_t i = 0;

	while(input != '\n' ) {
		char* t;
		char* s;
		t = s = malloc(SIZE); // check if NULL
		input = getchar();
		i = 0; 

		while(input != ' ' && input != '\n') {
			s[i] = input;
			input = getchar();
			i++;
		}
		printf("strängen: %s\n", s);

		insert(&head, s);

		s = t;
		
	}
	

	print(head);
	free(head);
}