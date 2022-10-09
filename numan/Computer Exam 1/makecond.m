function [ M ] = makecond( n, kappa )
% skapar en matris M med matrisdimension n och konditionstalet kappa

P = orth(rand(n));
Q = orth(rand(n));

D = eye(n);

% placerar användarens specifierade konditionstal på diagonalmatrisens
% första element
D(1, 1) = kappa;

M = P*D*Q;


end

