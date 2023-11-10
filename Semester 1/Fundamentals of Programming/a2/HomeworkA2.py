import random


def generate_array(n):
    random_array = []
    for i in range(n):
        random_array.append(random.randint(0, 100))
    return random_array


def bubble_sort(array, step):
    """
    Bubble Sort compares each element to the one right next to it and switch their
    places, continuing this process until all elements are sorted in the desired way.
    """
    n = len(array)
    steps = 0
    for i in range(n):
        for j in range(n-i-1):
            if array[j] > array[j+1]:
                aux = array[j]
                array[j] = array[j+1]
                array[j+1] = aux
                steps += 1
                if steps % step == 0:
                    print(f"Step {steps}: {array}")

    return array


def comb_sort(array, step):
    """
    Comb Sort improves on Bubble Sort by using a gap of the size of more than 1.
    The gap starts with a large value and shrinks by a factor of 1.3 in every
    iteration until it reaches the value 1.
    """
    steps = 0
    n = len(array)
    gap = n
    shrink = 1.3
    swapped = True
    while gap > 1 or swapped:
        gap = int(gap/shrink)
        if gap < 1:
            gap = 1
        swapped = False
        for i in range(n - gap):
            if array[i] > array[i + gap]:
                aux = array[i]
                array[i] = array[i + gap]
                array[i + gap] = aux
                swapped = True
                steps += 1
                if steps % step == 0:
                    print(f"Step {steps}: {array}")

    return array


def menu():
    print("Hello and welcome!")
    print("1.Generate a list of random natural numbers")
    print("2.Sort the list using the Bubble Sort")
    print("3. Sort the list using the Comb sort")
    print("4.Exit")


def main():
    menu()
    array = []
    while True:
        opt = int(input('Please select an option:'))
        if opt == 1:
            n = int(input("Please introduce the number of elements of the array:"))
            array = generate_array(n)
            print("This is the random generated array:", array)

        elif opt == 2:
            if not array:
                print("Please generate an array first.")
            else:
                step = int(input('Please introduce a step:'))
                b = bubble_sort(array.copy(), step)
                print("Bubble Sorted Array:", b)

        elif opt == 3:
            if not array:
                print("Please generate an array first.")
            else:
                step = int(input('Please introduce a step:'))
                c = comb_sort(array.copy(), step)
                print("Comb Sorted Array:", c)

        elif opt == 4:
            print("Exiting the program...")
            break

        elif opt > 4:
            print("Please introduce a valid option!")


main()
