namespace Lab1_ex5
{
    internal class Program
    {

        static bool checkIfTwoStringsAreAnagrams(string first_string, string second_string)
        {
            first_string = first_string.Replace(" ","").ToLower();
            second_string = second_string.Replace(" ","").ToLower();   
            
            if(first_string.Length != second_string.Length) { return false; }

            first_string = SortStrings(first_string);
            second_string = SortStrings(second_string);

            return first_string == second_string;
        }

        static string SortStrings(string string_to_be_sorted) {
            char[] char_array = string_to_be_sorted.ToCharArray();
            Array.Sort(char_array);
            return new string(char_array);
        }
        static void Main(string[] args)
        {
            Console.WriteLine("Enter the first string");
            string first_string=Console.ReadLine();

            Console.WriteLine("Enter the second string");
            string second_string=Console.ReadLine();

            if(checkIfTwoStringsAreAnagrams(first_string, second_string))
            {
                Console.WriteLine($"{first_string} and {second_string} are anagrams");
            }
            else
            {
                Console.WriteLine($"{first_string} and {second_string} are not anagrams");
            }
        }
    }
}
