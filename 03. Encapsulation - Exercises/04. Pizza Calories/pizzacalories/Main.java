package pizzacalories;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Pizza pizza = null;
    private static Scanner scanner;

    public static void main(String[] args) throws IOException {
        scanner = new Scanner(System.in);

        try {
            makePizza();
            System.out.println(pizza.toString());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }

    }

    private static void makePizza() throws IOException {
        validatePizza();
        validateDough();
        validateToppings();
    }

    private static void validatePizza() throws IOException {
        String[] tokens = scanner.nextLine().split("\\s+");
        String name = tokens[1];
        int numberOfToppings = Integer.parseInt(tokens[2]);

        pizza = new Pizza(name, numberOfToppings);
    }

    private static void validateDough() throws IOException {
        String[] tokens = scanner.nextLine().split("\\s+");
        String flour = tokens[1];
        String technique = tokens[2];
        double weight = Double.parseDouble(tokens[3]);

        pizza.setDough(new Dough(flour, technique, weight));
    }

    private static void validateToppings() throws IOException {
        String command = scanner.nextLine();
        while (!command.equals("END")){
            String[] tokens = command.split("\\s+");
            String type = tokens[1];
            double weight = Double.parseDouble(tokens[2]);

            pizza.addTopping(new Topping(type, weight));
            command = scanner.nextLine();
        }
    }
}
