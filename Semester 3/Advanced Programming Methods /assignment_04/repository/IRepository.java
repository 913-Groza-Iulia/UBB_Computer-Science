package repository;
import model.PrgState;
import model.exceptions.MyExc;

import java.awt.print.PrinterGraphics;
import java.util.List;

public interface IRepository {
   PrgState getCurrentProgramState() throws MyExc;
   void add(PrgState program) throws MyExc;
   void logPrgStateExec() throws MyExc;
   int getSize();
   List<PrgState> getAll();
}
