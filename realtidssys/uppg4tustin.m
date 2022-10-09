h = 0.1;
s = tf('s');
G = (1 + s) / (1 + s / 10)^2;

H = c2d(G, h, 'tustin');

bode(G, 'r')
hold on;
bode(H, 'g')