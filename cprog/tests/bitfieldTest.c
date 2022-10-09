#include <stdio.h>
#include <stdlib.h>

typedef struct {
	unsigned int a : 2;
} korv;

int main(void)
{
	
	korv k;

	korv* ko;
	ko = malloc(sizeof(korv));

	ko->a = 2;

	k.a = 3;

	printf("%d\n", k.a);
	printf("%d\n", ko->a);
}