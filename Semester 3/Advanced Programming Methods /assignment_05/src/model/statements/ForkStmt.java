package model.statements;

import model.ADTS.*;
import model.PrgState;
import model.exceptions.MyExc;
import model.values.StringValue;
import model.values.Value;

import java.io.BufferedReader;

public class ForkStmt implements IStmt{
    IStmt stmt;
    public ForkStmt(IStmt var) { stmt = var; }

    @Override
    public PrgState execute(PrgState state) throws MyExc {
        MyIDictionary<String, Value> symTbl = state.getSymTable().cloneH();
        MyIStack<IStmt> stk = new MyStack<IStmt>();
        MyIList<Value> out = state.getOut();
        MyIDictionary<StringValue, BufferedReader> fileMap = state.getFileTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        PrgState prg = new PrgState(stk, symTbl, out, stmt, fileMap, heap);
        return prg;
    }

    @Override
    public IStmt deepCopy() {
        return new ForkStmt(stmt.deepCopy()); }

    public String toString(){return "fork(" + stmt.toString() + ")";}
}
