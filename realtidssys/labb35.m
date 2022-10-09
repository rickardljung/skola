clear
A = [-0.12 0; 5 0];
B = [2.25; 0];
C = [0 1];
D = [0];
h = 0.05;

[phi, gamma] = c2d(A, B, h);

L = place(phi,gamma,[0.8+0.1*i 0.8-0.1*i])

lr = 1/(C * inv((eye(2) - phi +gamma * L)) * gamma)