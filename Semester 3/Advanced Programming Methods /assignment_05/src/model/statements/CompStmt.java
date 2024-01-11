package model.statements;

import model.ADTS.MyIStack;
import model.PrgState;

public class CompStmt implements IStmt{
    IStmt first;
    IStmt second;

    public CompStmt(IStmt stmt1, IStmt stmt2) {
        this.first = stmt1;
        this.second = stmt2;
    }

    @Override
    public PrgState execute(PrgState state) {
        MyIStack<IStmt> stack = state.getExeStack();
        stack.push(second);
        stack.push(first);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new CompStmt(first.deepCopy(), second.deepCopy());
    }

    @Override
    public String toString() {
        return "("+first.toString() + ";" + second.toString()+")";
    }
}
