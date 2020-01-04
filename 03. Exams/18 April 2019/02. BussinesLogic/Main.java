import core.EngineImpl;
import core.ManagerControllerImpl;
import core.interfaces.Engine;
import core.interfaces.ManagerController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
/*
        Engine engine = new EngineImpl();
        engine.run();*/
/*        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        ManagerController managerController = new ManagerControllerImpl();

        while (!input.equals("Exit")) {
            String[] tokens = input.split("\\s+");
            String command = tokens[0];

            switch (command) {
                case "AddPlayer":
                    try {
                        System.out.println(managerController.addPlayer(tokens[1], tokens[2]));
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "AddCard":
                    try {
                        System.out.println(managerController.addCard(tokens[1], tokens[2]));
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "AddPlayerCard":
                    try {
                        System.out.println(managerController.addPlayerCard(tokens[1], tokens[2]));
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "Fight":
                    try {
                        System.out.println(managerController.fight(tokens[1], tokens[2]));
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "Report":
                    try {
                        System.out.println(managerController.report());
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
            }

            input = scanner.nextLine();
        }*/
    }
}
