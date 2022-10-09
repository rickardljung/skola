[x, y] = leastSquares();

% definierar en parameter t för de 201 punkterna
t = 0:1/200:1;

% grad n på polynomet
n = 8;

% beräknar bernsteinpolynom med graden n för med parametern t
M = bernsteinMatrix(n, t);

% löser ut vektorer med kontrollpunkter för x och y.
Bx = M\x;
By = M\y;

% justerar startpunkter
Bx(1) = x(1);
By(1) = y(1);

% justerar slutpunkter
Bx(n+1) = x(201);
By(n+1) = y(201);

% ritar upp kontrollpolygonet med kontrollpunkterna
hold on;
plot(Bx, By, '-o');
axis equal;

% använder casteljau's algoritm med 1000 punkter för att ur
% kontrollpunkterna få x-värden och y-värden som sedan kan plottas.
[X, Y] = casteljau(Bx, By, n, 1000);

plot(X, Y, 'y');
axis equal;