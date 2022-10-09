n = 50;

% skapar en "dense" matris A genom addera på maximala summan av alla
% radelement till diagonalen.
A= rand(n);
d = n*eye(n);
A = A + d;

x0 = ones(n, 1);

b = rand(n, 1);

disp('Backslash');
tic;
x = A\b;
toc;

disp('Gauss');
tic
x = gauss(A,b);
toc

disp('sor');
tic
x = sor(A, b, x0, 1.5, 10^-11);
toc


