package model.statements;

import model.ADTS.MyIDictionary;
import model.ADTS.MyIStack;
import model.PrgState;
import model.expressions.Exp;
import model.values.Value;
import model.types.Type;
import model.exceptions.*;

public class AssignStmt implements IStmt {
    String id; Exp exp;

    public AssignStmt(String identifier, Exp expression) {
        this.id = identifier;
        this.exp = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws MyExc{
       MyIStack<IStmt> stack = state.getExeStack();
        MyIDictionary<String, Value> symTable = state.getSymTable();
        if(symTable.isDefined(id)) {
            Value val = exp.eval(symTable);
            Type typeId = (symTable.lookUp(id)).getType();
            if ((val.getType()).equals(typeId))
                symTable.update(id, val);
            else
                throw new VariableAndTypeDoNotMatchError(id);
        } else throw new VarNotInSymTableError(id);
        return state;
    }

    @Override
    public IStmt deepCopy() {
        return new AssignStmt(id, exp.deepCopy());
    }

    @Override
    public String toString() {
        return id + "=" + exp.toString();
    }
}
