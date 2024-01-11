package interpreter;

import model.exceptions.MyExc;
import model.expressions.ArithExp;
import model.expressions.RelationalExp;
import model.types.BoolType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.StringValue;
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


public class Interpreter {
    public static void main(String[] args){
       try {
           CompStmt ex1 = new CompStmt(new VarDecl("varf", new StringType()),
                   new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                           new CompStmt(new OpenRFile(new VarExp("varf")),
                                   new CompStmt(new VarDecl("varc", new IntType()),
                                           new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                   new CompStmt(new PrintStmt(new VarExp("varc")),
                                                           new CompStmt(new ReadFile(new VarExp("varf"), "varc"),
                                                                   new CompStmt(new PrintStmt(new VarExp("varc")), new CloseRFile(new VarExp("varf"))))))))));

           CompStmt ex2 = new CompStmt(new VarDecl("v", new IntType()),
                   new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));

           IStmt ex3 = new CompStmt(new VarDecl("a", new IntType()),
                   new CompStmt(new VarDecl("b", new IntType()),
                           new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                   new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));

           IStmt ex4 = new CompStmt(new VarDecl("a", new BoolType()),
                   new CompStmt(new VarDecl("v", new IntType()),
                           new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                   new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));


           IStmt ex5 = new CompStmt(new VarDecl("v", new IntType()),
                   new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                           new CompStmt(new WhileStmt(new RelationalExp(new VarExp("v"), new ValueExp(new IntValue(0)), 5),
                                   new CompStmt(new PrintStmt(new VarExp("v")),
                                           new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))), new PrintStmt(new VarExp("v")))));


           IStmt ex6 = new CompStmt(new VarDecl("v", new RefType(new IntType())),
                   new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                           new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                   new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntValue(30))),
                                           new PrintStmt(new ArithExp('+', new ReadHeapExp(new VarExp("v")), new ValueExp(new IntValue(5))))))));

           //Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
           IStmt ex7 = new CompStmt(new VarDecl("v", new RefType(new IntType())),
                   new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                           new CompStmt(new VarDecl("a", new RefType(new RefType(new IntType()))),
                                   new CompStmt(new NewStmt("a", new VarExp("v")),
                                           new CompStmt(new NewStmt("v", new ValueExp(new IntValue(30))),
                                                   new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))))))));

           //Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)
           IStmt ex8 = new CompStmt(new VarDecl("v", new RefType(new IntType())),
                   new CompStmt(new NewStmt("v", new ValueExp(new IntValue(20))),
                           new CompStmt(new VarDecl("a", new RefType(new RefType(new IntType()))),
                                   new CompStmt(new NewStmt("a", new VarExp("v")),
                                           new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                                   new PrintStmt(new ArithExp('+', new ReadHeapExp(new ReadHeapExp(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));


           //int v; Ref int a; v=10;new(a,22);fork(wH(a,30);v=32;print(v);print(rH(a)));print(v);print(rH(a))
           IStmt ex9 = new CompStmt(new VarDecl("v", new IntType()),
                   new CompStmt(new VarDecl("a", new RefType(new IntType())),
                           new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                   new CompStmt(new NewStmt("a", new ValueExp(new IntValue(22))),
                                           new CompStmt(new ForkStmt(new CompStmt(new NewStmt("a", new ValueExp(new IntValue(30))),
                                                   new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                           new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp(new VarExp("a"))))))),
                                                   new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp(new VarExp("a")))))))));

           TextMenu menu = new TextMenu();
           menu.addCommand(new ExitCommand("0", "exit"));
           menu.addCommand(new RunExample("1", ex1.toString(), ex1, "log1.txt"));
           menu.addCommand(new RunExample("2", ex2.toString(), ex2, "log2.txt"));
           menu.addCommand(new RunExample("3", ex3.toString(), ex3, "log3.txt"));
           menu.addCommand(new RunExample("4", ex4.toString(), ex4, "log4.txt"));
           menu.addCommand(new RunExample("5", ex5.toString(), ex5, "log5.txt"));
           menu.addCommand(new RunExample("6", ex6.toString(), ex6, "log6.txt"));
           menu.addCommand(new RunExample("7", ex7.toString(), ex7, "log7.txt"));
           menu.addCommand(new RunExample("8", ex8.toString(), ex8, "log8.txt"));
           menu.addCommand(new RunExample("9", ex9.toString(), ex9, "log9.txt"));
           menu.show();
       }catch(MyExc e)
       {
           System.out.println(e.getMessage());
       }
    }
}