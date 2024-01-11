package model.statements;

import model.ADTS.MyIDictionary;
import model.PrgState;
import model.exceptions.MyExc;
import model.expressions.Exp;
import model.types.Type;
import model.values.Value;

public class PrintStmt implements IStmt {
    Exp exp;

    public PrintStmt(Exp expression) {
        this.exp = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyExc {
        Value print = exp.eval(state.getSymTable(), state.getHeap());
        state.getOut().add(print);//add to output list
        System.out.println(print.toString());
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new PrintStmt(exp.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyExc {
        exp.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "print(" + exp.toString() + ")";
    }
}
