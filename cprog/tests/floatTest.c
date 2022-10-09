#include <stdio.h>

int main(void) 
{
	float a[] = { 1.0, 10000.0,  10000.0 };
	float b[] = { 1.0, 10000.0, -10000.0 };
	float sum = 0.0;
	int i;

	for (i=0; i<3; i++) {
		sum = sum + a[i]*b[i];
		printf("%f\n", sum );
	}

	printf("sum = %f\n", sum);
}