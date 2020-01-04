package vehicles;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Vehicle car = createVehicle(scanner);
        Vehicle truck = createVehicle(scanner);

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String command = tokens[0];
            String vehicle = tokens[1];
            Double quantity = Double.parseDouble(tokens[2]);

            switch (command) {
                case "Drive":
                    switch (vehicle) {
                        case "Car":
                            System.out.println(car.drive(quantity));
                            break;
                        case "Truck":
                            System.out.println(truck.drive(quantity));
                            break;
                    }
                    break;
                case "Refuel":
                    switch (vehicle) {
                        case "Car":
                            car.refuel(quantity);
                            break;
                        case "Truck":
                            truck.refuel(quantity);
                            break;
                    }
                    break;
            }
        }

        System.out.println(car.toString());
        System.out.println(truck.toString());
    }

    private static Vehicle createVehicle(Scanner scanner) {
        String[] vehicleInfo = scanner.nextLine().split("\\s+");

        String type = vehicleInfo[0];
        double fuelQuantity = Double.parseDouble(vehicleInfo[1]);
        double fuelConsumption = Double.parseDouble(vehicleInfo[2]);

        switch (type) {
            case "Car":
                return new Car(fuelQuantity, fuelConsumption);
            case "Truck":
                return new Truck(fuelQuantity, fuelConsumption);
        }

        return null;
    }
}
