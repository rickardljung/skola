#include <stdio.h>
#include <string.h>

int main()
{
	char s[] = "be careful with string literals!";
	char* t1;
	char* t2;
	t1 = strtok(s, " ");
	//t2 = strtok(NULL, " ");

	while(t1 != NULL) {
		printf("%s\n", t1);
		t1 = strtok(NULL, " ");

	}

	
}