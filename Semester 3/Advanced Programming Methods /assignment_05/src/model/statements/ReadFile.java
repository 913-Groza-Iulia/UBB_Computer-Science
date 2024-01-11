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

public class ReadFile implements IStmt{
    Exp exp;
    String var_name;
    public ReadFile(Exp expression, String variable_name)
    {
        this.exp = expression;
        this.var_name = variable_name;}


    @Override
    public PrgState execute(PrgState state) throws MyExc {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();

        if(!symTable.isDefined(var_name))
            throw new VarNotInSymTableError(var_name);

        if(!symTable.lookUp(var_name).getType().equals(new IntType()))
            throw new VarNotOfTypeError(var_name, "int");
        Value file_path = exp.eval(symTable, state.getHeap());
        if(!file_path.getType().equals(new StringType()))
            throw new VarNotOfTypeError("file", "string");
        StringValue file = (StringValue) file_path;
        if(!fileTable.isDefined(file))
            throw new FileNotInFileTable(file);
        BufferedReader buf;
        IntValue var_value;
        try
        {
            buf = fileTable.lookUp(file);
            String read = buf.readLine();
            if (read == null)
                var_value = new IntValue(0);
            else
                var_value = new IntValue(Integer.parseInt(read));
            symTable.update(var_name,var_value);
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public IStmt deepCopy() { return new ReadFile(exp.deepCopy(), var_name); }
    public String toString() { return "reading from file " + exp + " into the variable " + var_name; }
}

