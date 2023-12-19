package model.exceptions;

public class VariableAndTypeDoNotMatchError extends MyExc {
    String message;
    public VariableAndTypeDoNotMatchError(String msg)
    {
        super("Declared type of variable" + msg + "and type of the assigned expression do not match");
        message = "declared type of variable" + msg + " and type of the assigned expression do not match";}

    @Override
    public String what() {
        return message;
    }
}
