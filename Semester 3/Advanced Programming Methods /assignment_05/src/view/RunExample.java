package view;

import controller.Controller;
import model.ADTS.*;
import model.PrgState;
import model.exceptions.MyExc;
import model.statements.IStmt;
import model.values.StringValue;
import model.values.Value;
import repository.IRepository;
import repository.Repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class RunExample extends Command{
    String filename;
    IStmt Prg;
    public RunExample(String key, String desc, IStmt prG, String file){
        super(key, desc);
        filename=file;
        Prg=prG;
    }
    @Override
    public void execute() {
        try {
            MyIDictionary<String, Value> symTbl = new MyDictionary<String, Value>();
            MyIStack<IStmt> stk = new MyStack<IStmt>();
            MyIList<Value> out = new MyList<Value>();
            MyIDictionary<StringValue, BufferedReader> fileMap = new MyDictionary<StringValue, BufferedReader>();
            MyIHeap<Integer, Value> heap = new MyHeap<Integer, Value>();
            PrgState prg = new PrgState(stk, symTbl, out, Prg, fileMap, heap);
            IRepository repo = new Repository(filename);
            repo.add(prg);
            Controller ctrl = new Controller(repo);

            PrintWriter writer=new PrintWriter(filename);
            writer.print("");
            writer.close();
            ctrl.allStep();

        } catch (MyExc e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
