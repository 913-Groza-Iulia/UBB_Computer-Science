n=input("Give the number of trials n=");
p=input("Give the prob of success p=");
%n is a natural number, p is a value between 0 and 1
%x is the number of success or x = values for the pdf
x=0:1:n;
px=binopdf(x,n,p);
hold on %if you have multiple graphs in the same window, write 'hold on'
plot(x,px,'r+');


xx=0:0.1:n;
fx=binocdf(xx,n,p);
plot(xx,fx,'6');
