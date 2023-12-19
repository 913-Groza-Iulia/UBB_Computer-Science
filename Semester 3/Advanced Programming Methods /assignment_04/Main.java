import interpreter.Interpreter;
import model.exceptions.MyExc;

public class Main {
    public static void main(String[] args) throws MyExc {
       /* IRepository repo = new Repository();
        Controller controller = new Controller(repo);
        View view = new View(controller);
        view.execute();*/
        Interpreter.main(args);
    }
}
