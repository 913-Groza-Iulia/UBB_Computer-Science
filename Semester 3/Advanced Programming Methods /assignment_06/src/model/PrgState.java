package model;

import model.ADTS.MyIDictionary;
import model.ADTS.MyIHeap;
import model.ADTS.MyIList;
import model.ADTS.MyIStack;
import model.exceptions.ExeStackIsEmpty;
import model.exceptions.MyExc;
import model.statements.IStmt;
import model.values.StringValue;
import model.values.Value;
import java.io.BufferedReader;

public class PrgState {
    static int id=0;
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    MyIDictionary<StringValue, BufferedReader> fileTable;
    MyIHeap<Integer, Value> heap;
    public static int numberThreads = 0;

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl, MyIList<Value> ot, IStmt prg, MyIDictionary<StringValue, BufferedReader> ft, MyIHeap<Integer, Value> hp) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        fileTable = ft;
        heap = hp;
        stk.push(prg);
        id=getNumberThreads() ;
    }
    public int getId(){return id;}

    public MyIStack<IStmt> getExeStack() { return exeStack; }

    public void setExeStack(MyIStack<IStmt> exeStack) { this.exeStack = exeStack; }


    public MyIDictionary<String, Value> getSymTable() { return symTable; }

    public void setSymTable(MyIDictionary<String, Value> symTable) { this.symTable = symTable; }


    public MyIList<Value> getOut() { return out; }

    public void setOut(MyIList<Value> out) { this.out = out; }


    public void setFileTable(MyIDictionary<StringValue, BufferedReader> fileTable) { this.fileTable = fileTable; }
    public MyIDictionary<StringValue, BufferedReader> getFileTable(){ return fileTable; }


    public void setHeap(MyIHeap<Integer, Value> hp)  { this.heap = hp; }

    public MyIHeap<Integer, Value> getHeap(){ return heap; }

    public boolean isNotCompleted() { return !(exeStack.isEmpty()); }

    public PrgState oneStep() throws MyExc {
        if(exeStack.isEmpty()) {
            throw new ExeStackIsEmpty();
        }
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }

    static synchronized public int getNumberThreads(){ return ++numberThreads; }

    @Override
    public String toString() {
        return "\nPrgState: " +
                "\nid: " + id +
                "\nexeStack: " + exeStack +
                "\nsymTable: " + symTable +
                "\nout=: " + out +
                "\nFiles table: " + fileTable.getKeys() +
                "\nHeap: " + heap.toString() +
                "\n";
    }
}