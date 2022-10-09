#include <stdbool.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>

#include "poly.h"

int main(int argc, char** argv)
{
	poly_t* p = new_poly_from_string("5x^3 + x^6 - 7x^2");
	poly_t* q = new_poly_from_string("3x - x^8 + 2x^99");
	//"x^2 - 7x + 1"
	//"3x + 2"
	//"x^1000000 + 2"
	//"2x^2 + 3x + 4"


	print_poly(p);
	print_poly(q);

	poly_t* r = mul(p, q);

	print_poly(r);

	free_poly(p);
	free_poly(q);
	free_poly(r);

	return 0;
}