s = tf('s');
G = (1+s) / (1 + (s / 10)^2);
h = 0.1;
z = tf('z', h);
sp = (z - 1) / (h*z);
H = (1 + sp) / (1 + sp/10)^2;

bode(G, 'r')
hold on;
bode(H, 'g')