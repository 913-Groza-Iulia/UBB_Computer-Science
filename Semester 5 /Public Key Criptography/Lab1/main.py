import time
from random import randrange

def gcd(a,b):
    """
    This function implements the basic algorithm of gcd
    """
    if a == 0:
        return b
    if b == 0:
        return a
    if a==b:
        return a
    if a == 0 and b == 0:
        return "inf"
    gcd = 1
    for i in range(2,min(a,b)+1):
        if a%i == 0 and b%i==0:
            gcd = i
    return gcd


def gcd_substraction(a, b):
    """
    This function implements the gcd algorithm with substraction
    """
    while a!=b:
        if a>b:
            a = a-b
        else:
            b = b-a
    return a

def gcdEuclid(a,b):
    """
    This function implements the algorithm of Euclid
    """
    if a == 0 and b == 0:
        return "inf"
    if a == 0:
        return b
    return gcdEuclid(b%a,a)



def main():
    """
    This function implements the main function of the program
    :return:
    """
    a = 123456789987654328768
    b= 647326427364781267737248
    start0 = time.time()
    result0 = gcdEuclid(a,b)
    end0 = time.time()
    print(f"gcd with extended euclid: {result0}, Time taken: {end0 - start0:.6f} seconds")
    for i in range(1,11):
        print("iteration: ", i)
        a,b = randrange(0,1000000), randrange(0, 1000000)
        print("a=",a, " b=",b)

        start = time.time()
        result = gcd(a,b)
        end = time.time()
        print(f"gcd: {result}, Time taken: {end - start:.6f} seconds")

        start1 = time.time()
        result1 = gcd_substraction(a, b)
        end1 = time.time()
        print(f"gcd with substraction: {result1}, Time taken: {end1 - start1:.6f} seconds")

        start2 = time.time()
        result2 = gcdEuclid(a, b)
        end2 = time.time()
        print(f"gcd with euclid: {result2}, Time taken: {end2 - start2:.6f} seconds")


main()
