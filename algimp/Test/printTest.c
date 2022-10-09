#include <stdio.h>
#include <math.h>

int main(void) {

	for (double x = 0; x <= 0.3; x += 0.1) {
		printf("%10lf%10lf%10lf\n", x, sqrt(x), exp(x));
	}

	double numbers[4] = {0, 0.1, 0.2, 0.3};

	for(int i = 0; i <= 3; i++) {
		printf("%10lf %10lf% 10lf\n", numbers[i], sqrt(numbers[i]), exp(numbers[i]));
	}
	
	return 0;

}