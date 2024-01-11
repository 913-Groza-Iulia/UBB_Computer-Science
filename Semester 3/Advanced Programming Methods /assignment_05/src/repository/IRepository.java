package repository;
import model.PrgState;
import model.exceptions.MyExc;

import java.awt.print.PrinterGraphics;
import java.util.List;

public interface IRepository {
   void add(PrgState program) throws MyExc;
   void logPrgStateExec(PrgState program) throws MyExc;
   List<PrgState> getPrgList();
   void setPrgList(List<PrgState> var);
}
