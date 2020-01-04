package pizzacalories;

import java.util.ArrayList;

public class Pizza {
    private String name;
    private Dough dough;
    private ArrayList<Topping> toppings;


    public Pizza(String name, int numberOfToppings) {
        setName(name);
        setToppings(numberOfToppings);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        if (name.isEmpty() || name.equals("\\s+") || name.length() > 15){
            throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
        }
        this.name = name;
    }

    private Dough getDough() {
        return this.dough;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    private void setToppings(int size) {
        if (size < 1 || size > 10){
            throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
        }
        this.toppings = new ArrayList<>(size);
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public double getOverallCalories() {
        return this.dough.calculateCalories() + this.toppings.stream().mapToDouble(Topping::calculateCalories).sum();
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f", getName(), getOverallCalories());
    }
}
