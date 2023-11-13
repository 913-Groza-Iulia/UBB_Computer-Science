package model.statements;

import model.ADTS.MyIDictionary;
import model.PrgState;
import model.exceptions.FileAlreadyInFileTable;
import model.exceptions.MyExc;
import model.exceptions.VarNotOfTypeError;
import model.expressions.Exp;
import model.types.StringType;
import model.values.StringValue;
import model.values.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class openRFile implements IStmt{
    Exp exp;

    public openRFile(Exp expression)
    { this.exp = expression; }

    @Override
    public PrgState execute(PrgState state) throws MyExc {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        Value ifile = exp.eval(symTable);
        if(!ifile.getType().equals(new StringType()))
            throw new VarNotOfTypeError("file expr", "string");

        StringValue file = (StringValue) ifile;
        MyIDictionary<Value, BufferedReader> fileTable = state.getFileTable();
        if(fileTable.isDefined(file))
            throw new FileAlreadyInFileTable(file);
        
        BufferedReader buf;
        try
        {
            FileReader fr = new FileReader(file.getVal());
            buf = new BufferedReader(fr);
            fileTable.add(ifile,buf);
            return state;

        }catch ( FileNotFoundException e)
        {
            throw new MyExc(e.getMessage());}
    }

    @Override
    public IStmt deepCopy() {
        return new openRFile(exp.deepCopy());
    }

    @Override
    public String toString() {
        return "open " + exp;
    }
}
