package model.statements;

import model.ADTS.MyIDictionary;
import model.PrgState;
import model.exceptions.MyExc;
import model.types.Type;

public interface IStmt {
    PrgState execute(PrgState state) throws MyExc;
    IStmt deepCopy();
    MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyExc;
}
