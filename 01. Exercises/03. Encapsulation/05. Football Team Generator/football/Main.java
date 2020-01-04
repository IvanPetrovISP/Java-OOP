package football;

import java.util.*;

public class Main {
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        Map<String, Team> teams = new LinkedHashMap<>();

        String input = scanner.nextLine();
        while (!input.equals("END")) {
            String[] tokens = input.split(";");
            String command = tokens[0];
            String name = tokens[1];
            switch (command) {
                case "Team":
                    try {
                        teams.putIfAbsent(name, new Team(name));
                    } catch (IllegalArgumentException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case "Add":
                    if (!teams.containsKey(name)) {
                        System.out.printf("Team %s does not exist.%n", name);
                        break;
                    }

                    try {
                        Player player = new Player(tokens[2],
                                parseInt(tokens[3]),
                                parseInt(tokens[4]),
                                parseInt(tokens[5]),
                                parseInt(tokens[6]),
                                parseInt(tokens[7]));
                        teams.get(name).addPlayer(player);
                    } catch (IllegalArgumentException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case "Remove":
                    if (!teams.containsKey(name)) {
                        System.out.printf("Team %s does not exist.%n", name);
                        break;
                    }

                    try {
                        teams.get(name).removePlayer(tokens[2]);
                    } catch (IllegalArgumentException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case "Rating":
                    if (!teams.containsKey(name)) {
                        System.out.printf("Team %s does not exist.%n", name);
                        break;
                    }
                    try {
                        int rating = (int)teams.get(name).getRating();
                        System.out.printf("%s - %d%n", name, rating);
                    } catch (IllegalArgumentException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
            }

            input = scanner.nextLine();
        }
    }

    private static int parseInt(String value) {
        return Integer.parseInt(value);
    }
}
