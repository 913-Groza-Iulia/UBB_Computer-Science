X = [7 7 4 5 9 9
     4 12 8 1 8 7
     3 13 2 1 17 7
     12 5 6 2 1 13
     14 10 2 4 9 11
     3 5 12 6 10 7];

n=length(X);
conf_level=input("Enter the confidence level: ");
alpha=1-conf_level;
sigma=5;

#a)if sigma=5, find 100(1-alpha)% conf interval for the avg nr of files
#z=norminv
m1=mean(X)-sigma/sqrt(n)*norminv(1-alpha/2,0,1);
m2=mean(X)+sigma/sqrt(n)*norminv(1-alpha/2,0,1);
printf("The confidence interval for the mean when you know sigma is: (m1,m2) = (%4.3f, %4.3f)\n", m1,m2);
printf("\n");

#b)if sigma=? find 100(1-alpha)% conf interval for the avg nr of files
#s=standard deviation of the given data
m1b=mean(X)-std(X)/sqrt(n)*tinv(1-alpha/2,n-1); # the degrees of freedom are n-1 where
#n is the sample size.
m2b=mean(X)+std(X)/sqrt(n)*tinv(1-alpha/2,n-1);
printf("The confidence interval for the mean when you don't know sigma is: (m1,m2) = (%4.3f, %4.3f)\n", m1b,m2b);
printf("\n");

#c)find100(1 − alpha)% conf int for the variance and the standard deviation
%s^2 is computed with var and s is computed with std
v1=(n-1)*var(X) / chi2inv(1-alpha/2, n-1);
v2=(n-1)*var(X) / chi2inv(alpha/2, n-1);
printf("the confidence level for the variance is: (v1, v2) = (%4.3f, %4.3f)\n", v1, v2);
printf("\n");

s1 = sqrt(v1);
s2 = sqrt(v2);
printf("the confidence level for the standard deviation is: (s1, s2) = (%4.3f, %4.3f)\n", s1, s2);
printf("\n");
