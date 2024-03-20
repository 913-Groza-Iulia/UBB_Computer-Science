//Authentication Module: Manage user authentication with session management. 
//Username and password will be checked against a known list. A session should expire 
//after a set time. The module should allow the checking if a user is logged in. Passwords 
//should be salted

using System.Runtime.InteropServices;
using System.Security.Cryptography;

namespace AuthenticationModule
{
    public class UserManager
    {
        private Dictionary<string, string> users = new Dictionary<string, string>();
        private Dictionary<string, DateTime> sessions = new Dictionary<string, DateTime>();
        private TimeSpan sessionTimeout = TimeSpan.FromMinutes(0.2);
        const int keySize = 16;
        const int iterations = 6000;

        public UserManager()
        {
            users.Add("user1", HashPassword("password1"));
            users.Add("user2", HashPassword("password2"));
            users.Add("user3", HashPassword("password1"));
        }

        public bool AuthenticateUser(string username, string password)
        {
            if (users.ContainsKey(username))
            {
                string HashedPassword = users[username];
                
                if (VerifyPassword(password, HashedPassword))
                {
                    StartOrRenewSession(username);
                    return true;
                }
            }
            return false;
        }

        private void StartOrRenewSession(string username)
        {
            DateTime expirationTime = DateTime.Now.Add(sessionTimeout);
            sessions[username] = expirationTime;
        }

        public bool IsUserLoggedIn(string username)
        {
            return sessions.ContainsKey(username) && sessions[username] > DateTime.Now;
        }

        private string HashPassword(string password)
        {
           
            byte[] salt = RandomNumberGenerator.GetBytes(keySize);

            var pbdfk2 = new Rfc2898DeriveBytes(password, salt, iterations);
            byte[] hash = pbdfk2.GetBytes(20);
            
            byte[] combinedBytes = new byte[36];
          
            Array.Copy(salt, 0, combinedBytes, 0, keySize);
            Array.Copy(hash, 0, combinedBytes, keySize, hash.Length);
          
            string HashedPassword = Convert.ToBase64String(combinedBytes);
            return HashedPassword;
        }

        private bool VerifyPassword(string password, string HashedPassword)
        {
            byte[] storedHashPassword = Convert.FromBase64String(HashedPassword);

            byte[] storedSalt = new byte[keySize];
            Array.Copy(storedHashPassword, 0, storedSalt, 0, keySize);

            var pbdfk2 = new Rfc2898DeriveBytes(password, storedSalt, iterations);
            byte[] computedHashPassword = pbdfk2.GetBytes(20);

            for (int i = 0; i < computedHashPassword.Length; i++)
            {
                if (storedHashPassword[i + keySize] != computedHashPassword[i])
                {
                    return false;
                }
            }
            return true;
        }
    }

    internal class Program
    {
        static void Main(string[] args)
        {

            UserManager userManager = new UserManager();
            Console.WriteLine("Welcome!");
            while (true)
            {
                Console.WriteLine("1. Login");
                Console.WriteLine("2. Check if a user is logged in");

                Console.Write("Enter your choice: ");
                string choice = Console.ReadLine();

                switch (choice)
                {
                    case "1":
                        Console.WriteLine("Enter the username: ");
                        string usernameLogin = Console.ReadLine();

                        Console.WriteLine("Enter the password: ");
                        string password = Console.ReadLine();

                        if (userManager.AuthenticateUser(usernameLogin, password))
                        {
                            Console.WriteLine("\nUser authenticated successfully.\n");
                        }
                        else
                        { 
                            Console.WriteLine("\nAuthentication failed.\n");
                        }
                        break;

                    case "2":
                        Console.WriteLine("Enter the username: ");
                        string usernameStillLoggedIn = Console.ReadLine();

                        if (userManager.IsUserLoggedIn(usernameStillLoggedIn))
                        {
                            Console.WriteLine("\nUser is currently logged in.\n");
                        }
                        else
                        {
                            Console.WriteLine("\nUser is not logged in.\n");
                        }
                        break;
                    default:
                        Console.WriteLine("\nPlease enter a valid choice.\n");
                        break;
                }
            }
        }
    }
}   
