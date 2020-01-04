package vehicle;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Vehicle car = createVehicle(scanner.nextLine().split("\\s+"));
        Vehicle truck = createVehicle(scanner.nextLine().split("\\s+"));
        Bus bus = (Bus) createVehicle(scanner.nextLine().split("\\s+"));

        int n = Integer.parseInt(scanner.nextLine());

        while (n-- > 0) {
            String[] tokens = scanner.nextLine().split("\\s+");
            String command = tokens[0];
            String vehicle = tokens[1];
            double value = Double.parseDouble(tokens[2]);
            switch (command) {
                case "Drive":
                    switch (vehicle) {
                        case "Car":
                            car.drive(value);
                            break;
                        case "Truck":
                            truck.drive(value);
                            break;
                        case "Bus":
                            bus.withAC();
                            bus.drive(value);
                            break;
                    }
                    break;
                case "Refuel":
                    switch (vehicle) {
                        case "Car":
                            car.refuel(value);
                            break;
                        case "Truck":
                            truck.refuel(value);
                            break;
                        case "Bus":
                            bus.refuel(value);
                            break;
                    }
                    break;
                case "DriveEmpty":
                    bus.withoutAC();
                    bus.drive(value);
                    break;
            }
        }

        System.out.println(car.toString());
        System.out.println(truck.toString());
        System.out.println(bus.toString());

    }

    private static Vehicle createVehicle(String[] tokens) {
        String type = tokens[0];
        double fuelQuantity = Double.parseDouble(tokens[1]);
        double fuelConsumption = Double.parseDouble(tokens[2]);
        double tankCapacity = Double.parseDouble(tokens[3]);

        if (type.equals("Car")) {
            return new Car(fuelQuantity, fuelConsumption, tankCapacity);
        } else if (type.equals("Truck")) {
            return new Truck(fuelQuantity, fuelConsumption, tankCapacity);
        } else {
            return new Bus(fuelQuantity, fuelConsumption, tankCapacity);
        }
        //switch (type) {
        //    case "Car":
        //        return new Car(fuelQuantity, fuelConsumption, tankCapacity);
        //    case "Truck":
        //        return new Truck(fuelQuantity, fuelConsumption, tankCapacity);
        //    case "Bus":
        //        return new Bus(fuelConsumption, fuelConsumption, tankCapacity);
        //}
        //return null;
    }
}
