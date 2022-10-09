#include <stdio.h>

int main(void)
{
	int r = 111;
	int s = 32;

	printf("%d \n", r % s);
	printf("%d", r & (s-1));
}