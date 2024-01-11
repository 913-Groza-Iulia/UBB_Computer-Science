package model.statements;

import model.ADTS.MyIDictionary;
import model.PrgState;
import model.exceptions.MyExc;
import model.exceptions.VarNotOfTypeError;
import model.expressions.Exp;
import model.types.BoolType;
import model.types.Type;
import model.values.BoolValue;
import model.values.Value;

public class WhileStmt implements IStmt{
    Exp expression; //the loop condition
    IStmt repeatStmt; //the stmt to be repeated
    public WhileStmt(Exp exp, IStmt t){
        expression = exp;
        repeatStmt = t; }

    @Override
    public PrgState execute(PrgState state) throws MyExc {
      Value icondition = expression.eval(state.getSymTable(), state.getHeap());

      if (!icondition.getType().equals(new BoolType()))
            throw new VarNotOfTypeError("conditional expr","boolean");

      BoolValue condition = (BoolValue) icondition;
      if(condition.getVal())
          {
            state.getExeStack().push(this);
            state.getExeStack().push(repeatStmt);
         }
      return null;
    }

    @Override
    public IStmt deepCopy() {
        return new WhileStmt(expression.deepCopy(), repeatStmt.deepCopy());
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyExc {
        Type typeExpr = expression.typecheck(typeEnv);
        if(typeExpr.equals(new BoolType())){
            repeatStmt.typecheck(typeEnv.cloneH());
            return typeEnv;
        }else
            throw new MyExc("The condition of WHILE does have the type bool");
    }


    public String toString(){
        return "(WHILE(" +
                expression.toString()+
                ") " +
                repeatStmt.toString()+
                ")";
    }
}
