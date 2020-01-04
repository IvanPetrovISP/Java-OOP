package cresla.io;

import cresla.interfaces.InputReader;

import java.util.Scanner;

public class ConsoleReader implements InputReader {
    private Scanner scanner;

    public ConsoleReader() {
        this.scanner = new Scanner(System.in);
    }

    private boolean hasInput() {
        return this.scanner.hasNextLine();
    }

    @Override
    public String readLine() {
        if (hasInput()) {
            return scanner.nextLine();
        } else {
            return null;
        }
    }
}
