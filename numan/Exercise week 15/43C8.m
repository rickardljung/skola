x = linspace(2, 4, 11);
degree = 6;
X = zeros(11, degree+1);
y = zeros(1, 11);

for i=1:11
    for d=0:degree
    y(i) = y(i) + x(i)^d;
    X(i, d+1) = x(i)^d;
    end
end

