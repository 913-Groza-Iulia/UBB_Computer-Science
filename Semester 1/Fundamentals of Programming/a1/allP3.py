# ex12

def is_leap_year(y):
    if (y % 4 == 0 and y % 100 != 0) or y % 400 == 0:
        return True
    else:
        return False


def days_in_year(y):
    if is_leap_year(y):
        return 366
    else:
        return 365


def days_in_a_month(y, m):
    if m == 1 or m == 3 or m == 5 or m == 7 or m == 8 or m == 10 or m == 12:
        return 31
    elif m == 2:
        if is_leap_year(y):
            return 29
        else:
            return 28
    else:
        return 30


def days_between_dates(y1, m1, d1, y2, m2, d2):
    days = 0
    while y1 < y2 or (y1 == y2 and m1 < m2) or (y1 == y2 and m1 == m2 and d1 < d2):
        days += 1
        d1 += 1

        if d1 > days_in_a_month(m1, y1):
            d1 = 1
            m1 += 1

        if m1 > 12:
            m1 = 1
            y1 += 1

    return days


def age_in_days(b_y, b_m, b_d, c_y, c_m, c_d):
    age = days_between_dates(b_y, b_m, b_d, c_y, c_m, c_d)
    return age


def start():
    b_y = int(input("Please introduce the birth year: "))
    b_m = int(input("Please introduce the birth month: "))
    b_d = int(input("Please introduce the birth day: "))

    c_y = int(input("Please introduce the current year: "))
    c_m = int(input("Please introduce the current month: "))
    c_d = int(input("Please introduce the current day: "))
    sol = age_in_days(b_y, b_m, b_d, c_y, c_m, c_d)
    print("The person is", sol, "days old")


# start()

# ex13
def is_prime(num):
    if num < 2 or (num > 2 and num % 2 == 0):
        return False
    i = 3
    while i * i <= num:
        if num % i == 0:
            return False
        i += 2
    return True


def primeDivisorsCounter(num):
    numberOfDivisors = 0
    if is_prime(num) or num == 1:
        return 1
    if num % 2 == 0:
        numberOfDivisors += 1
    i = 3
    while i <= int(num / 2):
        # check the odd numbers
        if num % i == 0 and is_prime(i):
            numberOfDivisors += 1
        i += 2
    return numberOfDivisors


def nthPrimeDivisor(num, counter):
    if num == 1:
        return 1
    if num % 2 == 0 and counter == 1:
        return 2
    if is_prime(num):
        return num
    if num % 2 == 0:
        counter -= 1
    for i in range(3, num, 2):
        if num % i == 0 and is_prime(i):
            counter -= 1
        if counter == 0:
            return i
    return -1


def findPrimePosition(position):
    i = 1
    while position > 0:
        position -= primeDivisorsCounter(i)
        i += 1
    i -= 1
    newPosition = primeDivisorsCounter(i) + position
    return nthPrimeDivisor(i, newPosition)


def start1():
    n = int(input("Please introduce the n: "))
    print(f"The prime number is {findPrimePosition(n)}")


# start1()


# ex14
from math import sqrt


def is_prime(n):
    """
    This functions checks if a number is prime or not.

    It returns 1 for num 2, then 0 for the left even numbers and those lower than 2. Then it checks if the num
    has any odd divisors, in which case it would return 0 ( cause the number is not prime ).
    :param n: the number whose primality we check
    :return: 1 if a number is prime, 0 if its not
    """
    if n == 2: return 1
    if n < 2 or n % 2 == 0: return 0
    stop = int(sqrt(n)) + 1
    for div in range(3, stop, 2):
        if n % div == 0: return 0


def factors(n, num):
    """
    Returns how many steps are left and the number we are currently on.

    :param n: how many steps we have left to go over to get to the element of the sequence we need to print
    :param num: the number whose prime divisors we are checking
    :return: return n and prints the element if n==0
    """
    div = 2
    while div <= num:
        if num % div == 0:
            while num % div == 0:
                num = num // div
            n = n - div
            if n <= 0: return n, div
        div = div + 1
    return n, div


def start3():
    n = int(input("Pick a number: "))
    n -= 1 
    if n == 0:
        print(f"The element is 1.")
    else:
        aux = 0
        current = 2
        while n > 0:
            if is_prime(current) != 0:
                n = n - 1
                if n == 0: print(current)
            else:
                n, aux = factors(n, current)
                if n <= 0: print(aux)
            current += 1


# start3()


# ex15
def is_perfect(num):
    sum = 1
    div = 2
    while div * div < num:
        if num % div == 0: sum = sum + div + num / div
        div += 1
    if div * div == num: sum = sum + div
    if sum == num:
        return 1
    else:
        return 0


def start4():
    n = int(input("Your number is: "))
    perf_num = n - 1
    ok = 0
    while perf_num > 5 and ok == 0:
        if is_perfect(perf_num) == 1:
            print(f"The largest perfect number smaller than {n} is {perf_num}")
            ok = 1
        else:
            perf_num -= 1
    if ok == 0: print(f"There is no perfect number smaller than {n}")

# start4()
