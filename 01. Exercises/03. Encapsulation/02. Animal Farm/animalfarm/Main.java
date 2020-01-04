package animalfarm;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        Chicken chicken = null;

        String name = scanner.nextLine();
        int age = Integer.parseInt(scanner.nextLine());

        try {
            chicken = new Chicken(name, age);
        } catch (IllegalArgumentException exception){
            System.out.println(exception.getMessage());
        } finally {
            if (chicken != null){
                System.out.println(chicken.toString());
            }
        }

    }
}
