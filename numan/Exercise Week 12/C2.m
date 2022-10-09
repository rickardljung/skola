format long
x = 1.00001;
X = ones(1,100);
sum = 1-x;

for i=1:98
    sum = sum*x + (-1)^i;
    X(1,i) = sum;
end

pos = x;

for i=1:49
    pos = pos*x + 1;
end

neg = -x;
for i=1:49
    neg = neg*x - 1;
    X(1,i) = sum;
end

sum2 = neg + pos;


    