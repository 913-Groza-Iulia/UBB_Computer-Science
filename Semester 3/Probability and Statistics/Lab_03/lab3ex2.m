#normal approximation
p=input("Enter a p between 0.05 and 0.95: ");
for n=1:3:100
   k=0:n
   y=binopdf(k,n,p)
   plot(k,y)
   pause(0.5)
endfor


#poisson approximation
p=input("Enter a p <= 0.05: ");
n=input("Enter a n >=30: ");
v=0:n;
y=binopdf(v,n,p);
z=poisspdf(v,n*p);
plot(v,y,'m',v,z,'b');
