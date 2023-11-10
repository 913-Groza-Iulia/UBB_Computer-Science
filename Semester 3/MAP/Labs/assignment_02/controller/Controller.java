package controller;

import model.ADTS.MyIStack;
import model.PrgState;
import model.exceptions.ExeStackIsEmpty;
import model.exceptions.MyExc;
import model.statements.IStmt;
import repository.IRepository;

public class Controller {
    IRepository repo;
    boolean displayFlag;

    public Controller(IRepository repo) {
        this.repo = repo;
        this.displayFlag = true;
    }

    PrgState OneStep(PrgState state, boolean displayFlag) throws MyExc {
        MyIStack<IStmt> stk = state.getExeStack();
        if(stk.isEmpty())
            throw new ExeStackIsEmpty();
        IStmt crtStmt = stk.pop();
        crtStmt.execute(state);
        if(displayFlag) System.out.println(state);
        return state;
    }

    public void AllStep(boolean displayFlag) throws MyExc
    {
        PrgState prg = repo.getCurrentProgramState();
        System.out.println(prg);
        while (!prg.getExeStack().isEmpty()) {
           try{
               OneStep(prg, displayFlag);

           }catch (MyExc e){
               System.out.println("ERROR: " + e.what());
           }
        }
       if(!displayFlag)
           System.out.println(prg);
    }

    public boolean getDisplayFlag() {
        return displayFlag;
    }

    public void setDisplayFlag(boolean displayFlag)
    {
        this.displayFlag = displayFlag;
    }

    public void addProgram(PrgState crtPrgState) {
        this.repo.add(crtPrgState);
    }
}
