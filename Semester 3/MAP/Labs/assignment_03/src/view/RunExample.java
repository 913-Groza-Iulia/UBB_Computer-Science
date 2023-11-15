package view;

import controller.Controller;
import model.exceptions.MyExc;

public class RunExample extends Command{
    private Controller ctr;
    public RunExample(String key, String desc,Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }
    @Override
    public void execute() {
        boolean currentFlag= ctr.getDisplayFlag();
        try {

            ctr.AllStep(currentFlag);
        } catch (MyExc e) {
            System.out.println(e.what());
        }
    }
}
