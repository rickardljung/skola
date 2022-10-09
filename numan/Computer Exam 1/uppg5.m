n = 50;

% skapar en gles matris med matrisdimension n
[A, b] = sparsesetup(n);
x0 = ones(n, 1);

disp('Backslash');
tic;
x = A\b;
toc;

disp('Gauss');
tic
x = gauss(A,b);
toc

disp('SOR');
tic
x = sor(A, b, x0, 1.9, 10^-11);
toc
