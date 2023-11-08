def is_prime(num):
    if num <= 3:
        return False
    if num % 2 == 0:
        return False

    i = 3
    while i*i <= num:
        if num % i == 0:
            return False
        i += 1
    return True


def prime_pairs(n):
    notfound = 1
    p1 = -1
    p2 = -1
    for i in range(2, n, 1):
        if notfound == 1:
            if is_prime(i) and is_prime(n-i):
                notfound = 0
                p1 = i
                p2 = n-i

    if p1 == -1 and p2 == -1:
        print("There are no prime numbers for this number!")
    else:
        print("The prime numbers are:", p1, p2)


def main():
    while True:
      num = int(input("Please introduce a number:"))
      prime_pairs(num)
      if num == 0:
         break


main()
