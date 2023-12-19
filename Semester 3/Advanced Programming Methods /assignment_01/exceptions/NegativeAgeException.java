package exceptions;

public class NegativeAgeException extends Exception{
    public NegativeAgeException(String message)
    {
        super(message);
    }
}