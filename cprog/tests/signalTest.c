#include <stdio.h>
#include <signal.h>


/*void catch_ctrl_c(int sig) {
	printf("Ctrl + c\n");
}
*/

int main ()
{
	
	signal(SIGINT, SIG_IGN);
	while(1) {
		printf("stop!\n");
	}
}