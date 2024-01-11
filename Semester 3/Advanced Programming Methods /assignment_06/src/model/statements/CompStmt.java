package model.statements;

import model.ADTS.MyIDictionary;
import model.ADTS.MyIStack;
import model.PrgState;
import model.exceptions.MyExc;
import model.types.Type;

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
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyExc {
        MyIDictionary<String,Type> typEnv1 = first.typecheck(typeEnv);
        MyIDictionary<String,Type> typEnv2 = second.typecheck(typEnv1);
        return typEnv2;
    }

    @Override
    public String toString() {
        return "("+first.toString() + ";" + second.toString()+")";
    }
}
