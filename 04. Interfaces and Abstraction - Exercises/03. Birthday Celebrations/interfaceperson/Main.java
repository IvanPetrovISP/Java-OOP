package interfaceperson;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> birthDates = new ArrayList<>();

        String line = scanner.nextLine();
        while (!line.equals("End")) {
            String[] tokens = line.split("\\s+");
            switch (tokens[0]){
                case "Citizen":
                    birthDates.add(tokens[4]);
                    break;
               //case "Robot":
               //    break;
                case "Pet":
                    birthDates.add(tokens[2]);
                    break;
            }

            line = scanner.nextLine();
        }
        String birthDateToPrint = scanner.nextLine();

        birthDates.stream()
                .filter(s -> s.endsWith(birthDateToPrint))
                .forEach(System.out::println);
    }
}