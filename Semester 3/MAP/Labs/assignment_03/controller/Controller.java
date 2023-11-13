package controller;

import model.ADTS.MyDictionary;
import model.ADTS.MyIStack;
import model.ADTS.MyList;
import model.ADTS.MyStack;
import model.PrgState;
import model.exceptions.ExeStackIsEmpty;
import model.exceptions.MyExc;
import model.statements.IStmt;
import model.values.Value;
import repository.IRepository;

import java.io.BufferedReader;

public class Controller {
    IRepository repo;
    boolean displayFlag;

    public Controller(IRepository repo) {
        this.repo = repo;
        this.displayFlag = true;
    }

    PrgState OneStep(PrgState state, boolean displayFlag) throws MyExc {
        MyIStack<IStmt> stk = state.getExeStack();
        if (stk.isEmpty())
            throw new ExeStackIsEmpty();
        IStmt crtStmt = stk.pop();
        crtStmt.execute(state);
        if (displayFlag) System.out.println(state);
        return state;
    }

    public void AllStep(boolean displayFlag) throws MyExc {
        PrgState prg = repo.getCurrentProgramState();
        System.out.println(prg);
        repo.logPrgStateExec();
        while (!prg.getExeStack().isEmpty()) {
            try {
                OneStep(prg, displayFlag);
                repo.logPrgStateExec();

            } catch (MyExc e) {
                System.out.println("ERROR: " + e.what());
            }
        }
        if (!displayFlag)
            System.out.println(prg);
    }

    public boolean getDisplayFlag() {
        return displayFlag;
    }

    public void setDisplayFlag(boolean displayFlag) {
        this.displayFlag = displayFlag;
    }

    public void addProgram(PrgState crtPrgState) {
        this.repo.add(crtPrgState);
    }

    public void add(IStmt stmt) throws MyExc {
        PrgState p = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), stmt, new MyDictionary<Value, BufferedReader>());
        repo.add(p);
    }
}
