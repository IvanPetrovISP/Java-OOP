package wildfarm.entities.animalmodels;

import wildfarm.entities.foodmodels.Food;

public class Zebra extends Mammal {

    public Zebra(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("Zs");
    }

    @Override
    public void eat(Food food) {
        if (food.getClass().getSimpleName().equals("Vegetable")) {
            this.setFood(food.getQuantity());
        } else {
            System.out.println(String.format("%ss are not eating that type of food!",
                    this.getClass().getSimpleName()));
        }
    }
}
