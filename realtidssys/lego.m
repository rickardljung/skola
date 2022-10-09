clear
h = 0.1;
g = 9.81
l = 0.25
w = sqrt(g/l);

A = [0 1; w^2 1];
B = [1; w^2];
C = [1 0];
D = [0];

ss = ss(A, B, C, D);

discsys = c2d(ss, h, 'tustin')
bode(discsys)

H = zpk(discsys)

