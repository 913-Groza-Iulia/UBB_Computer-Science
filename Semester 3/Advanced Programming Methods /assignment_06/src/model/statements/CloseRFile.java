package model.statements;

import model.ADTS.MyIDictionary;
import model.PrgState;
import model.exceptions.FileNotInFileTable;
import model.exceptions.MyExc;
import model.exceptions.VarNotOfTypeError;
import model.expressions.Exp;
import model.types.StringType;
import model.types.Type;
import model.values.StringValue;
import model.values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStmt{
    Exp exp;
    public CloseRFile(Exp expression) { this.exp = expression; }

    @Override
    public PrgState execute(PrgState state) throws MyExc {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        Value file_path = exp.eval(symTable, state.getHeap());

        if (!file_path.getType().equals(new StringType()))
            throw new VarNotOfTypeError("close","string");

        StringValue file = (StringValue) file_path;
        MyIDictionary<StringValue, BufferedReader> fileTbl = state.getFileTable();

        if (!fileTbl.isDefined(file))
            throw new FileNotInFileTable(file);
        try
        {
            fileTbl.lookUp((StringValue) file_path).close();
            fileTbl.remove((StringValue) file_path);
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public IStmt deepCopy() { return new CloseRFile(exp.deepCopy()); }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyExc {
        if(exp.typecheck(typeEnv).equals(new StringType()))
            return typeEnv;
        else
            throw new MyExc("CloseRFile requires a string exp");
    }

    public String toString() { return "closing the file "+exp; }
}
