#a)bernoulli distribution
p=input("Enter a p in (0,1): ");
n=input("Enter a n: ");
v=rand(1,n);
v(v>=p) = 0; # sets all values in v greater than or equal to p to 0
v(v!=0) = 1; #sets all remaining non-zero values in v to 1
unique(v); %display the unique values
[x, m] = hist(v, 2);
for i=1:2
  printf("Value %d has %d probability\n", i-1, x(i)/n);
endfor

