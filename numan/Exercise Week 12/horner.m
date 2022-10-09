function [ sum ] = horner( c, x )

sum = c(length(c))*x + c(length(c)-1);
length(c)
for i = 2:length(c)-1
  sum = sum*x + c(length(c)-i);
end

