#include <stdio.h>

void f(int* a)
{
	*a = 12;
}

int g(void)
{
	int b;
	f(&b);
	return b + 1;
}

int c;
int main(void)
{
	c = g();
	printf("%d\n", c);
}