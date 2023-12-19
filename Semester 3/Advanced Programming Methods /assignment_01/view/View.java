package view;

import controller.Controller;
import exceptions.AquariumFullException;
import exceptions.LivingThingNotFoundException;
import exceptions.NegativeAgeException;
import model.*;

import java.util.Scanner;

public class View {
    Controller controller;
    private final Scanner scanner;

    public View(Controller controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void printMenu() {
        System.out.println("0-Exit the program");
        System.out.println("1-Add a living thing to the aquarium");
        System.out.println("2-Remove a living thing from the aquarium");
        System.out.println("3-Print all living things with age >1");
    }

    public void execute() {
        do {
            printMenu();
            System.out.println("Enter an option: ");
            String option = scanner.nextLine();

            switch (option) {
                case "0":
                    return;
                case "1":
                    addLivingThing();
                    break;
                case "2":
                    removeLivingThing();
                    break;

                case "3":
                    printLivingThingsOlderThan();
                    break;

            }
        } while (true);
    }

    private void addLivingThing() {

        System.out.println("Enter the type of living thing (Fish or Turtle): ");
        String type = scanner.nextLine();

        System.out.println("Enter the age: ");

        try {
            int age = Integer.parseInt(scanner.nextLine());
            if (type.equalsIgnoreCase("Fish")) {
                controller.add(new Pesti(age));
                printAll();
            } else if (type.equalsIgnoreCase("Turtle")) {
                controller.add(new BroasteTestoase(age));
                printAll();
            } else {
                System.out.println("Invalid living thing type.");
            }
        } catch (NegativeAgeException | AquariumFullException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("The given age is not a number");
        }

    }

    private void removeLivingThing() {
        System.out.println("Enter the type of living thing to remove (Fish or Turtle): ");
        String type = scanner.nextLine();

        System.out.println("Enter the age: ");

        try {
            int age = Integer.parseInt(scanner.nextLine());
            if ("Fish".equalsIgnoreCase(type)) {
                controller.remove(new Pesti(age));
                printAll();
            } else if ("Turtle".equalsIgnoreCase(type)) {
                controller.remove(new BroasteTestoase(age));
                printAll();
            } else {
                System.out.println("Invalid living thing type.");
            }
        } catch (LivingThingNotFoundException |
                 NegativeAgeException e) {
            System.out.println(e.getMessage());
        } catch (
                NumberFormatException e) {
            System.out.println("Given age is not a number");
        }

    }

    private void printLivingThingsOlderThan() {
        Acvariu[] olderLivingThings = controller.getAllOlderThan(1);

        System.out.println("Living things older than " + 1 + " years:");
        for (Acvariu livingThing : olderLivingThings) {
            if (livingThing != null) {
                System.out.println(livingThing);
            }
        }
    }

    private void printAll() {
        Acvariu[] olderLivingThings = controller.getAll();
        for (Acvariu livingThing : olderLivingThings) {
            if (livingThing != null) {
                System.out.println(livingThing);
            }
        }
    }
}