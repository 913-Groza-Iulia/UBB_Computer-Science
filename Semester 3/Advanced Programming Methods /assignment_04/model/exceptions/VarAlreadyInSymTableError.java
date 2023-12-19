package model.exceptions;

public class VarAlreadyInSymTableError extends MyExc{
    String message;

    public VarAlreadyInSymTableError(String msg) {
        super("Variable " + msg + "already declared in the sym table");
        message = "variable " + msg + "already declared in the sym table";
    }

    @Override
    public String what() { return message; }

}
