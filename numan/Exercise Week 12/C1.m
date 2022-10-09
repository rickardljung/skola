format long;
resA = zeros;
resB = zeros;


for i=1:14
    x = 1-10^(-i);
    resA(1, i) = (1-sec(x)) / (tan(x))^2;
end

for i=1:14
    x = 1-10^(-i);
    resB(1, i) = (1-(1-x)^3) / x;
end

resAb = zeros;
resBb = zeros;

for i=1:14
    x = 1-10^(-i);
    resAb(1, i) = ((cos(x)^2)/(sin(x)^2)) - (cos(x)/(sin(x)^2));
end

for i=1:14
    x = 1-10^(-i);
    resBb(1, i) = -3 + 3x - x^2;
end

