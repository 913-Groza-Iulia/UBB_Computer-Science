package repository;

import model.PrgState;
import model.exceptions.MyExc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Repository implements IRepository{
    String logFilePath;
    List<PrgState> programStateList;
    public Repository(String FilePath){
        programStateList = new ArrayList<>();
        this.logFilePath=FilePath; }

    @Override
    public void add(PrgState program) {
        programStateList.add(program);
    }

    @Override
    public void logPrgStateExec(PrgState program) throws MyExc {
        try{
            PrintWriter logFile= new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.write(program.toString());
            logFile.close();
        } catch (IOException e) {
            throw new MyExc(e.getMessage());
        }
    }

    public List<PrgState> getPrgList(){return this.programStateList;}
    public void setPrgList(List<PrgState> list){this.programStateList = list;}

    @Override
    public String toString() {
        return "Repository{" +
                "programStateList=" + programStateList +
                '}';
    }
}
