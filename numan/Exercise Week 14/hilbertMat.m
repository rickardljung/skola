function [ A ] = hilbertMat( n )
A = zeros(n, n);
b = zeros(1,n);

for i=1:n
    for j=1:n
        A(i, j) = 1 / (i+j-1);
        b(i) = b(i) + A(i, j)
    end
end

end

