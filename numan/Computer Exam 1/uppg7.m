% beräknar tiden det tar för  backslashoperationen i Matlab, naiv 
% gausselimination med LU-faktorisering, och SOR för både en "dense" och en
% gles matris med matrisdimension n.
n = 1500;

% skapar en "dense" matris
AD= rand(n);
d = n*eye(n);
AD = AD + d;
bD = rand(n, 1);

x0 = ones(n, 1);

% skapar en gles matris
[AS, bs] = sparsesetup(n);

disp('DENSE');
disp('Backslash');
tic;
x = AD\bD;
toc;


disp('Gauss');
tic
x = gauss(AD,bD);
toc

disp('sor');
tic
x = sor(AD, bD, x0, 1.5, 10^-11);
toc

disp('SPARSE!');

disp('Backslash');
tic;
x = AS\bs;
toc;

disp('Gauss');
tic
x = gauss(AS, bs);
toc

disp('sor');
tic
x = sor(AS, bs, x0, 1.5, 10^-11);
toc