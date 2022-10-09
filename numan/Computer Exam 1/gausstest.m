%[A, b] = sparsesetup(100);
A = rand(50);
b = rand(50, 1);

tic
A\b
toc

tic
gauss(A, b)
toc