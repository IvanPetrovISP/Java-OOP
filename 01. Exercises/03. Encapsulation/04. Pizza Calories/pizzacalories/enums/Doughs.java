package pizzacalories.enums;

public enum Doughs {
    White(1.5),
    Wholegrain(1.0),
    Crispy(0.9),
    Chewy(1.1),
    Homemade(1.0);

    private final double modifier;

    Doughs(double modifier) {
        this.modifier = modifier;
    }

    public double getModifier() {
        return modifier;
    }

}
