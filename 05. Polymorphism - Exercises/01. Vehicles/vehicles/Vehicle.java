package vehicles;

import java.text.DecimalFormat;

public abstract class Vehicle {
    protected double fuelQuantity;
    protected double fuelConsumption;

    protected Vehicle(double fuelQuantity, double fuelConsumption) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
    }

    abstract void setFuelConsumption(double fuelConsumption);
    abstract double setRefuel(double fuel);

    public void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public double getFuelQuantity() {
        return this.fuelQuantity;
    }

    public String drive(double distance) {
        double fuelNeeded = this.fuelConsumption * distance;
        if (fuelNeeded > this.fuelQuantity) {
            return String.format("%s needs refueling",
                    this.getClass().getSimpleName());
        }

        this.setFuelQuantity(getFuelQuantity() - fuelNeeded);
        DecimalFormat display = new DecimalFormat("#.##");
        return String.format("%s travelled %s km",
                this.getClass().getSimpleName(),
                display.format(distance));

    }

    public void refuel(double fuel) {
        //setRefuel(fuel);
        this.setFuelQuantity(getFuelQuantity() + setRefuel(fuel));
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f",
                this.getClass().getSimpleName(),
                this.getFuelQuantity());
    }
}
