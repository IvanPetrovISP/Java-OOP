package animalfarm;

public class Chicken {
    private String name;
    private int age;
    private int LIFESPAN = 15;

    public Chicken(String name, int age) {
        setName(name);
        setAge(age);
    }

    private String getName() {
        return name;
    }

    private int getAge() {
        return age;
    }

    private void setName(String name) {
        if (name.length() < 1){
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }


    private void setAge(int age) {
        if (age < 0 || age > LIFESPAN){
            throw new IllegalArgumentException("Age should be between 0 and 15.");
        }
        this.age = age;
    }

    public double productPerDay() {
        return this.calculateProductPerDay();
    }


    private double calculateProductPerDay() {
        double result = 0;

        if (this.getAge() >= 12){
            result = 0.75;
        } else if (this.getAge() >= 6){
            result = 1.00;
        } else {
            result = 2.00;
        }

        return result;
    }

    @Override
    public String toString() {
        return String.format("Chicken %s (age %d) can produce %.2f eggs per day.",
                getName(),
                getAge(),
                productPerDay());
    }
}
