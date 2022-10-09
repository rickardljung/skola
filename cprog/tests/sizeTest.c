#include <stdio.h>

void fun(int a[10], int* b)
{
	printf("%zu\n", sizeof(a));
	printf("%zu\n", sizeof(b));

}

int main()
{
	int a[10];
	int* b;

	printf("%zu\n", sizeof(a));
	printf("%zu\n", sizeof(b));

	fun(a, b);


	
}