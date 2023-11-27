package model.statements;

import model.PrgState;
import model.exceptions.MyExc;

public interface IStmt {
    PrgState execute(PrgState state) throws MyExc;
    IStmt deepCopy();
}
