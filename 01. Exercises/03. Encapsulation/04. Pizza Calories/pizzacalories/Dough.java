package pizzacalories;

import pizzacalories.enums.Doughs;

public class Dough {
    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        setFlourType(flourType);
        setBakingTechnique(bakingTechnique);
        setWeight(weight);
    }

    private String getFlourType() {
        return this.flourType;
    }

    private String getBakingTechnique() {
        return this.bakingTechnique;
    }

    private double getWeight() {
        return this.weight;
    }

    private void setFlourType(String flourType) {
        try {
            Doughs.valueOf(flourType);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.flourType = flourType;
    }

    private void setBakingTechnique(String bakingTechnique) {
        try {
            Doughs.valueOf(bakingTechnique);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Invalid type of dough.");
        }
        this.bakingTechnique = bakingTechnique;
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 200){
            throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
        }
        this.weight = weight;
    }

    private double setModifier(String string) {
        return Doughs.valueOf(string).getModifier();
    }

    public double calculateCalories() {
        double flourModifier = setModifier(getFlourType());
        double techniqueModifier = setModifier(getBakingTechnique());
        double modifier = flourModifier * techniqueModifier;

        return (2 * getWeight()) * modifier;
    }
}
