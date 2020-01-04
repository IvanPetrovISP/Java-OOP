package entities;

import entities.interfaces.Fighter;

public class FighterImpl extends BaseMachine implements Fighter {
    private static final double INITIAL_HEALTHPOINTS = 200;
    private static final double ATTACKPOINTS_MODIFIER = 50;
    private static final double DEFENSEPOINTS_MODIFIER = 25;

    private static final String TYPE = "Fighter";

    private boolean aggressiveMode = false;

    public FighterImpl(String name, double attackPoints, double defensePoints) {
        super(name, attackPoints, defensePoints, INITIAL_HEALTHPOINTS);
        toggleAggressiveMode();
    }

    @Override
    public boolean getAggressiveMode() {
        return this.aggressiveMode;
    }

    @Override
    public void toggleAggressiveMode() {
        this.aggressiveMode = !this.aggressiveMode;
        if (this.aggressiveMode) {
            setAttackPoints(getAttackPoints() + ATTACKPOINTS_MODIFIER);
            setDefensePoints(getDefensePoints() - DEFENSEPOINTS_MODIFIER);
        } else {
            setAttackPoints(getAttackPoints() - ATTACKPOINTS_MODIFIER);
            setDefensePoints(getDefensePoints() + DEFENSEPOINTS_MODIFIER);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("- %s", this.getName()));
        result.append(System.lineSeparator());
        result.append(String.format(" *Type: %s", TYPE));
        result.append(System.lineSeparator());
        result.append(super.toString());
        result.append(System.lineSeparator());
        result.append(String.format(" *Aggressive Mode(%s)", this.getAggressiveMode() ? "ON" : "OFF"));
        return result.toString();
    }
}
