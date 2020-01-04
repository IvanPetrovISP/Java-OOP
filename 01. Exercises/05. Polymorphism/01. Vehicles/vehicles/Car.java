package vehicles;

public class Car extends Vehicle {
    private final double consumptionModifier = 0.9;
    private final double refuelModifier = 1.0;

    public Car(double fuelQuantity, double fuelConsumption) {
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
