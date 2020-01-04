package wildfarm.entities.animalmodels;

import wildfarm.entities.foodmodels.Food;

import java.text.DecimalFormat;

public class Cat extends Feline {
    private String breed;

    public Cat(String animalName, String animalType, Double animalWeight,
               String livingRegion, String breed) {
        super(animalName, animalType, animalWeight, livingRegion);
        this.breed = breed;
    }

    public String getBreed() {
        return this.breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    public void eat(Food food) {
        this.setFood(food.getQuantity());
    }

    @Override
    public String toString() {
        DecimalFormat display = new DecimalFormat("#.##");
        return String.format("%s[%s, %s, %s, %s, %d]",
                this.getClass().getSimpleName(),
                this.getAnimalName(),
                this.getBreed(),
                display.format(this.getAnimalWeight()),
                this.getLivingRegion(),
                this.getFoodEaten());
    }
}
