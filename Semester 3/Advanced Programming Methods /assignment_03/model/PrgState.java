package model;

import model.ADTS.MyIDictionary;
import model.ADTS.MyIList;
import model.ADTS.MyIStack;
import model.statements.IStmt;
import model.values.Value;
import java.io.BufferedReader;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    IStmt originalProgram; //optional field, but good to have
    MyIDictionary<Value, BufferedReader> fileTable;

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot, IStmt prg, MyIDictionary<Value, BufferedReader> ft) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        fileTable = ft;
        originalProgram = prg.deepCopy();//recreate the entire original prg
        stk.push(prg);
    }

    public MyIStack<IStmt> getExeStack() { return exeStack; }

    public void setExeStack(MyIStack<IStmt> exeStack) { this.exeStack = exeStack; }

    public MyIDictionary<String, Value> getSymTable() { return symTable; }

    public void setSymTable(MyIDictionary<String, Value> symTable) { this.symTable = symTable; }

    public MyIList<Value> getOut() { return out; }

    public void setOut(MyIList<Value> out) { this.out = out; }

    public void setFileTable(MyIDictionary<Value, BufferedReader> fileTable) { this.fileTable = fileTable; }

    public MyIDictionary<Value, BufferedReader>  getFileTable(){ return fileTable; }

    public IStmt getOriginalProgram() { return originalProgram; }

    public void setOriginalProgram(IStmt originalProgram) {
        this.originalProgram = originalProgram;
    }

    @Override
    public String toString() {
        return "PrgState:\n " +
                "exeStack= " + exeStack +
                "\n symTable= " + symTable +
                "\n out=" + out +
                "\n Files table= " + fileTable.getKeys() +
                "\n";
    }
}