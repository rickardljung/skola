y0 = load('scrippsy.txt');

y = y-279;
y = log(y);

x0 = linspace(0, 50, 50);

y = polyfit(x0', y, 2);

y = exp(y);


RMSE = sqrt(sum((y0-y).^2)/length(x0));