package motocrossWorldChampionship.io;

import motocrossWorldChampionship.io.interfaces.InputReader;

import java.io.IOException;
import java.util.Scanner;

public class ReaderImpl implements InputReader {
    Scanner scanner = new Scanner(System.in);
    @Override
    public String readLine() throws IOException {
        return scanner.nextLine();
    }
}
