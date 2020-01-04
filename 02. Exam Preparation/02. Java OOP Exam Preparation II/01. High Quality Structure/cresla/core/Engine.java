package cresla.core;

import cresla.interfaces.InputReader;
import cresla.interfaces.Manager;
import cresla.interfaces.OutputWriter;
import cresla.io.ConsoleReader;
import cresla.io.ConsoleWriter;

import java.util.*;

public class Engine {
    private InputReader reader;
    private OutputWriter writer;
    private Manager manager;

    public Engine() {
        this.reader = new ConsoleReader();
        this.writer = new ConsoleWriter();
        this.manager = new ManagerImpl();
    }

    private List<String> splitInput(String text) {
        List<String> result;
        String[] temp = text.split("\\s+");
        result = Arrays.asList(temp);
        return result;
    }

    public void run() {
        String input = reader.readLine();

        while (input != null) {
            List<String> tokens = splitInput(input);
            String command = tokens.get(0);
            List<String> values = tokens.subList(1, tokens.size());

            switch (command){
                case "Reactor":
                    writer.writeLine(this.manager.reactorCommand(values));
                    break;
                case "Module":
                    writer.writeLine(this.manager.moduleCommand(values));
                    break;
                case "Report":
                    writer.writeLine(this.manager.reportCommand(values));
                    break;
                case "Exit":
                    writer.writeLine(this.manager.exitCommand(null));
                    return;
            }

            input = reader.readLine();
        }
    }
}
