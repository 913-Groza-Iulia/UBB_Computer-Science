package model.exceptions;
import model.values.Value;

public class FileNotInFileTable extends MyExc{
    String message;
    public FileNotInFileTable(Value msg) {
        super("file " + msg.toString() +" is not declared");
        message = "file " + msg.toString() +" is not declared";}

    @Override
    public String what() {
        return message;
    }
}
