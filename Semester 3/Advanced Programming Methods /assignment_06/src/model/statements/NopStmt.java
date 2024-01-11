package model.statements;

import model.ADTS.MyIDictionary;
import model.PrgState;
import model.exceptions.MyExc;
import model.types.Type;

public class NopStmt implements IStmt{
    public NopStmt(){}

    @Override
    public PrgState execute(PrgState state){
        return null; }

    @Override
    public IStmt deepCopy() { return new NopStmt(); }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyExc {
        return typeEnv;
    }

    @Override
    public String toString() { return "no operation statement";}
}
