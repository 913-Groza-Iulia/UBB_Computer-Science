package model.statements;

import model.ADTS.MyIDictionary;
import model.PrgState;
import model.exceptions.FileNotInFileTable;
import model.exceptions.MyExc;
import model.exceptions.VarNotOfTypeError;
import model.expressions.Exp;
import model.types.StringType;
import model.values.StringValue;
import model.values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class closeRFile implements IStmt{
    Exp exp;
    public closeRFile(Exp expression) { this.exp = expression; }

    @Override
    public PrgState execute(PrgState state) throws MyExc {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        Value file_path = exp.eval(symTable);

        if (!file_path.getType().equals(new StringType()))
            throw new VarNotOfTypeError("close","string");

        StringValue file = (StringValue) file_path;
        MyIDictionary<Value, BufferedReader> fileTbl = state.getFileTable();

        if (!fileTbl.isDefined(file))
            throw new FileNotInFileTable(file);
        try
        {
            fileTbl.lookUp(file_path).close();
            fileTbl.remove(file_path);
            return state;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public IStmt deepCopy() { return new closeRFile(exp.deepCopy()); }

    public String toString() { return "closing the file "+exp; }
}
