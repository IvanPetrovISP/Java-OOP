package motocrossWorldChampionship.core;

import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.core.interfaces.Engine;
import motocrossWorldChampionship.io.ReaderImpl;
import motocrossWorldChampionship.io.WriterImpl;
import motocrossWorldChampionship.io.interfaces.InputReader;
import motocrossWorldChampionship.io.interfaces.OutputWriter;

import java.io.IOException;
import java.util.Scanner;

public class EngineImpl implements Engine {
    private InputReader reader;
    private OutputWriter writer;
    private ChampionshipController controller;

    public EngineImpl() {
        this.reader = new ReaderImpl();
        this.writer = new WriterImpl();
        this.controller = new ChampionshipControllerImpl();
    }

    @Override
    public void run() {

        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!input.equals("End")) {
            String[] tokens = input.split("\\s+");
            String command = tokens[0];

            try {
                switch (command) {
                    case "CreateRider":
                        writer.writeLine(controller
                                .createRider(tokens[1]));
                        break;
                    case "CreateMotorcycle":
                        writer.writeLine(controller
                                .createMotorcycle(tokens[1], tokens[2], Integer.parseInt(tokens[3])));
                        break;
                    case "AddMotorcycleToRider":
                        writer.writeLine(controller
                                .addMotorcycleToRider(tokens[1], tokens[2]));
                        break;
                    case "AddRiderToRace":
                        writer.writeLine(controller
                                .addRiderToRace(tokens[1], tokens[2]));
                        break;
                    case "CreateRace":
                        writer.writeLine(controller
                                .createRace(tokens[1], Integer.parseInt(tokens[2])));
                        break;
                    case "StartRace":
                        writer.writeLine(controller.startRace(tokens[1]));
                        break;
                }
            } catch (IllegalArgumentException | NullPointerException e) {
                writer.writeLine(e.getMessage());
            }

            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
