package model.statements;

import model.ADTS.MyIDictionary;
import model.PrgState;
import model.exceptions.MyExc;
import model.exceptions.VarNotOfTypeError;
import model.expressions.Exp;
import model.types.BoolType;
import model.types.Type;
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
        Value icondition =exp.eval(state.getSymTable(), state.getHeap());
        if (!icondition.getType().equals(new BoolType()))
            throw new VarNotOfTypeError("conditional expression", "boolean");
        BoolValue condition = (BoolValue) icondition;
        if(condition.getVal())//if is true, push then
            state.getExeStack().push(thenS);
        else
            state.getExeStack().push(elseS);

        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new IfStmt(exp.deepCopy(), thenS.deepCopy(), elseS.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyExc {
        Type typexp=exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            thenS.typecheck(typeEnv.cloneH());
            elseS.typecheck(typeEnv.cloneH());
            return typeEnv;
        }
        else
            throw new MyExc("The condition of IF has not the type bool");
    }


    @Override
    public String toString(){
        return "(IF("+ exp.toString()+") THEN(" +thenS.toString() +")ELSE("+elseS.toString()+"))";
    }
}
