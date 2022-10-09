#include <stdio.h>

int main()
{
	int x = 12;
	int* p = &x;

	*p = 13;

	printf("%d\n", x);

	
}