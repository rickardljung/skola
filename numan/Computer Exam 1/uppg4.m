it = 30;
% beräknar medelvärdet av 30 iterationer

gtime = zeros(1, 21);
itime= zeros(1, 21);
% vektorer dit tiderna för de olika matrisdimensionerna sparas undan.
% Summan av alla it stycken tider för dimension 10*10 sparas på plats
% gtime(1, 1) och så vidare.

n = 10:50:1010;
for j = 1:it
    counter = 1;
    j
    
for i = 10:50:1010
    
    A = makecond(i, 1);
    b = rand(i, 1);

    tic
    x = A\b;
    gtime(1, counter) = gtime(1, counter) + toc;

    tic
    x = inv(A)*b;
    itime(1, counter) = itime(1, counter) + toc;
    
    counter = counter + 1;

end

end

gtime = gtime./it;
itime = itime./it;

% för att få ut ett medelvärde divideras antalet iterationer på summorna.

plot(n, gtime)
hold on

plot(n, itime, 'g')