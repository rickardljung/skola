n = 3;
h = 0.05;

A = [0.6 0.5; 0 0.6];
B = [0; 2.34];
C = [0.788 -0.875];
D = [-1.64];

H = ss(A, B, C, D, h);

Ar = round(2^n*A)/2^n;
Br = round(2^n*B)/2^n;
Cr = round(2^n*C)/2^n;
Dr = round(2^n*D)/2^n;

Hr = ss(Ar, Br, Cr, Dr, h);

step(H, Hr);
figure
bode(H, Hr);

