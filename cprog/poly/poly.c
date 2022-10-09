//Rickard Johansson 920918-2279
//polynomial

#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "error.h"
#include "poly.h"

struct poly_t {
	poly_t* 	next;
	int 		deg;
	int 		coef;
};

static poly_t* new_term(int deg, int coef)
{
	poly_t* term;
	term = malloc(sizeof(poly_t));
	if(term == NULL) {
		fprintf(stderr, "could not allocate memory");
		exit(1);
	}
	term->next = NULL;
	term->deg = deg;
	term->coef = coef;
	return term;
}
static void insert(poly_t** head, int deg, int coef) 
{
	poly_t* p = *head;
	poly_t* q;

	if(p == NULL) {
		(*head) = malloc(sizeof(poly_t));
		(*head)->next = NULL;
		(*head)->deg = deg;
		(*head)->coef = coef;
		return;
	}
	if(p->deg < deg) {
		*head = new_term(deg, coef);
		(*head)->next = p;
		return;
	}	
	do {
		if(p->deg == deg) {
			p->coef = p->coef + coef;
			break;
		}
		if(p->next == NULL){
			p->next = new_term(deg, coef);
			break;	
		}		
		if(p->next->deg < deg) {
			q = new_term(deg, coef);
			q->next = p->next;
			p->next = q;
			break;
		}		
		
		p = p->next;
	} while(p != NULL);		
}

poly_t* new_poly_from_string(const char* poly)
{	
	poly_t* head = NULL;
	char input;
	int sign = 1;
	int deg = 1;
	int coef = 1;
	int i = 0;
	int j = 0;
	
	while(poly[i] != '\0') {
		input = poly[i];

		if(isdigit(input)) { 
			coef = input -	'0';
			j = i + 1;
			while(poly[j] != ' ' && isdigit(poly[j])) {
				coef = coef * 10;
				coef = coef + (poly[j] - '0');
				j = j + 1;
			}
			i = j;	
			if (poly[i] == '\0')
				insert(&head, 0, coef*sign);			
		} else {
			switch(input) {
				case 'x':
					if(poly[i + 1] == '^') {	
						deg = poly[i + 2] - '0';
						j = i + 3;
						while(poly[j] != ' ' && poly[j] != '\0') {
							deg = deg * 10;
							deg = deg + (poly[j] - '0');
							j = j + 1;
						}
						i = j;
					}
					insert(&head, deg, coef*sign);
					if(poly[i] == '\0')
						return head;
					sign = 1;
					coef = 1;
					deg = 1;
					break;
				case '+':
					sign = 1;
					break;
				case '-':
					sign = -1;
					break;				
				case ' ':
					break;
				default:
					fprintf(stderr, "invalid input");
					exit(1);
			}
			i = i + 1;		
		}
		
	}
	return head;
}

void free_poly(poly_t *head) 
{
	poly_t* p;
	poly_t* q;

	p = head->next;
	free(head);

	while(p != NULL) {
		q = p->next;
		free(p);
		p = q;
	}
}

poly_t* mul(poly_t* poly_a, poly_t* poly_b)
{
	poly_t* r = NULL;
	poly_t* tmp;

	while(poly_a != NULL) {
		tmp = poly_b;
		while(tmp != NULL) {
			insert(&r, poly_a->deg + tmp->deg, (poly_a->coef)*(tmp->coef));
			tmp = tmp->next;
		}
		poly_a = poly_a->next;		
	}
	return r;

}

void print_poly(poly_t* head)
{
	poly_t* p = head;
	int sign = 1;
	if(p == NULL)
		return; 		
	do {
		if(p->coef != 0) {
			if(p != head) {
				if(p->coef > 0) {
					printf(" + ");
					sign = 1;
				}
				else {
					printf(" - ");
					sign = -1;
				}
			}			
			if((p->coef != 1 && p->coef != -1) || p->deg == 0) {
				printf("%d", p->coef*sign);
				if(p->next != NULL)
					printf(" ");			
			}
			if(p->coef == -1 && p == head)
				printf("-");				
			if(p->deg == 1)
				printf("x");
			if(p->deg > 1)
				printf("x^%d", p->deg);
			
		}
		p = p->next;
	}while(p != NULL);
	printf("\n");
}	
