m = input("m(in R)=");
sigma  = input("sigma(>0)= "); #positive number
alpha = input("alpha(in (0,1))= "); # alpha and beta numbers between 0 and 1
beta = input("beta(in (0,1))= ");

#!! in a continuos case <= is equal to <

#a) P(x<=0), P(x>=0) = ?
p1=normcdf(0,m,sigma);
fprintf("P(x<=0)=%f\n",p1);

#P(x>=0)=P(x>0)=1-P(x<=0)
p2=1-normcdf(0,m,sigma);
fprintf("P(x>=0)=%f\n",p2);

#b) P(-1<=x<=1), P(x<=-1 or x>=1) = ?
#P(a<=x<=b) = F(b) - F(a)
p3=normcdf(1,m,sigma)-normcdf(-1,m,sigma);
fprintf("P(-1<=x<=1)=%f\n",p3);

%P(X<=a OR X>=b)=1-P(a<X<b)=1-(F_X(b)-F_X(a))=1-F_X(b)+F_X(a)
p4=1-p3;
fprintf("P(x<=-1 or x>=1)=%f\n",p4);

#c)x_alpha such that P(X < x_alpha)=P(X <= x_alpha)=alpha
x_alpha=norminv(alpha,m,sigma);
fprintf("X alpha is:%f\n",x_alpha);

#d)x_beta such that P(X > x_beta)=P(X >= x_beta)=beta
x_beta=norminv(beta,m,sigma);
fprintf("X beta is:%f\n",x_beta);
