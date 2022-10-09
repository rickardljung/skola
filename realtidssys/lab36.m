clear
A = [-0.12 0; 5 0];
B = [2.25; 0];
C = [0 1];
D = [0];
h = 0.05;

[phi, gamma] = c2d(A, B, h);

ce = [0 1 0];

phie = [phi gamma; zeros(1,2) 1];
gammae = [gamma; 0];

p1 = 0.6 + 0.2*i;
p2 = 0.6 - 0.2*i;
p3 = 0.55;

K = place(phie',ce',[p1 p2 p3])'
%sys_cl = ss(phie-gammae*K,gammae,ce,0);

