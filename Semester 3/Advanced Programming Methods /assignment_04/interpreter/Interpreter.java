package interpreter;

import controller.Controller;
import model.ADTS.MyDictionary;
import model.ADTS.MyHeap;
import model.ADTS.MyList;
import model.ADTS.MyStack;
import model.PrgState;
import model.exceptions.MyExc;
import model.expressions.ArithExp;
import model.expressions.RelationalExp;
import model.types.BoolType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.StringValue;
import model.values.Value;
import repository.IRepository;
import repository.Repository;
import model.expressions.ValueExp;
import model.expressions.VarExp;
import model.statements.*;
import model.types.IntType;
import model.values.IntValue;
import model.types.RefType;
import model.expressions.ReadHeapExp;
import model.statements.NewStmt;
import view.ExitCommand;
import view.RunExample;
import view.TextMenu;

import java.io.BufferedReader;

public class Interpreter {
        public static void main(String[] args) {
                try {
                        CompStmt e0 = new CompStmt(new VarDecl("varf", new StringType()),
                                new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                                        new CompStmt(new OpenRFile(new VarExp("varf")),
                                                new CompStmt(new VarDecl("varc", new IntType()),
                                                        new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                                new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                        new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                                                new CompStmt(new PrintStmt(new VarExp("varc")), new CloseRFile(new VarExp("varf"))))))))));

                        PrgState prg0 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), e0, new MyDictionary<Value, BufferedReader>(), new MyHeap<Integer, Value>());
                        Repository repo0 = new Repository("log.txt", prg0);
                        Controller ctr0 = new Controller(repo0);
//            ctr0.add(e0);


                        CompStmt ex1 = new CompStmt(new VarDecl("v", new IntType()),
                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));

                        PrgState prg1 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), ex1, new MyDictionary<Value, BufferedReader>(), new MyHeap<Integer, Value>());
                        IRepository repo1 = new Repository("log1.txt", prg1);
                        Controller ctr1 = new Controller(repo1);
//            ctr1.add(ex1);

                        IStmt ex2 = new CompStmt(new VarDecl("a", new IntType()),
                                new CompStmt(new VarDecl("b", new IntType()),
                                        new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new
                                                ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                                new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new
                                                        IntValue(1)))), new PrintStmt(new VarExp("b"))))));

                        PrgState prg2 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), ex2, new MyDictionary<Value, BufferedReader>(), new MyHeap<Integer, Value>());
                        IRepository repo2 = new Repository("log2.txt", prg2);
                        Controller ctr2 = new Controller(repo2);
//            ctr2.add(ex2);

                        IStmt ex3 = new CompStmt(new VarDecl("a", new BoolType()),
                                new CompStmt(new VarDecl("v", new IntType()),
                                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                                        VarExp("v"))))));

                        PrgState prg3 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), ex3, new MyDictionary<Value, BufferedReader>(), new MyHeap<Integer, Value>());
                        IRepository repo3 = new Repository("log3.txt", prg3);
                        Controller ctr3 = new Controller(repo3);
 //           ctr3.add(ex3);

                        IStmt ex4 = new CompStmt(new VarDecl("a", new IntType()),
                                new CompStmt(new VarDecl("v", new IntType()),
                                        new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(4))),
                                                new CompStmt(new IfStmt(new RelationalExp(new ValueExp(new IntValue(5)), new VarExp("a"), 2), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                                        VarExp("v"))))));

                        PrgState prg4 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), ex4, new MyDictionary<Value, BufferedReader>(), new MyHeap<Integer, Value>());
                        IRepository repo4 = new Repository("log4.txt", prg4);
                        Controller ctr4 = new Controller(repo4);
//            ctr4.add(ex4);

                        IStmt ex5 = new CompStmt(new VarDecl("v", new IntType()),
                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                                        new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), 5),
                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                        new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                                new PrintStmt(new VarExp("v")))));

                        PrgState prg5 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), ex5, new MyDictionary<Value, BufferedReader>(), new MyHeap<Integer, Value>());
                        IRepository repo5 = new Repository("log5.txt", prg5);
                        Controller ctr5 = new Controller(repo5);
//            ctr5.add(ex5);

//            Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
                        IStmt ex6 = new CompStmt(new VarDecl("v", new RefType(new IntType())),
                                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                                        new CompStmt(new VarDecl("a", new RefType(new RefType(new IntType()))),
                                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                                new PrintStmt(new VarExp("a")))))));

                        PrgState prg6 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), ex6, new MyDictionary<Value, BufferedReader>(), new MyHeap<Integer, Value>());
                        IRepository repo6 = new Repository("log6.txt", prg6);
                        Controller ctr6 = new Controller(repo6);

//            Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
                        IStmt ex7 = new CompStmt(new VarDecl("v", new RefType(new IntType())),
                                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                                new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntValue(30))),
                                                        new PrintStmt(new ArithExp('+', new ReadHeapExp(new VarExp("v")), new ValueExp(new IntValue(5))))))));

                        PrgState prg7 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), ex7, new MyDictionary<Value, BufferedReader>(), new MyHeap<Integer, Value>());
                        IRepository repo7 = new Repository("log7.txt", prg7);
                        Controller ctr7 = new Controller(repo7);

//            Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)
                        IStmt ex8 = new CompStmt(new VarDecl("v", new RefType(new IntType())),
                                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                                        new CompStmt(new VarDecl("a", new RefType(new RefType(new IntType()))),
                                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                                        new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                                                new PrintStmt(new ArithExp('+', new ReadHeapExp(new ReadHeapExp(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));

                        PrgState prg8 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), ex8, new MyDictionary<Value, BufferedReader>(), new MyHeap<Integer, Value>());
                        IRepository repo8 = new Repository("log8.txt", prg8);
                        Controller ctr8 = new Controller(repo8);

//            Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
                        IStmt ex9 = new CompStmt(new VarDecl("v", new RefType(new IntType())),
                                new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                                        new CompStmt(new VarDecl("a", new RefType(new RefType(new IntType()))),
                                                new CompStmt(new NewStmt("a", new VarExp("v")),
                                                        new CompStmt(new NewStmt("v", new ValueExp(new IntValue(30))),
                                                                new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))))))));

                        PrgState prg9 = new PrgState(new MyStack<IStmt>(), new MyDictionary<String, Value>(), new MyList<Value>(), ex9, new MyDictionary<Value, BufferedReader>(), new MyHeap<Integer, Value>());
                        IRepository repo9 = new Repository("log9.txt", prg9);
                        Controller ctr9 = new Controller(repo9);

                        TextMenu menu = new TextMenu();
                        menu.addCommand(new ExitCommand("0", "exit"));
                        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
                        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
                        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
                        menu.addCommand(new RunExample("4", ex4.toString(), ctr4));
                        menu.addCommand(new RunExample("5", ex5.toString(), ctr5));
                        menu.addCommand(new RunExample("6", ex6.toString(), ctr6));
                        menu.addCommand(new RunExample("7", ex7.toString(), ctr7));
                        menu.addCommand(new RunExample("8", ex8.toString(), ctr8));
                        menu.addCommand(new RunExample("9", ex9.toString(), ctr9));
                        menu.addCommand(new RunExample("10", e0.toString(), ctr0));
                        menu.show();
                } catch (MyExc e) {
                        System.out.println(e.what());

                }
        }
}