function [ kap, reg, rrg, rei, rri ] = invmult( n, kap )
% beräknar relativt fel och relativ residualnorm för matriser med
% matrisdimension n för matriserna med konditionstalen kap.

% kap - vektor med konditionstalen för de matriser som ska undersökas

% reg - vektor med den relativa residualnormen för matriserna då de löses
% med gausselimination
% rrg - vektor med det relativa felet för matriserna då de löses
% med gausselimination

% rei - vektor med den relativa residualnormen för matriserna då de löses
% med invertera-och-multiplicera
% rri - vektor med det relativa felet för matriserna då de löses
% med invertera-och-multiplicera

reg = [];
rrg = [];
rei = [];
rri = [];


for i = 1:length(kap)

    % skapar en matris A med matrisdimensionen n och konditionstalet kap(i)
    A = makecond(n , kap(i));
    
    % skapar en slumpad lösning x
    x = rand(n, 1);
    
    b = A*x;
    
    % approximativ lösning för gausselimination
    x1 = A\b;
    
    % approximativ lösning för invertera-och-multiplicera
    x2 = inv(A)*b;
   
   
    reg = [reg norm(x1-x, inf)/norm(x, inf)];
    rrg = [rrg norm(A*x1-b, inf)/norm(b, inf)];
    
    rei = [rei norm(x2-x, inf)/norm(x, inf)];
    rri = [rri norm(A*x2-b, inf)/norm(b, inf)];
    
end

