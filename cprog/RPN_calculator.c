//Rickard Johansson 920918-2279. Resubmitted version, last examiner Erik

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

#define SIZE	(100)

int 	stack[SIZE];
int	sp = -1;

void push(int number)
{
	if((sp + 1) == SIZE) {
		perror("Stack is full");
		exit(1);
	}

	sp = sp + 1;	
	stack[sp] = number;
}

int pop()
{
	if(sp == -1) {
		perror("Stack is empty");
		exit(1);
	}

	int number = stack[sp];
	sp = sp - 1;
	return number;
}

int main() 
{
	int 	input = 0;
	int	new_number = 1;
	int	temp = 0;
	
	while(input != EOF) {
		input = getchar();
		if (input == EOF)
			printf("EOF!!!!!!\n");
		if(isdigit(input)) {
			if(new_number == 1)  {
				push(input - '0');
				new_number = 0;
			}else {
				push(pop()*10 + (input - '0'));
			}
		} else {
			new_number = 1;
			switch(input) {
			case '+':
				push(pop() + pop());
				break;
			case '-':
				temp = pop();
				push(pop() - temp);
				break;
			case '*':
				push(pop() * pop());
				break;
			case '/':
				temp = pop();
				if(temp == 0) {
					perror("Divided by zero");
					exit(1);
				}
				push(pop() / temp);
				break;
			case ' ':
				break;
			case '\n':
				printf("%d \n", pop());
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
