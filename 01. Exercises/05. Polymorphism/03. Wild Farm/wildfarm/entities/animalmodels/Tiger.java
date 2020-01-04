package wildfarm.entities.animalmodels;

import wildfarm.entities.foodmodels.Food;

import java.text.DecimalFormat;

public class Tiger extends Feline {

    public Tiger(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }

    @Override
    public void eat(Food food) {
        if (food.getClass().getSimpleName().equals("Meat")) {
            this.setFood(food.getQuantity());
        } else {
            System.out.println(String.format("%ss are not eating that type of food!",
                    this.getClass().getSimpleName()));
        }
    }

}
