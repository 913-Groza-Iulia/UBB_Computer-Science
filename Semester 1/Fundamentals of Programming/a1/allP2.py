# ex7

def is_prime(num):
    if num <= 3:
        return True
    if num % 2 == 0 or num % 3 == 0:
        return False
    i = 5
    while i * i <= num:
        if num % i == 0:
            return False
    return True


def find_twin_prime_numbers(num):
    first_number = num + 1
    if is_prime(first_number):
        second_number = first_number + 2
        if is_prime(second_number):
            return first_number, second_number
    first_number += 1


def start2():
    while True:
        n = int(input("Enter a number:"))
        if n <= 0:
            print("Please choose another number")
        else:
            print(f"The twin number larger than {n} is {find_twin_prime_numbers(n)}")
            break


# start2()

# ex8
def fibonacci(n):
    f0 = 1
    f1 = 1
    f2 = f0 + f1
    while f2 <= n:
        f0 = f1
        f1 = f2
        f2 = f0 + f1
    return f2


def start():
    while True:
        num = int(input("Please introduce a number > 2:"))
        sol = fibonacci(num)
        print(f"The smallest fibonacci number greater than {num} is {sol}")
        if num == 0:
            break


# start()
# ex9
def productOfAllFactors(num):
    product = 1
    i = 2
    while i*i < num:
        if num % i == 0:
            product = product * i * int(num / i)
        i += 1
        if i*i == num:
            product = product * i
    print(f"The product of all factors of {num} is {product}")

def start3():
    n = int(input("Enter a number:"))
    productOfAllFactors(n)

# start3()

# ex10
def find_palindrome(num):
    reverse_num = num[::-1]  # start by the end and move backward with -1
    palindrome_number = int(reverse_num)
    return palindrome_number


def start1():
    n = input("Enter a number:")
    print(f"The palindrome number of {n} is {find_palindrome(n)}")

# start1()

# ex11
def have_property_p(num1, num2):
    set_n1 = set(str(num1))
    set_n2 = set(str(num2))

    return set_n1 == set_n2

def start4():
    n1 = int(input("Enter the first natural number: "))
    n2 = int(input("Enter the second natural number: "))
    if have_property_p(n1, n2):
        print(f"The numbers {n1} and {n2} have the property P")
    else:
        print(f"The numbers {n1} and {n2} do not have the property P")

start4()

