#include <stdio.h>
#include <stdbool.h>

//UI functions
void print_menu()
{
    /*
     This function prints the options from the menu on the console
     :param: none
     :return: nothing
     */
    printf("\nWelcome! Here is the menu:\n");
    printf("1. Read a vector of numbers from the keyboard\n");
    printf("2. Read a sequence of natural numbers (sequence ended by 0) and determine the number");
    printf("of 0 digits of the product of the read numbers.\n");
    printf("3. Find the longest contiguous subsequence with prime sums\n");
    printf("4. Exit the program\n");
}

int get_input_from_user()
{
    /*
     This function asks the user to choose an option from the menu
     :param: none
     :return: the value input by the user
     */
    int choice;
    printf("Enter your choice: ");
    scanf("%d",&choice);
    return choice;
}

void read_vector(int v[], int *size)
{
    /*
     This function asks the user to enter the size of the vector and its elements
     :param: array to stock elements,
     :return: nothing
     */
    printf("Enter the number of elements in the vector: ");
    scanf("%d", size);

    for(int i = 0; i < *size; i++){
        printf("Enter the elements of the vector:\n");
        scanf("%d", &v[i]);
    }
    printf("\n");
}

void print_vector(int v[], int size)
{
    /*
     This function prints to the console an array
     :param: the array to be printed and its size
     :return: nothing
     */
    for(int i = 0; i < size; i++){
        printf("%d ", v[i]);
    }
    printf("\n");
}

//Functionality a: Count 0 digits in the product of a sequence of natural numbers
void read_sequence(int sequence[])
{
    /*
     This function reads a sequence of natural numbers from the user until 0
     :param: array to store the sequence
     :return: nothing
     */
    int num, index = 0;
    printf("Enter the sequence of natural numbers (sequence ends with 0):\n");
    do {
        scanf("%d", &num);
        sequence[index++] = num;
    } while (num != 0);
}

int count_zero_digits(const int sequence[])
{
    /*
     This function calculates the product of the sequence and counts the 0 digits in it
     :param: array containing the sequence
     :return: nothing
     */
    int product = 1;
    for(int i=0; sequence[i]!=0; i++)
        product*= sequence[i];
    printf("The product is: %d\n",product);

    int zero_digits = 0;
    while (product != 0) {
        if (product % 10 == 0) {
            zero_digits++;
        }
        product /= 10;
    }
    printf("The number of 0 digits is: %d\n",zero_digits);
}

//Functionality b: find the longest contiguous subsequence such that the sum of any two consecutive elements is a prime number.
bool is_prime(int number)
{
    /*
     This function checks if a number is prime
     :param: an integer
     :return: true if the number is prime or false otherwise
     */
    if(number <=1)
        return false;
    for(int i=2; i*i <= number; i++)
        if(number % i == 0)
            return false;
    return true;
}

void find_longest_contiguous_subsequence(int v[],int size)
{
    /*
     This function finds the longest contiguous subsequence in the given vector such that
     the sum of any two consecutive elements is a prime number. It iterates through the
     vector to identify such subsequences checking if the sum of any 2 consecutive numbers
     from the vector is prime and determines the longest one.
     :param: an array of integer numbers and its size
     :return: nothing
     */
    int start = 0, end = 0;
    int max_length = 0;
    int current_length = 1;
    int max_start = 0;

    for (int i = 0; i < size - 1; i++) {
        if (is_prime(v[i] + v[i + 1])) {
            current_length++;
            if (current_length > max_length) {
                max_length = current_length;
                max_start = start;
                end = i + 1;
            }
        } else {
            current_length = 1;
            start = i + 1;
        }
    }
    printf("Longest contiguous subsequence with prime sums: ");
    for (int i = max_start; i <= end; i++)
    {
        printf("%d ", v[i]);
    }
    printf("\n");
}

//Function that runs the application
int run_app()
{
    /*
     This function prints the menu, asks the user for their choice from the menu and uses
     a switch for user-data validation
     :param: none
     :return: 0 in case of exiting
     */
    int v[100];
    int size=0;
    int choice;
    int sequence[100];
    while(1)
    {
        print_menu();
        choice=get_input_from_user();
        switch (choice)
        {
            case 1:
                read_vector(v,&size);
                printf("Your vector is: ");
                print_vector(v,size);
                break;

            case 2:
                read_sequence(sequence);
                count_zero_digits(sequence);
                break;

            case 3:
                find_longest_contiguous_subsequence(v,size);
                break;

            case 4:
                printf("Exiting...\n");
                return 0;
            default:
                printf("Invalid option, try again.\n");

        }
    }
}

int main()
{
    run_app();
    return 0;
}


