package controller;

import model.ADTS.*;
import model.PrgState;
import model.exceptions.ExeStackIsEmpty;
import model.exceptions.MyExc;
import model.statements.IStmt;
import model.values.RefValue;
import model.values.Value;
import repository.IRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {

    IRepository repository;
    boolean displayFlag;

    public Controller(IRepository repository) {
        this.repository = repository;
        this.displayFlag = true;
    }

    Map<Integer,Value> safeGarbageCollector(List<Integer> symTableAddr, List<Integer> heapAddresses, Map<Integer,Value> heapAddr){
        return heapAddr.entrySet().stream()
                .filter(elem -> symTableAddr.contains(elem.getKey()) ||heapAddresses.contains(elem.getKey()))
                .collect(Collectors.toMap(HashMap.Entry::getKey, HashMap.Entry::getValue));
    }

    List<Integer> getAddress(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getVal();})
                .collect(Collectors.toList()); }

    PrgState oneStep(PrgState state, boolean display_flag) throws MyExc {
        MyIStack<IStmt> stk=state.getExeStack();
        if(stk.isEmpty()) throw new ExeStackIsEmpty();
        IStmt crtStmt = stk.pop();
        crtStmt.execute(state);
        if(display_flag)
            System.out.println(state);
        return state;
    }

    public void allStep(boolean display_flag) throws MyExc {
        PrgState prg = repository.getCurrentProgramState();
        repository.logPrgStateExec();
        while (!prg.getExeStack().isEmpty()){
            oneStep(prg, display_flag);
            prg.getHeap().setContent((HashMap<Integer, Value>) safeGarbageCollector(
                    getAddress(prg.getSymTable().getDict().values()),
                    getAddress(prg.getHeap().getContent().values()),
                    prg.getHeap().getContent()));
            repository.logPrgStateExec();
        System.out.println(prg.toString());
        }
        if(!display_flag)
            System.out.println(prg);
    }


    public void toggleDisplayFlag() {
        boolean currentFlag = getDisplayFlag();
        setDisplayFlag(!currentFlag);
        System.out.println("Display flag is now " + (!currentFlag ? "on" : "off"));
    }

    public boolean getDisplayFlag() {
        return displayFlag;
    }

    public void setDisplayFlag(boolean displayFlag) {
        this.displayFlag = displayFlag;
    }

}
