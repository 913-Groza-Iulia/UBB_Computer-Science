package model.statements;

import model.PrgState;
import model.exceptions.MyExc;
import model.exceptions.VarNotOfTypeError;
import model.expressions.Exp;
import model.types.BoolType;
import model.values.Value;
import model.values.BoolValue;

public class IfStmt implements IStmt{
    Exp exp; IStmt thenS; IStmt elseS;

    public IfStmt(Exp expression, IStmt t, IStmt el)
    {
        this.exp = expression;
        this.thenS = t; //if cond is true
        this.elseS = el;//if cond is false
    }

    @Override
    public PrgState execute(PrgState state) throws MyExc {
        Value icondition =exp.eval(state.getSymTable());
        if (!icondition.getType().equals(new BoolType()))
            throw new VarNotOfTypeError("conditional expression", "boolean");
        BoolValue condition = (BoolValue) icondition;
        if(condition.getVal())//if is true, push then
            state.getExeStack().push(thenS);
        else
            state.getExeStack().push(elseS);

        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new IfStmt(exp.deepCopy(), thenS.deepCopy(), elseS.deepCopy());
    }

    @Override
    public String toString(){
        return "(IF("+ exp.toString()+") THEN(" +thenS.toString() +")ELSE("+elseS.toString()+"))";
    }
}
