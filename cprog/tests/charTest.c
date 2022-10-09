#include <stdio.h>

enum {A, B, C, D = 100, E, F};

int main()
{
	char c = 'A';
	char* a = &c;
	signed char* b = a;

	printf("%c\n", *b);

	unsigned char ch = -8 & 127;

	printf("%d\n", ch);


	
}