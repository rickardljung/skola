#include <mb_interface.h>
#include <xutil.h>
#include <stdio.h>
#include <time.h>
#include "xuartlite_l.h"
#include "xparameters.h"
#include "xtmrctr.h"

int binary_gcd(int u, int v)
{
      	int shift;

  /* GCD(0,v) == v; GCD(u,0) == u, GCD(0,0) == 0 */
  if (u == 0) return v;
  if (v == 0) return u;

  /* Let shift := lg K, where K is the greatest power of 2
        dividing both u and v. */

  for (shift = 0; ((u | v) & 1) == 0; ++shift) {
         u >>= 1;
         v >>= 1;
  }

  while ((u & 1) == 0)
    u >>= 1;

  /* From here on, u is always odd. */
  do {
       /* remove all factors of 2 in v -- they are not common */
       /*   note: v is not zero, so while will terminate */
       while ((v & 1) == 0)  /* Loop X */
           v >>= 1;

       /* Now u and v are both odd. Swap if necessary so u <= v,
          then set v = v - u (which is even). For bignums, the
          swapping is just pointer movement, and the subtraction
          can be done in-place. */
        if (u > v) {
                unsigned int t = v; v = u; u = t;
        }  // Swap u and v.
       v = v - u;                       // Here v >= u.
     } while (v != 0);

  /* restore common factors of 2 */
  return u << shift;

}

int mod_gcd(int a, int b)
{
        if (a == 0) return b;
        if (b == 0) return a;

        int t = 0;
        while (b != 0) {
                t = b;
                b = a % b;
                a = t;
        }
        return a;
}

int good_gcd(int n, int* numbers)
{
        int divisor = numbers[0];
        int i;
        int slave_divisor = 0;
        for (i = 1; i < n; ++i) {
              divisor =  binary_gcd(divisor, numbers[i]);
        }
        getfsl(slave_divisor, 0);
        divisor = binary_gcd(divisor, slave_divisor);
        return divisor;
}

int naive_gcd(int n, int* numbers)
{
        int divisor = numbers[0];
        int i;
        int slave_divisor = 0;
        for (i = 1; i < n; ++i) {
                divisor =  mod_gcd(divisor, numbers[i]);
        }

        getfsl(slave_divisor, 0);
        divisor = binary_gcd(divisor, slave_divisor);
        return divisor;
}

int main(){

       int n  =0;
       getfsl(n, 0);
       int i;
       int numbers[n];

       for (i = 0; i < n; i++) {
    	   getfsl(numbers[i], 0);
       }

       int good = good_gcd(n, numbers);
       putfsl(good, 0);

       int naive = naive_gcd(n, numbers);
       putfsl(naive, 0);

       return 0;
}


