#include <stdio.h>

#define SIZE 	(4)

void input(double a[], size_t size)
{
	size_t i;

	for(i = 0; i < size; i++) {
		scanf("%lf", &a[i]);
	}
}

void print(double a[], size_t size)
{
	size_t i;

	for(i = 0; i < size; i++) {
		printf("%lf\n", a[i]);
	} 

}

int main(void)
{
	double a[SIZE];
	input(a, SIZE);
	print(a, SIZE);

}
