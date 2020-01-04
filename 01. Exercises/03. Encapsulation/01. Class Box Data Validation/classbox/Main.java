package classbox;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Box box = null;

        int length = Integer.parseInt(scanner.nextLine());
        int width = Integer.parseInt(scanner.nextLine());
        int height = Integer.parseInt(scanner.nextLine());

        try {
            box = new Box(length, width, height);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        } finally {
            if (box != null) {
                System.out.println(box.toString());
            }
        }


    }
}
