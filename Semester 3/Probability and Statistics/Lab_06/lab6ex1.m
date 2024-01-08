#ztest when the population standard deviation is known,
#ttest when the population standard deviation is unknown and must be estimated from the sample.
#vartest2 when comparing the variances of two independent samples
#find the rejection region, the value of the test statistic and the P-value of the test for all prbs
X = [7 7 4 5 9 9 ...
    4 12 8 1 8 7 ...
    3 13 2 1 17 7 ...
    12 5 6 2 1 13 ...
    14 10 2 4 9 11 ...
    3 5 12 6 10 7];
n = length(X);
alpha = input("Enter the significance level: ");
fprintf("alpha=%f\n",alpha);

#a)σ=5, at the 5% significance level,does the data suggest that the standard is met?
#What about at 1%?
#The null hypothesis is H0: miu = 8.5 (It goes together wth miu > 8.5),the standard is met
#The alternative hyphotesis H1: miu < 8.5, the standard is not met
#=> this is a left tailed test
sigma=5;
m0=input("Enter the m0: ");#it will be 8.5
#ztest=N
[H, PVAL, CI, ZVALUE] = ztest (X, m0, sigma, 'alpha', alpha, 'tail', 'left');
zalpha=norminv(alpha,0,1);
RR=[-inf,zalpha];
printf("The confidence interval for mu is (%4.3f,%4.3f)\n", CI);
printf("The rejection region is (%4.3f, %4.3f)\n", RR);
printf("The value of the test statistic z is %4.3f\n", ZVALUE);
printf("The P-value of the test is %4.3f\n", PVAL);

if H == 1 % result of the test, h = 0, if H0 is NOT rejected, h = 1, if H0 IS rejected
    printf("\nThe null hypothesis is rejected.\n");
    printf("The data suggests that the standard IS NOT met.\n");
else
    printf("\nThe null hypothesis is not rejected.\n");
    printf("The data suggests that the standard IS met.\n");
end

#b)if σ=?,does the data suggest that,on avg,the nr of files stored exceeds 5.5? (same significance level)
#H0: miu<= 5.5
#H1: miu> 5.5
#=> this is a right tailed test
m0=input("Enter the m0: ");#it will be 5.5
[H, PVAL, CI, STATS] = ttest (X, m0, 'alpha', alpha, 'tail', 'right');
RR=[tinv(1-alpha,n-1),+inf];
printf("\nThe confidence interval for mu is (%4.3f,%4.3f)\n", CI);
printf("the rejection region is (%4.3f,%4.3f)\n", RR);
printf("the value of the test statistic t is %4.3f\n", STATS);
printf("the P-value of the test is %4.3f\n", PVAL);
if H==1
    printf("The null hypothesis is rejected.\n");
    printf("The data suggests that the average exceeds 5.5.");
else
    printf("The null hypothesis is not rejected.\n");
    printf("The data suggests that the average DOES NOT exceed 5.5.\n");
end


#for the left tailed test: H0=he parameter is greater than or equal to the specified value.
#H1=The parameter is less than the specified value.

#for the right tailed test: H0=he parameter is less than or equal to the specified value.
#The parameter is greater than the specified value.
#exceeds,increased


#for the two tailed test: H0=The parameter is equal to the specified value.
#H1=The parameter is not equal to the specified value.


