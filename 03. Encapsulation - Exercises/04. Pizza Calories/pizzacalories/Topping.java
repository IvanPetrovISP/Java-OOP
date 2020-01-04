package pizzacalories;

import pizzacalories.enums.Doughs;
import pizzacalories.enums.Toppings;

public class Topping {
    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        setToppingType(toppingType);
        setWeight(weight);
    }

    private String getToppingType() {
        return this.toppingType;
    }

    private double getWeight() {
        return this.weight;
    }

    private void setToppingType(String toppingType) {
        try {
            Toppings.valueOf(toppingType);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(String.format("Cannot place %s on top of your pizza.", getToppingType()));
        }
        this.toppingType = toppingType;
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 50){
            throw new IllegalArgumentException(String.format("%s weight should be in the range [1..50].", getToppingType()));
        }
        this.weight = weight;
    }

    private double setModifier(String string) {
        return Doughs.valueOf(string).getModifier();
    }

    public double calculateCalories() {
        double modifier = Toppings.valueOf(this.toppingType).getModifier();
        return (2 * this.weight) * modifier;
    }
}
