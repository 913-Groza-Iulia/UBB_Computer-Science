import random
import math

def is_prime(num):
    if num <= 1:
        return False
    if num <= 3:
        return True
    if num % 2 == 0 or num % 3 == 0:
        return False
    i = 5
    while i * i <= num:
        if num % i == 0 or num % (i + 2) == 0:
            return False
        i += 6
    return True

def pollard_p_minus_1(n,B,a):
    k = 1
    for q in range(2,B+1):
        if is_prime(q):
            i = 1
            while q ** i <= B:
                k *= q ** i
                i += 1


    # a = random.randint(1,n-1)
    newA = pow(a,k,n) 

    d = math.gcd(newA-1, n)

    if d == 1 or d == n:
        return "failure"
    else:
        return d

n=1241143
b=13
a=2
result = pollard_p_minus_1(n,b,a)
print(result)
