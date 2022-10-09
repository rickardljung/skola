function [ X ] = secant( f, x0, x1, TOL )

X = zeros(100);
X(1) = x0;
X(2) = x1;

error = 1;

index = 2;

while error > TOL
    
    X(index+1) = X(index)- (f(X(index))*(X(index)-X(index-1)))/(f(X(index))-f(X(index-1)));
    
    X(index-1) = X(index);
    X(index) = X(index+1);
    
    error = abs(X(index) - X(index-1)) / max(abs(X(index)), 0.000001);
    
    index = index + 2;
end

    

