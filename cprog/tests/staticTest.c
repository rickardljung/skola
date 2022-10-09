#include<stdio.h>

void f(int b)
{
	static int a;
	if(b==1)
		a += 10;
	else
		a = 2;

	printf("%d\n", a);
}

int main(void)
{

	static int a = 1;

	f(0);

	f(1);

	f(1);



	


	
}