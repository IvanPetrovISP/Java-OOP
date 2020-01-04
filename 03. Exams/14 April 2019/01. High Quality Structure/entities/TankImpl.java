package entities;

import entities.interfaces.Tank;

public class TankImpl extends BaseMachine implements Tank {
    private static final double INITIAL_HEALTHPOINTS = 100;
    private static final double ATTACKPOINTS_MODIFIER = 40;
    private static final double DEFENSEPOINTS_MODIFIER = 30;

    private static final String TYPE = "Tank";

    private boolean defenseMode = false;

    public TankImpl(String name, double attackPoints, double defensePoints) {
        super(name, attackPoints, defensePoints, INITIAL_HEALTHPOINTS);
        toggleDefenseMode();
    }

    @Override
    public boolean getDefenseMode() {
        return this.defenseMode;
    }

    @Override
    public void toggleDefenseMode() {
        this.defenseMode = !this.defenseMode;
        if (this.defenseMode) {
            setAttackPoints(getAttackPoints() - ATTACKPOINTS_MODIFIER);
            setDefensePoints(getDefensePoints() + DEFENSEPOINTS_MODIFIER);
        } else {
            setAttackPoints(getAttackPoints() + ATTACKPOINTS_MODIFIER);
            setDefensePoints(getDefensePoints() - DEFENSEPOINTS_MODIFIER);
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
        result.append(String.format(" *Defense Mode(%s)", this.getDefenseMode() ? "ON" : "OFF"));
        return result.toString();
    }
}
