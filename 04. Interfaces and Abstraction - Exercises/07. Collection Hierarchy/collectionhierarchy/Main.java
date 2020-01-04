package collectionhierarchy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    //static AddCollection add;
    //static AddRemoveCollection addRemove;
    //static MyListImpl myList;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //String input = scanner.nextLine();
        List<String> list = Arrays.stream(scanner.nextLine().split("\\s+"))
                .collect(Collectors.toList());

        AddCollection add = new AddCollection();
        AddRemoveCollection addRemove = new AddRemoveCollection();
        MyListImpl myList = new MyListImpl();

        try {
            for (String s : list) {
                System.out.print(add.add(s) + " ");
            }
            System.out.println();
            for (String s : list) {
                System.out.print(addRemove.add(s) + " ");
            }
            System.out.println();
            for (String s : list) {
                System.out.print(myList.add(s) + " ");
            }
        } catch (IllegalArgumentException ignored) {
        }

        int number = Integer.parseInt(scanner.nextLine());

        System.out.println();
        try {
            for (int i = 0; i < number; i++) {
                System.out.print(addRemove.remove() + " ");
            }
            System.out.println();
            for (int i = 0; i < number; i++) {
                System.out.print(myList.remove() + " ");
            }
        } catch (IllegalArgumentException ignored) {
        }

    }
}
