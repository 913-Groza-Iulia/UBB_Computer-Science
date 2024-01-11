package model.statements;

import model.PrgState;
public class NopStmt implements IStmt{
    public NopStmt(){}

    @Override
    public PrgState execute(PrgState state){
        return null; }

    @Override
    public IStmt deepCopy() { return new NopStmt(); }

    @Override
    public String toString() { return "no operation statement";}
}
