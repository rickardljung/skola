A = importdata('airfoil.txt');
X = zeros(192, 1);
Y = zeros(192, 1);

x = zeros(93, 1);
y = zeros(93, 1);

% plockar ut x-värdena och y-värdena var för sig
for i = 1:192
    
    X(i) = A(i, 1);
    Y(i) = A(i, 2);
end
  
plot(X, Y, 'o');
axis equal;
