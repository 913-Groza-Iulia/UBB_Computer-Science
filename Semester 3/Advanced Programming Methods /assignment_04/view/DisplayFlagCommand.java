package view;

import controller.Controller;

public class DisplayFlagCommand extends Command {
    Controller controller;

    public DisplayFlagCommand(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.toggleDisplayFlag();
    }
}
