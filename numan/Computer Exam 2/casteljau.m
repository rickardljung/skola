function [ X, Y ] = casteljau( Bx, By, n, nbrOfPoints )
% beräknar med hjälp av kontrollpunkterna Bx och By x-värdena och y-värdena
% för bezier-kurvan med nbrOfPoints punkter. 

t0 = linspace(0, 1, nbrOfPoints);

X = zeros(1, nbrOfPoints);
Y = zeros(1, nbrOfPoints);


for  k = 1:nbrOfPoints
    x0 = Bx;
    y0 = By;
    
    for j = 1:n
        for i = 1:n-j+1
            x0(i) = x0(i)*(1-t0(k)) + x0(i+1)*t0(k);
            y0(i) = y0(i)*(1-t0(k)) + y0(i+1)*t0(k);
        end
    
    end
    
    X(k) = x0(1);
    Y(k) = y0(1);
     
end

end

