package wildfarm;

import wildfarm.entities.animalmodels.*;
import wildfarm.entities.foodmodels.Food;
import wildfarm.entities.foodmodels.Meat;
import wildfarm.entities.foodmodels.Vegetable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        List<Animal> animals = new ArrayList<>();
        int counter = 0;

        while (!input.equals("End")) {
            if (counter % 2 == 0) {
                Animal animal = createAnimal(input.split("\\s+"));
                if (animal != null) {
                    animals.add(animal);
                }
            } else {
                Food food = createFood(input.split("\\s+"));
                if (food != null) {
                    animals.get(animals.size() - 1).makeSound();
                    animals.get(animals.size() - 1).eat(food);
                }
            }

            counter++;
            input = scanner.nextLine();
        }

        animals.forEach(System.out::println);

    }

    private static Animal createAnimal (String[] tokens) {
        String type = tokens[0];
        String name = tokens[1];
        double weight = Double.parseDouble(tokens[2]);
        String region = tokens[3];
        if (tokens.length == 5) {
            return new Cat(type, name, weight, region, tokens[4]);
        }
        switch (type) {
            case "Mouse":
                return new Mouse(type, name, weight, region);
            case "Tiger":
                return new Tiger(type, name, weight, region);
            case "Zebra":
                return new Zebra(type, name, weight, region);
        }
        return null;
    }

    private static Food createFood(String[] tokens) {
        String type = tokens[0];
        int quantity = Integer.parseInt(tokens[1]);
        switch (type) {
            case "Vegetable":
                return new Vegetable(quantity);
            case "Meat":
                return new Meat(quantity);
        }
        return null;
    }
}
