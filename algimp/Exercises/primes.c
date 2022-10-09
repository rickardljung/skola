#include <stdio.h>
#include <stdbool.h>

int main(void)
{
	bool prime = true;
	for(int i = 1; i <= 1000; i++) {
		if(i < 2)
			continue;
		if(i != 2 && (i % 2) == 0)
			continue;

		prime = true;
		for(int j = 3; j * j <= i; j+=2) {
			if(i % j == 0) {
				prime = false;
				break;
			}
		}
		if(prime)
			printf("%d, ", i);
	}
}