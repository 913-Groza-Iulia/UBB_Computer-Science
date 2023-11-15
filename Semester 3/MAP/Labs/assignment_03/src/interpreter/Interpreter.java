package interpreter;

import controller.Controller;
import model.exceptions.MyExc;
import model.expressions.ArithExp;
import model.expressions.RelationalExp;
import model.types.BoolType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.StringValue;
import repository.IRepository;
import repository.Repository;
import model.expressions.ValueExp;
import model.expressions.VarExp;
import model.statements.*;
import model.types.IntType;
import model.values.IntValue;
import view.ExitCommand;
import view.RunExample;
import view.TextMenu;

public class Interpreter {
    public static void main(String[] args) {
        try {
            CompStmt e0 =new CompStmt(
                    new VarDecl("varf", new StringType()),
                    new CompStmt(
                            new AssignStmt("varf", new ValueExp(new StringValue("test.in"))),
                            new CompStmt(
                                    new openRFile(new VarExp("varf")), // Open the file before reading
                                    new CompStmt(
                                            new VarDecl("varc", new IntType()),
                                            new CompStmt(
                                                    new readFile(new VarExp("varf"), "varc"),
                                                    new CompStmt(
                                                            new PrintStmt(new VarExp("varc")),
                                                            new CompStmt(
                                                                    new readFile(new VarExp("varf"), "varc"),
                                                                    new CompStmt(
                                                                            new PrintStmt(new VarExp("varc")),
                                                                            new closeRFile(new VarExp("varf"))
                                                                    )
                                                            )
                                                    )
                                            )
                                    )
                            )
                    )
            );

            IRepository repo0 = new Repository("log.txt");
            Controller ctr0 = new Controller(repo0);
            ctr0.add(e0);


            CompStmt ex1 = new CompStmt(new VarDecl("v", new IntType()),
                    new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));

            IRepository repo1 = new Repository("log1.txt");
            Controller ctr1 = new Controller(repo1);
            ctr1.add(ex1);

            IStmt ex2 = new CompStmt( new VarDecl("a",new IntType()),
                    new CompStmt(new VarDecl("b",new IntType()),
                            new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new
                                    ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                    new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"), new ValueExp(new
                                            IntValue(1)))), new PrintStmt(new VarExp("b"))))));

            IRepository repo2 = new Repository("log2.txt");
            Controller ctr2 = new Controller(repo2);
            ctr2.add(ex2);

            IStmt ex3 = new CompStmt(new VarDecl("a", new BoolType()),
                    new CompStmt(new VarDecl("v", new IntType()),
                            new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                    new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                            VarExp("v"))))));

            IRepository repo3 = new Repository("log3.txt");
            Controller ctr3 = new Controller(repo3);
            ctr3.add(ex3);

            IStmt ex4 = new CompStmt(new VarDecl("a", new IntType()),
                    new CompStmt(new VarDecl("v", new IntType()),
                            new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(4))),
                                    new CompStmt(new IfStmt(new RelationalExp(new ValueExp(new IntValue(5)),new VarExp("a"),2), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                            VarExp("v"))))));

            IRepository repo4 = new Repository("log4.txt");
            Controller ctr4 = new Controller(repo4);
            ctr4.add(ex4);


            TextMenu menu = new TextMenu();
            menu.addCommand(new ExitCommand("0", "exit"));
            menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
            menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
            menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
            menu.addCommand(new RunExample("4", ex4.toString(), ctr4));
            menu.addCommand(new RunExample("5", e0.toString(), ctr0));
            menu.addToggleDisplayFlagCommand("6", "Toggle Display Flag", ctr0);
            menu.show();
        }
        catch (MyExc e){
            System.out.println(e.what());
        }

    }
}