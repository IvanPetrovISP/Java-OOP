package motocrossWorldChampionship.core;

import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.core.interfaces.Engine;
import motocrossWorldChampionship.io.InputReaderImpl;
import motocrossWorldChampionship.io.OutputWriterImpl;
import motocrossWorldChampionship.io.interfaces.InputReader;
import motocrossWorldChampionship.io.interfaces.OutputWriter;

import java.io.IOException;
import java.util.Scanner;

public class EngineImpl implements Engine {
    private Scanner scanner;
    private ChampionshipController controller;

    public EngineImpl(ChampionshipController controller) {
        this.scanner = new Scanner(System.in);
        this.controller = controller;
    }


    @Override
    public void run() {

        String input = scanner.nextLine();

        while (!input.equals("End")) {
            String[] tokens = input.split("\\s+");
            String command = tokens[0];

            try {
                switch (command) {
                    case "CreateRider":
                        System.out.println(controller
                                .createRider(tokens[1]));
                        break;
                    case "CreateMotorcycle":
                        System.out.println(controller
                                .createMotorcycle(tokens[1], tokens[2], Integer.parseInt(tokens[3])));
                        break;
                    case "AddMotorcycleToRider":
                        System.out.println(controller
                                .addMotorcycleToRider(tokens[1], tokens[2]));
                        break;
                    case "AddRiderToRace":
                        System.out.println(controller
                                .addRiderToRace(tokens[1], tokens[2]));
                        break;
                    case "CreateRace":
                        System.out.println(controller
                                .createRace(tokens[1], Integer.parseInt(tokens[2])));
                        break;
                    case "StartRace":
                        System.out.println(controller.startRace(tokens[1]));
                        break;
                }
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e.getMessage());
            }


            input = scanner.nextLine();
        }
    }
}

