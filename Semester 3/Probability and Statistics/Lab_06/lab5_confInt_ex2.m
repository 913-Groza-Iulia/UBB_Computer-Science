X1 = [22.4 21.7...
      24.5 23.4...
      21.6 23.3...
      22.4 21.6...
      24.8 20.0];

X2 = [17.7 14.8...
      19.6 19.6...
      12.1 14.8...
      15.4 12.6...
      14.0 12.2];

n1 = length(X1);
n2 = length(X2);
conf_level=input("Enter the confidence level: \n");
alpha=1-conf_level;

#a)σ1=σ2, find 100(1 − alpha)% confidence interval for the difference of the true means
sp=((n1-1)*var(X1)+(n2-1)*var(X2)) / (n1+n2-2);
m1=mean(X1)-mean(X2)-tinv(1-alpha/2,n1+n2-2)*sp*sqrt(1/n1+1/n2);
m2=mean(X1)-mean(X2)+tinv(1-alpha/2,n1+n2-2)*sp*sqrt(1/n1+1/n2);
printf("The confidence interval for the difference when σ1=σ2: (m1,m2) = (%4.3f, %4.3f)\n", m1,m2);
printf("\n");

#b)σ1!=σ2, find 100(1 − alpha)% confidence interval for the difference of the true means
c=(var(X1)/n1) / (var(X1)/n1 + var(X2)/n2);
n=1/(c*c/(n1-1) + (1-c)*(1-c)/(n2-1));
aux=c^2 / (n1-1) + (1-c)^2 / n2;
n=ceil(1/aux); %parte intreaga
m1b=mean(X1)-mean(X2)-tinv(1-alpha/2,n)*sqrt(var(X1)/n1+var(X2)/n2);
m2b=mean(X1)-mean(X2)+tinv(1-alpha/2,n)*sqrt(var(X1)/n1+var(X2)/n2);
printf("The confidence interval for the difference when σ1!=σ2: (m1,m2) = (%4.3f, %4.3f)\n", m1b,m2b);
printf("\n");

#c)find 100(1 − alpha)% confidence interval for the ratio of the variances
m1c=1/finv(1-alpha/2,n1-1,n2-1)*var(X1)/var(X2);
m2c=1/finv(alpha/2,n1-1,n2-1)*var(X1)/var(X2);
printf("The confidence interval for the ration of variances: (m1,m2) = (%4.3f, %4.3f)\n", m1c,m2c);
printf("\n");
