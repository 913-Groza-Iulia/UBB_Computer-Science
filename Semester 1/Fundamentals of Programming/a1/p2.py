
def fibonacci(n):
    f0 = 1
    f1 = 1
    f2 = f0 + f1
    while f2 <= n:
        f0 = f1
        f1 = f2
        f2 = f0 + f1
    return f2


def main():
    while True:
        num = int(input("Please introduce a number > 2:"))
        sol = fibonacci(num)
        print("The smallest fibonacci number greater than", num, "is:", sol)
        if num == 0:
            break


main()

