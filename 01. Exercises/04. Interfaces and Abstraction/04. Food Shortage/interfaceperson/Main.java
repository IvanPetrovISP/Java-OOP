package interfaceperson;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Citizen> citizens = new ArrayList<>();
        List<Rebel> rebels = new ArrayList<>();

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] arr = scanner.nextLine().split("\\s+");
            if (arr.length == 4) {
                citizens.add(new Citizen(arr[0],
                        Integer.parseInt(arr[1]),
                        arr[2],
                        arr[3]));
            } else {
                rebels.add(new Rebel(arr[0],
                        Integer.parseInt(arr[1]),
                        arr[2]));
            }
        }

        String line = scanner.nextLine();
        while (!line.equals("End")) {
            boolean isCitizen = false;
            for (Citizen citizen : citizens) {
                if (citizen.getName().equals(line)) {
                    citizen.buyFood();
                    isCitizen = true;
                    break;
                }
            }
            if (!isCitizen) {
                for (Rebel rebel : rebels) {
                    if (rebel.getName().equals(line)) {
                        rebel.buyFood();
                        break;
                    }
                }
            }

            line = scanner.nextLine();
        }

        int citizensSum = citizens.stream()
                .mapToInt(Citizen::getFood).sum();
        int rebelsSum = rebels.stream()
                .mapToInt(Rebel::getFood).sum();

        System.out.println(citizensSum + rebelsSum);
    }
}