import random
import timeit

"""
Bubble sort:
-best case: O(n) when the elements are already sorted,
-worst case: O(n^2) when the elements are in reverse order: there is n-1 passes, and during each pass,
performs n-1 comparisons and n-1 swaps => ~ O((n-1)*(n-1)),
-average case: O(n^2).

Comb sort:
-best case: O(n*log(n)) when the elements are already sorted,
-worst case: O(n^2) when the elements are in reverse order: initially the comb sort starts with a large 
gap. As the gap is gradually reduced with each pass, comb sort becomes more similar to a regular bubble sort,
-average case: O((n^2)).
"""


def generate_array(n):
    random_array = []
    for i in range(n):
        random_array.append(random.randint(0, 100))
    return random_array


def generatearray(length, random_array):
    for i in range(0, length + 1):
        x = random.randint(0, 100)
        random_array.append(x)
    return


def bubble_sort_with_step(array, step):
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


def bubble_sort(array):
    """
    Bubble Sort compares each element to the one right next to it and switch their
    places, continuing this process until all elements are sorted in the desired way.
    """
    for i in range(len(array)):
        for j in range(len(array)-i-1):
            if array[j] > array[j+1]:
                aux = array[j]
                array[j] = array[j+1]
                array[j+1] = aux

    return array


def comb_sort(array):
    """
    Comb Sort improves on Bubble Sort by using a gap of the size of more than 1.
    The gap starts with a large value and shrinks by a factor of 1.3 in every
    iteration until it reaches the value 1.
    """
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

    return array


def comb_sort_with_step(array, step):
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


def increasing_order(array):
    n = len(array) - 1
    for i in range(0, n - 1):
        for j in range(i + 1, n):
            if array[i] > array[j]:
                aux = array[i]
                array[i] = array[j]
                array[j] = aux
    return


def reverse_order(array):
    n = len(array) - 1
    for i in range(0, n - 1):
        for j in range(i + 1, n):
            if array[i] < array[j]:
                aux = array[i]
                array[i] = array[j]
                array[j] = aux
    return


def main():
    print("Hello and welcome! Please pick an option:")
    print("1. Sort a random generated array")
    print("2. Print the complexity of a sort")
    while True:
        option = int(input("Insert your option here:"))
        if option == 1:
            menu_for_A2()
        elif option == 2:
            result = menu_for_A3()
            if result == 3:
                break
        elif option == 3:
            break
        else:
            print("Please introduce a valid option!")


def menu_for_A2():
    print("Welcome again! Please pick an option:")
    print("1.Generate a list of random natural numbers")
    print("2.Sort the list using the Bubble Sort")
    print("3.Sort the list using the Comb sort")
    print("4.Exit the program")
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
                b = bubble_sort_with_step(array.copy(), step)
                print("Bubble Sorted Array:", b)

        elif opt == 3:
            if not array:
                print("Please generate an array first.")
            else:
                step = int(input('Please introduce a step:'))
                c = comb_sort_with_step(array.copy(), step)
                print("Comb Sorted Array:", c)

        elif opt == 4:
            print("Exiting the program...")
            break

        elif opt > 4:
            print("Please introduce a valid option!")


def menu_for_A3():
    while True:
        print("Welcome again! Please pick the sort to analyze:")
        print("1.Bubble sort")
        print("2.Comb sort ")
        opt = int(input("Please pick an option: "))
        if opt == 1:
            bubble_sort_complexity()
        elif opt == 2:
            comb_sort_complexity()
        elif opt == 3:
            break
        else:
            print("Please introduce a valid option!")


l1 = []
l2 = []
l3 = []
l4 = []
l5 = []
generatearray(200, l1)
generatearray(400, l2)
generatearray(800, l3)
generatearray(1200, l4)
generatearray(2400, l5)


