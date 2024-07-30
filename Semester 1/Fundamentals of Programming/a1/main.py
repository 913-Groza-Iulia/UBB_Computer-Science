# ex1
def is_prime(number):
    if number <= 3:
        return True
    if number % 2 == 0 or number % 3 == 0:
        return False
    i = 5
    while i * i <= number:
        if number % i == 0:
            return False
        i += 1
    return True


def next_prime(number):
    next_prime_number = number + 1
    while True:
        if is_prime(next_prime_number):
            return next_prime_number
        else:
            next_prime_number += 1

# n = int(input("Enter a number: "))

# print(f"The first prime number larger than {n} is {next_prime(n)}")

# ex2

def ex2(number):
    notFound = 81
    firstNumber = -1
    secondNumber = -1
    for i in range(2, int(number/2), 1):
        if notFound == 1:
            if is_prime(i) and is_prime(number-i):
                firstNumber = i
                secondNumber = number - i
                notFound = 0
    print(f"The prime numbers are {firstNumber}, {secondNumber}")

def start():
    n = int(input("Enter a number: "))
    if n % 2 == 1:
        print("Please choose an even number!")
    else:
        ex2(n)

#start()

# ex3
def find_minimal_number(number):
    digits = list(str(number))
    digits.sort()
    if digits[0] == '0':
        for i in range(1, len(digits)):
            if digits[i] != '0':
                digits[0], digits[i] = digits[i], '0'
                break
    newNumber = int(''.join(digits))
    return newNumber

def start1():
    n = int(input("Enter a number: "))
    print(f"The minimal number is {find_minimal_number(n)}")

# start1()

# ex 4
def find_largest_number(number):
    digits = list(str(number))
    digits.sort(reverse=True)
    newNumber = int(''.join(digits))
    return newNumber

def start2():
    n = int(input("Enter a number: "))
    print(f"The minimal number is {find_largest_number(n)}")

# start2()

# ex 5
def largest_prime_number_smaller_than_n(number):
    number_found = number - 1
    while number_found > 0:
      if is_prime(number_found):
        return number_found
      else:
        number_found -= 1
    print("No number found")
def start3():
    n=int(input("Enter a number: "))
    print(f"The largest prime number smaller than n is {largest_prime_number_smaller_than_n(n)}")

start3()