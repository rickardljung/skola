kap = 1;
gaussmean = [];
invmean = [];
n = [];
for j =10:50:1010
       n = [n j];
end

gaussmatrix = zeros(21,21);
invmatrix = zeros(21,21);
for i = 1:21
    i
   count =1;
   for j =10:50:1010
       A = makecond(j,kap);
       b = rand(j, 1);
   
       tic
       xgauss = A\b;
       gaussmatrix(i,count) = toc;
   
   
       tic 
       xinv = inv(A)*b;
       invmatrix(i,count) = toc;
       count = count+1;
   end
end
for i = 1:21
   
   gtot =0;
   itot = 0;
   for j = 1:21
       
       gtot = gtot + gaussmatrix(j,i);
       itot = itot + invmatrix(j,i);
   end 
   
   gaussmean = [gaussmean gtot/21];
   invmean = [invmean itot/21];
end

hold on
plot(n,gaussmean, 'r');
plot(n,invmean, 'b');
hold off