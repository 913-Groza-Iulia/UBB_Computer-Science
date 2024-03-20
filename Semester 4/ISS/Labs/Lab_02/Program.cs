namespace UserAuthentication
{
    internal class Program
    {
        static Dictionary<string, string> users = new Dictionary<string, string>();
        static Dictionary<string, DateTime> sessions = new Dictionary<string, DateTime>();
        static TimeSpan sessionTimeOut = TimeSpan.FromMinutes(1); 
        static void Main(string[] args)
        {
            users.Add("user1", "password1");
            users.Add("user2", "password2");

            Console.WriteLine("Welcome to the authentication system!");

            while(true)
            {
                Console.WriteLine ("Enter username: ");
                string username = Console.ReadLine();

                Console.WriteLine("Enter password: ");
                string password = Console.ReadLine();

                if(AuthenticateUser(username, password))
                {
                    Console.WriteLine("Authentication successful!");
                    string sessionToken = GenerateSessionToken();
                    Console.WriteLine($"Your session token: {sessionToken}");

                    DateTime sessionExpiryTime = DateTime.Now + sessionTimeOut;
                    //TimeSpan remainingTime = sessionExpiryTime - DateTime.Now;
                    //Console.WriteLine($"Minutes left of session: {remainingTime.TotalMinutes}");
                    //do
                    //{
                    //    if (IsSessionValid(sessionToken) == false)
                   //     {
                   //         break;
                  //      }
                  //  } while(DateTime.Now < sessionExpiryTime);
                }
                else
                {
                    Console.WriteLine("Invalid username or password. Try again.");
                }
            }
        }

        static bool AuthenticateUser(string username, string password)
        {
            if(users.ContainsKey(username) && users[username] == password)
            {
                string sessionToken = GenerateSessionToken();
                sessions[sessionToken] = DateTime.Now;
                return true;
            }
            return false;
        }

        static string GenerateSessionToken()
        {
            return Guid.NewGuid().ToString();
        }

        static bool IsSessionValid(string sessionToken)
        {
            if(sessions.ContainsKey(sessionToken)) 
            {
                DateTime lastAccessTime = sessions[sessionToken];
                if((DateTime.Now - lastAccessTime) < sessionTimeOut)
                {
                    sessions[sessionToken] = DateTime.Now;
                    return true;    
                }
                else
                {
                   sessions.Remove(sessionToken);
                   Console.WriteLine("Session expired. Please log in again.");
                }
            }
            return false;
        }
    }
}
