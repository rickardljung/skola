#include <stdio.h>

int main(void)
{
	int a = 1249;
	int div = 1;
	int b = a;

	while(b > 9) {
		b = b / 10;
		div *= 10;
	}

	while(a != 0) {
		b = a / div;
		putchar(b + '0');

		a = a - (b * div);
		div /= 10;
	}

	putchar('\n');

	printf("%d\n", '\0' );
}