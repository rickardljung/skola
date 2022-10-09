A = importdata('airfoil.txt');
x = zeros(92, 1);
y = zeros(92, 1);

% plockar ur x-värdena och y-värdena för övre delen
for i = 1:92
    x(i) = A(i, 1); 
    y(i) = A(i, 2);
end

% beräknar konstanterna för ett polynom med punkterna ovan, med hjälp av least
% squares. Ett 11-gradspolynom används.
P = polyfit(x, y, 11);

% de 9 x-värden som saknas för övre bågen
missingX = [0.806454;0.818712;0.830656;0.842274;0.853553;0.864484;0.875056;0.885257;0.895078];

% beräknar de y-värden som saknas
missingY = polyval(P, missingX);


% sätter in alla x- och y-värden i vektorerna x och y.
k = 1;
for i=22:201
    
    if i<31
        x(i) = missingX(k);
        y(i) = missingY(k);
        k= k+1;
    end
    
    if i>30
        x(i) = A(i-9, 1);
        y(i) = A(i-9, 2);
    end  
    
end

plot(x, y, 'o');
axis equal;
