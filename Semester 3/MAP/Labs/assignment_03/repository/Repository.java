package repository;

import model.PrgState;
import model.exceptions.MyExc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Repository implements IRepository{
    String filename;
    public Repository(String fn) {
        programStateList = new ArrayList<>();
        filename = fn;
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
    public void logPrgStateExec() throws MyExc {
        try{
            PrintWriter logFile= new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
            for(int i=0; i<getSize(); i++)
                logFile.printf(getAll().get(i).toString()+"\n");
            logFile.close();
        } catch (IOException e) {
            throw new MyExc(e.getMessage());
        }
    }

    @Override
    public int getSize() {
        return programStateList.size();
    }

    @Override
    public List<PrgState> getAll() {
        return programStateList;
    }

    public String getFilename() { return filename; }
    public void setFilename(String filename) { this.filename = filename; }

    @Override
    public String toString() {
        return "Repository{" +
                "programStateList=" + programStateList +
                '}';
    }
}
