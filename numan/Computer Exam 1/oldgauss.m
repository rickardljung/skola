function [ x ] = gauss( A, b )

l = length(b);
L = eye(l);

for j = 1:l-1
   
    for i = j+1:l
                    
        sub = (A(i,j)/A(j, j));
        L (i, j) = sub;
        for k = 1:l
            
          A(i, k) = A(i, k) - A(j,k)*sub;
        
        end
        
    end  
end

 U = A;
 c = L\b; 
 x = U\c;
 
end

