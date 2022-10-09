n = 14;
A = zeros(n,n);
x = ones(n, 1);

for i = 1:n
    for j = 1:n
        A(i,j) = 5/(i + 2*j - 1);
    end
end

b = A*x;

condition = cond(A);
error = x - xc; 

xc = A\b;

