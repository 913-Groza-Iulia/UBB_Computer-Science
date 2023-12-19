package repository;
import model.PrgState;
import model.exceptions.MyExc;

import java.awt.print.PrinterGraphics;

public interface IRepository {
   PrgState getCurrentProgramState();
   void add(PrgState program);
}
