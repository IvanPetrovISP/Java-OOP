package vehicle;

import java.text.DecimalFormat;

public abstract class Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;
    private double tankCapacity;
    private double SUMMER_CONSUMPTION_MODIFIER;
    private double TANK_LEAK_MODIFIER;

    public Vehicle(double fuelQuantity, double fuelConsumption, double tankCapacity,
                   double SUMMER_CONSUMPTION_MODIFIER, double TANK_LEAK_MODIFIER) {
        this.setFuelQuantity(fuelQuantity);
        this.setFuelConsumption(fuelConsumption);
        this.setTankCapacity(tankCapacity);
        this.setSUMMER_CONSUMPTION_MODIFIER(SUMMER_CONSUMPTION_MODIFIER);
        this.setTANK_LEAK_MODIFIER(TANK_LEAK_MODIFIER);
    }

    public double getFuelQuantity() {
        return this.fuelQuantity;
    }

    public double getFuelConsumption() {
        return this.fuelConsumption;
    }

    public double getTankCapacity() {
        return this.tankCapacity;
    }

    public double getSUMMER_CONSUMPTION_MODIFIER() {
        return this.SUMMER_CONSUMPTION_MODIFIER;
    }

    public void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption + this.getSUMMER_CONSUMPTION_MODIFIER();
    }

    public void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    public void setSUMMER_CONSUMPTION_MODIFIER(double SUMMER_CONSUMPTION_MODIFIER) {
        this.SUMMER_CONSUMPTION_MODIFIER = SUMMER_CONSUMPTION_MODIFIER;
    }

    public void setTANK_LEAK_MODIFIER(double TANK_LEAK_MODIFIER) {
        this.TANK_LEAK_MODIFIER = TANK_LEAK_MODIFIER;
    }

    private String formatNumber(double number) {
        DecimalFormat display = new DecimalFormat("#.##");
        return display.format(number);
    }

    public void drive(double distance) {
        double fuelRequired = distance * this.getFuelConsumption();
        if (this.getFuelQuantity() == 0){
            System.out.println(String.format("%s needs refueling", this.getClass().getSimpleName()));
        } else {
            if (fuelRequired <= this.getFuelQuantity()) {
                this.setFuelQuantity(this.getFuelQuantity() - fuelRequired);
                System.out.println(String.format("%s travelled %s km",
                        this.getClass().getSimpleName(),
                        formatNumber(distance)));
            } else {
                System.out.println(String.format("%s needs refueling", this.getClass().getSimpleName()));
            }
        }
    }

    public void refuel(double fuel) {
        if (fuel <= 0) {
            System.out.println("Fuel must be a positive number");
        } else {
            if ((this.getFuelQuantity() + fuel) > this.getTankCapacity()) {
                System.out.println("Cannot fit fuel in tank");
            } else {
                this.setFuelQuantity(this.getFuelQuantity() + (fuel * TANK_LEAK_MODIFIER));
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f",
                this.getClass().getSimpleName(),
                this.getFuelQuantity());
    }
}
