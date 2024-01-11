package model.exceptions;

public class MyExc extends Exception implements Exc{
    String message;
    public MyExc(String msg)
    {
        super(msg);
        message=msg;
    }

    @Override
    public String what() {
        return message;
    }
}
