//Rickard Johansson 920918-2279. Resubmitted version, last examiner Erik

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

#define SIZE	(100)

int 	stack[SIZE];

typedef struct stack_t stack_t;

struct stack_t {
	int sp;
};

void push(int number, stack_t** pointer)
{
	stack_t* p = *pointer;
	int sp = (*pointer)->sp;
	if((sp + 1) == SIZE) {
		perror("Stack is full");
		exit(1);
	}
	sp = sp + 1;
	p->sp = sp;	
	stack[sp] = number;


	printf("push %d, sp=%d\n", number, sp);
}

int pop(stack_t** pointer)
{
	stack_t* p = *pointer;
	int sp = (*pointer)->sp;
	if(sp == -1) {
		perror("Stack is empty");
		exit(1);
	}

	int number = stack[sp];
	p->sp = sp - 1;	
	return number;
}

int main() 
{
	int 	input = 0;
	int	new_number = 1;
	int	temp = 0;
	stack_t* sp = malloc(sizeof(stack_t));
	sp->sp = -1;
	
	while(input != EOF) {
		input = getchar();
		if(isdigit(input)) {
			if(new_number == 1)  {
				push(input - '0', &sp);
				new_number = 0;
			}else {
				push(pop(&sp)*10 + (input - '0'), &sp);
			}
		} else {
			new_number = 1;
			switch(input) {
			case '+':
				push(pop(&sp) + pop(&sp), &sp);
				break;
			case '-':
				temp = pop(&sp);
				push(pop(&sp) - temp, &sp);
				break;
			case '*':
				push(pop(&sp) * pop(&sp), &sp);
				break;
			case '/':
				temp = pop(&sp);
				if(temp == 0) {
					perror("Divided by zero");
					exit(1);
				}
				push(pop(&sp) / temp, &sp);
				break;
			case ' ':
				break;
			case '\n':
				printf("%d \n", pop(&sp));
				sp = 0;
				break;
			case EOF:
				break;

			default:
				perror("Wrong input");
				exit(1);
		}
		}	
	}
	return 0;
}
