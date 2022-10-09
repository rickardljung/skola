//Rickard Johansson 920918-2279

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define SIZE	(158)

int		num[SIZE];
int 	temp_v[SIZE];
int		add_index = 0;
int 	pos = 0;

void add(int a)
{
	int add = 0;
	int memory = 0;
	pos = add_index - 1;
	
	while(a > 0 || memory > 0) {
		pos = pos + 1;
		add = temp_v[pos] + (a % 10) + memory;

		if(add > 9) {
			add = add % 10;
			memory = 1;
		} else {
			memory = 0;
		}
		temp_v[pos] = add;
		a = a / 10;		
	}
}

int main(int argc, char *argv[])
{
	int n = atoi(argv[1]);
	num[0] = 1;
	int	p = 0;

	int i;

	for(n; n>1; n--) {
		add_index = 0;		
		for(i = 0; i <= p; i++) {
			add(n * num[i]);
			add_index = add_index + 1;
		}
		
		p = pos;
		for(i = 0; i <= p; i++)
			num[i] = temp_v[i];	
	
		memset(temp_v, 0, SIZE * sizeof(temp_v[0]));
	}
	
	for(i = p; i>=0; i--) {
		printf("%d", num[i]);
	}

	return 0;	
}