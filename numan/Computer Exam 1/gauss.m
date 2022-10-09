function [ x ] = gauss( A, b )
% beräknar lösningen x ur matrisekvationen A*x = b med hjälp av naiv
% gausselimination och LU-faktorisering

l = length(b);
L = eye(l);


% eliminerar och nollställer element i matris A genom radoperationer under
% diagonalen i varje kolonn för att skapa en uppåt triangulär matris
for j = 1:l-1
   
    for i = j+1:l
                    
        mul = (A(i,j)/A(j, j));
        
        % skapar en nedåt triangulär matris genom att spara undan
        % "multipliers" till matrisen L.
        L (i, j) = mul;
        
        A(i, :) = A(i, :) - A(j, :)*mul;
     
    end  
end

% löser ut x genom LU-faktorisering
 L
 U = A;
 c = L\b; 
 x = U\c;
 
end

