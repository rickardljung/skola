kappa = [1 10^3 10^6 10^9 10^12 10^15];

[ kap, reg, rrg, rei, rri ] = invmult(5, kappa);

figure
plot(kappa, reg, 'g');
hold on
plot(kappa, rei);

figure
loglog(kappa, rrg, 'g');
hold on
loglog(kappa, rri);