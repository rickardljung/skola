A = [-0.12 0; 5 0];
B = [2.25; 0];
C = [0 1];
D = [0];

h = 0.05;

ss = ss(A, B, C, D);

discsys = c2d(ss, h, 'tustin')
bode(discsys)


H = zpk(discsys)