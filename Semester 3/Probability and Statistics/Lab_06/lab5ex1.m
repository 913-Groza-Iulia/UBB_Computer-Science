X = [20 21 22 23 24 25 26 27
      2  1  3  6  5  9  2  2];

Y = [75 76 77 78 79 80 81 82
      3  2  2  5  8  8  1  1];

#a)the means
mean_X=mean(X,"all");
mean_Y=mean(Y,"all");
disp(mean_X);
disp(mean_Y);

#b)the variances
var_X=var(X);
var_Y=var(Y);
disp(["The variances of X: ", num2str(var_X)]);
disp(["The variances of Y: ", num2str(var_Y)]);

#c) the covariance cov(X,Y)
cov_X=cov(X);
fprintf("The covariance of X:%f\n", cov_X);
cov_Y=cov(Y);
fprintf("The covariance of Y:%f\n", cov_Y);

#d) the correlation coefficient
cor_XY=corrcoef(X,Y);
fprintf("The correlation coefficient:%f\n", cor_XY);
