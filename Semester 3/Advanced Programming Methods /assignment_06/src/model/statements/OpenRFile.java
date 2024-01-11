package model.statements;

import model.ADTS.MyIDictionary;
import model.PrgState;
import model.exceptions.FileAlreadyInFileTable;
import model.exceptions.MyExc;
import model.exceptions.VarNotOfTypeError;
import model.expressions.Exp;
import model.types.StringType;
import model.types.Type;
import model.values.StringValue;
import model.values.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFile implements IStmt{
    Exp exp;

    public OpenRFile(Exp expression)
    { this.exp = expression; }

    @Override
    public PrgState execute(PrgState state) throws MyExc {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        Value file_path = exp.eval(symTable, state.getHeap());
        if(!file_path.getType().equals(new StringType()))
            throw new VarNotOfTypeError("file", "string");

        StringValue file = (StringValue) file_path;
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        if(fileTable.isDefined(file))
            throw new FileAlreadyInFileTable(file);
        
        BufferedReader buf;
        try
        {
            FileReader fr = new FileReader(file.getVal());
            buf = new BufferedReader(fr);
            fileTable.add((StringValue) file_path,buf);
            return null;

        }catch ( FileNotFoundException e)
        {
            throw new MyExc(e.getMessage());}
    }

    @Override
    public IStmt deepCopy() {
        return new OpenRFile(exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyExc {
        if(exp.typecheck(typeEnv).equals(new StringType()))
            return typeEnv;
        else
            throw new MyExc("OpenRFile requires a string exp");
    }

    @Override
    public String toString() {
        return "open " + exp;
    }
}
