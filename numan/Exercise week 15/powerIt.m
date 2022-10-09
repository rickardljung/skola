function [ lam, u ] = powerIt( A, x, k )

for i=1:k
    u = x/norm(x);
    x = A*u;
    lam = u'* x;
end

u = x/norm(x);

end

