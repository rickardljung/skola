#include <stdio.h>
#include <stdlib.h>

void addToStack(int* stack) {
	stack[0] = 42.99;
}

int main(void)
{
	int stack[10];
	addToStack(stack);

	printf("Element 0: %d", stack[0]);

	int* stack2 = malloc(sizeof(int)*10);
	addToStack(stack2);
	printf("Element 0 i stack2: %d", stack2[0]);

	free(stack2);

}