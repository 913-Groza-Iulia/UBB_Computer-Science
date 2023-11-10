package repository;

import model.PrgState;
import model.exceptions.MyExc;
import java.util.*;

public class Repository implements IRepository{

    public Repository() {
        programStateList = new ArrayList<>();
    }

    List<PrgState> programStateList;
    @Override
    public PrgState getCurrentProgramState() {
            return programStateList.get(0);
    }

    @Override
    public void add(PrgState program) {
        programStateList.add(program);
    }

    @Override
    public String toString() {
        return "Repository{" +
                "programStateList=" + programStateList +
                '}';
    }
}
