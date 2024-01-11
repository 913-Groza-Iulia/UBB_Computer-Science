package view;

import controller.Controller;
import model.ADTS.*;
import model.PrgState;
import model.exceptions.MyExc;
import model.expressions.ArithExp;
import model.expressions.LogicExp;
import model.expressions.ValueExp;
import model.expressions.VarExp;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.Value;

import java.io.BufferedReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class View {
    Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    private final IStmt ex1 = new CompStmt(new VarDecl("v",new IntType()),
            new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))), new PrintStmt(new
                    VarExp("v"))));
    private void executeEx1() throws MyExc {
        executeStatement(ex1);
    }

    private final IStmt ex2 = new CompStmt( new VarDecl("a",new IntType()),
            new CompStmt(new VarDecl("b",new IntType()),
                    new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),new
                            ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                            new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"), new ValueExp(new
                                    IntValue(1)))), new PrintStmt(new VarExp("b"))))));

    private void executeEx2() throws MyExc {
        executeStatement(ex2);
    }

    private void executeStatement(IStmt ex) throws MyExc {
        MyStack<IStmt> stk = new MyStack<IStmt>();
        MyIDictionary<String, Value> symtbl = new MyDictionary<>();
        MyIList<Value> out = new MyList<>();
        MyIDictionary<Value, BufferedReader> fileTable = new MyDictionary<>();
        //PrgState crtPrgState = new PrgState(stk, symtbl, out, ex, fileTable);

        //this.controller.addProgram(crtPrgState);
        //boolean currentFlag = controller.getDisplayFlag();
       // try {
            //controller.allStep(currentFlag);
       // } catch (MyExc e) {
       //    System.out.println("ERROR: " + e.what());
       // }
    }

    private final IStmt ex3 = new CompStmt(new VarDecl("a", new BoolType()),
            new CompStmt(new VarDecl("v", new IntType()),
                    new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                            new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
                                    IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                    VarExp("v"))))));

    private void executeEx3() throws MyExc {
        executeStatement(ex3);
    }

    public void execute() throws MyExc {

        print_Menu();
        int user_command;
        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                user_command = in.nextInt();
                if (user_command == 0) {
                    break;
                } else if (user_command == 1) {
                    executeEx1();
                } else if (user_command == 2) {
                    executeEx2();
                } else if (user_command == 3) {
                    executeEx3();
                } else if (user_command == 4) {
                    setDisplayFlag();
                } else {
                    System.out.println("Invalid option!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Expected an integer option!");
            }
        }
    }

    private void setDisplayFlag() {
        //boolean currentFlag = controller.getDisplayFlag();
        //controller.setDisplayFlag(!currentFlag);
       // System.out.println("Display flag is now " + (!currentFlag ? "on" : "off"));
    }

    void print_Menu() {
        System.out.println("Menu:");
        System.out.println("0. Exit the program");
        System.out.println("1. Run example 1");
        System.out.println("2. Run example 2");
        System.out.println("3. Run example 3");
        System.out.println("4. Set the display flag");
        System.out.println("Choose an option: ");
    }
}
