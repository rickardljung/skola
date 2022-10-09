function [ x ] = newton( J, f1, f2, x0, it)

for i = 1:it
   
    % måste göra J till en funktion typ
    b = [f1(x0(1), x0(2)) f2(x0(1), x0(2))]
    s = J\b
    
    x = x0 + s;
    
    x0= x;
end


end

