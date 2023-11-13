package model.statements;

import model.ADTS.MyIDictionary;
import model.PrgState;
import model.exceptions.*;
import model.expressions.Exp;
import model.types.IntType;
import model.types.StringType;
import model.values.IntValue;
import model.values.StringValue;
import model.values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class readFile implements IStmt{
    Exp exp;
    String var_name;
    public readFile(Exp expression, String variable_name)
    {
        this.exp = expression;
        this.var_name = variable_name;}


    @Override
    public PrgState execute(PrgState state) throws MyExc {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<Value, BufferedReader> fileTable = state.getFileTable();

        if(!symTable.isDefined(var_name))
            throw new VarNotInSymTableError(var_name);

        if(!symTable.lookUp(var_name).getType().equals(new IntType()))
            throw new VarNotOfTypeError(var_name, "int");
        Value ifile = exp.eval(symTable);
        if(!ifile.getType().equals(new StringType()))
            throw new VarNotOfTypeError("file expr", "string");
        StringValue file = (StringValue) ifile;//converts into a string value
        if(!fileTable.isDefined(file))
            throw new FileNotInFileTable(file);
        BufferedReader buf;
        IntValue varValue;
        try
        {
            buf = fileTable.lookUp(file);
            String read = buf.readLine();
            if (read == null)
                varValue = new IntValue(0);
            else
                varValue = new IntValue(Integer.parseInt(read));
            symTable.update(var_name,varValue);
            return state;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public IStmt deepCopy() { return new readFile(exp.deepCopy(), var_name); }
    public String toString() { return "reading from file " + exp + "into the variable " +var_name; }
}

