#X=the number of heads to appear
n=3; p=0.5;
#a)pdf of x=?
x=0:3;
px=binopdf(x,n,p);
fprintf("The pdf of x is:\n");
disp([x;px]);

#b)fx=cdf of x=?
x1=0:3;
fx=binocdf(x,n,p);
fprintf("The cdf of x is:\n");
disp([x1;fx]);

#c)P(x=0), P(x!=1) = ?
fprintf("P(x->0)=%f\n", binopdf(0,n,p));
fprintf("P(x<>1)=%f\n", 1-binopdf(1,n,p));

#d)P(x<=2), P(x<2) = ?
fprintf("P(x<=2)=%f\n", binocdf(2,n,p));
#P(x<2)=P(x<=1)
fprintf("P(x<2)=%f\n",binocdf(1,n,p));

#e)P(x>=1), P(x>1) = ?
#P(x>=1)=1-P(x<1)=1-P(x<=0)
fprintf("P(x>=1)=%f\n", 1-binocdf(0,n,p));
#P(x>1)=1-P(x<=1)
fprintf("P(x>1)=%f\n", 1-binocdf(1,n,p));

#f)simulate 3 coin tosses + compute the value of x
#N-the number of simulations
N=input("Enter the number of simualtions N= ");
U=rand(3, N); #generate matrix of 3xN of random numbers
Y = (U<0.5); #tests if every elem of U is <0.5, get a matrix with 1 and 0(1 if the elem < 0.5, 0 otherwise)
X = sum(Y); #sum of each column
clf;
hist(X);