def bubble_sort_complexity():
    while True:
        print("The lengths of the list will be: 200,400,800,1200,2400")
        print("Please select what should be the case:")
        print("1.Best case - The list is already sorted")
        print("2.Average case")
        print("3.Worst case - The list is in reverse order")
        print("4.Exit the program")
        optb = int(input("Please pick an option: "))
        if optb == 1:
            increasing_order(l1)
            increasing_order(l2)
            increasing_order(l3)
            increasing_order(l4)
            increasing_order(l5)
            print("The first sort took(200):")
            start_iter = timeit.default_timer()
            bubble_sort(l1)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)
            print("The second sort took(400):")
            start_iter = timeit.default_timer()
            bubble_sort(l2)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)
            print("The third sort took(800):")
            start_iter = timeit.default_timer()
            bubble_sort(l3)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)
            print("The fourth sort took(1200):")
            start_iter = timeit.default_timer()
            bubble_sort(l4)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)
            print("The fifth sort took(2400):")
            start_iter = timeit.default_timer()
            bubble_sort(l5)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)

        elif optb == 2:
            print("The first sort took(200):")
            start_iter = timeit.default_timer()
            bubble_sort(l1)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)
            print("The second sort took(400):")
            start_iter = timeit.default_timer()
            bubble_sort(l2)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)
            print("The third sort took(800):")
            start_iter = timeit.default_timer()
            bubble_sort(l3)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)
            print("The fourth sort took(1200):")
            start_iter = timeit.default_timer()
            bubble_sort(l4)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)
            print("The fifth sort took(2400):")
            start_iter = timeit.default_timer()
            bubble_sort(l5)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)

        elif optb == 3:
            reverse_order(l1)
            reverse_order(l2)
            reverse_order(l3)
            reverse_order(l4)
            reverse_order(l5)
            print("The first sort took(200):")
            start_iter = timeit.default_timer()
            bubble_sort(l1)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)
            print("The second sort took(400):")
            start_iter = timeit.default_timer()
            bubble_sort(l2)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)
            print("The third sort took(800):")
            start_iter = timeit.default_timer()
            bubble_sort(l3)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)
            print("The fourth sort took(1200):")
            start_iter = timeit.default_timer()
            bubble_sort(l4)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)
            print("The fifth sort took(2400):")
            start_iter = timeit.default_timer()
            bubble_sort(l5)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)

        elif optb == 4:
            print("Exiting the program...")
            break


def comb_sort_complexity():
    while True:
        print("The lengths of the list will be: 200,400,800,1200,2400")
        print("Please select what should be the case:")
        print("1.Best case - The list is already sorted")
        print("2.Average case")
        print("3.Worst case - The list is in reverse order")
        print("4.Exit the program")
        optc = int(input("Please pick an option: "))
        if optc == 1:
            increasing_order(l1)
            increasing_order(l2)
            increasing_order(l3)
            increasing_order(l4)
            increasing_order(l5)
            print("The first sort took(200):")
            start_iter = timeit.default_timer()
            comb_sort(l1)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)
            print("The second sort took(400):")
            start_iter = timeit.default_timer()
            comb_sort(l2)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)
            print("The third sort took(800):")
            start_iter = timeit.default_timer()
            comb_sort(l3)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)
            print("The fourth sort took(1200):")
            start_iter = timeit.default_timer()
            comb_sort(l4)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)
            print("The fifth sort took(2400):")
            start_iter = timeit.default_timer()
            comb_sort(l5)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)

        elif optc == 2:
            print("The first sort took(200):")
            start_iter = timeit.default_timer()
            comb_sort(l1)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)
            print("The second sort took(400):")
            start_iter = timeit.default_timer()
            comb_sort(l2)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)
            print("The third sort took(800):")
            start_iter = timeit.default_timer()
            comb_sort(l3)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)
            print("The fourth sort took(1200):")
            start_iter = timeit.default_timer()
            comb_sort(l4)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)
            print("The fifth sort took(2400):")
            start_iter = timeit.default_timer()
            comb_sort(l5)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)

        elif optc == 3:
            reverse_order(l1)
            reverse_order(l2)
            reverse_order(l3)
            reverse_order(l4)
            reverse_order(l5)
            print("The first sort took(200):")
            start_iter = timeit.default_timer()
            comb_sort(l1)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)
            print("The second sort took:(400)")
            start_iter = timeit.default_timer()
            comb_sort(l2)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)
            print("The third sort took(800):")
            start_iter = timeit.default_timer()
            comb_sort(l3)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)
            print("The fourth sort took(1200):")
            start_iter = timeit.default_timer()
            comb_sort(l4)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)
            print("The fifth sort took(2400):")
            start_iter = timeit.default_timer()
            comb_sort(l5)
            end_iter = timeit.default_timer()
            print(end_iter - start_iter)

        elif optc == 4:
            print("Exiting the program...")
            break


main()
