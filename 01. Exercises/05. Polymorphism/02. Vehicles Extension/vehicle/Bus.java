package vehicle;

public class Bus extends Vehicle {
    boolean isEmpty = true;

    public Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
        super(fuelQuantity, fuelConsumption, tankCapacity, 0.0, 1.0);
    }

    public void withAC() {
        if (isEmpty) {
            this.setSUMMER_CONSUMPTION_MODIFIER(1.4);
            this.setFuelConsumption(this.getFuelConsumption());
            isEmpty = !isEmpty;
        }
    }

    public void withoutAC() {
        if (!isEmpty) {
            this.setFuelConsumption(this.getFuelConsumption() - this.getSUMMER_CONSUMPTION_MODIFIER());
            this.setSUMMER_CONSUMPTION_MODIFIER(0.0);
            isEmpty = !isEmpty;
        }
    }

}
