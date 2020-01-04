package vehicles;

public class Truck extends Vehicle {
    private final double consumptionModifier = 1.6;
    private final double refuelModifier = 0.95;

    public Truck(double fuelQuantity, double fuelConsumption) {
        super(fuelQuantity, fuelConsumption);
        setFuelConsumption(fuelConsumption);
    }

    @Override
    void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption + consumptionModifier;
    }

    @Override
    double setRefuel(double fuel) {
        return fuel * refuelModifier;
    }
}
