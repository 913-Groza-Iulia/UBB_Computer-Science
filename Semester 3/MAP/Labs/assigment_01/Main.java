import controller.Controller;
import repository.InMemoryRepo;
import repository.*;
import view.View;

public class Main
{
    public static void main(String[] args){
        IRepository repo=new InMemoryRepo();
        Controller ctrl=new Controller(repo);
        View view=new View(ctrl);
        view.execute();
    }
}