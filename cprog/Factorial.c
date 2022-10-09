//Rickard Johansson 920918-2279

#include <stdio.h>

#define SIZE	(100)

int factorial(int n)
{
	if(n == 1) {
		return 1;
	} else {
		return factorial(n-1)*n;
	}

}

int main(int argc, char *argv[])
{
	int n = atoi(argv[1]);

	printf("%d" "%s", factorial(n), "\n");

	return 0;
	

}

