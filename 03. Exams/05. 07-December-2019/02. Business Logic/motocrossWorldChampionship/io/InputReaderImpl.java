package motocrossWorldChampionship.io;

import motocrossWorldChampionship.io.interfaces.InputReader;

import java.util.Scanner;

public class InputReaderImpl implements InputReader {
    private Scanner scanner;

    public InputReaderImpl() {
        this.scanner = new Scanner(System.in);
    }

    private boolean hasNextLine() {
        return this.scanner.hasNextLine();
    }

    @Override
    public String readLine() {
        if (hasNextLine()) {
            return scanner.nextLine();
        } else {
            return null;
        }
    }
}
