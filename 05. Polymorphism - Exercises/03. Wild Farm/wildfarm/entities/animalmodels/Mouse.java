package wildfarm.entities.animalmodels;

import wildfarm.entities.foodmodels.Food;

public class Mouse extends Mammal {

    public Mouse(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("SQUEEEAAAK!");
    }

    @Override
    public void eat(Food food) {
        if (food.getClass().getSimpleName().equals("Vegetable")) {
            this.setFood(food.getQuantity());
        } else {
            System.out.println("Mice are not eating that type of food!");
        }
    }
}
