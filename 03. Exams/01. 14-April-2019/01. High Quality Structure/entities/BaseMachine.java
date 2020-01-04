package entities;

import entities.interfaces.Machine;
import entities.interfaces.Pilot;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMachine implements Machine {
    private String name;
    private Pilot pilot;
    private double healthPoints;
    private double attackPoints;
    private double defensePoints;
    private List<String> targets;

    protected BaseMachine(String name, double attackPoints, double defensePoints, double healthPoints) {
        setName(name);
        this.attackPoints = attackPoints;
        this.defensePoints = defensePoints;
        setHealthPoints(healthPoints);
        this.targets = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Machine name cannot be null or empty.");
        }
        this.name = name;
    }

    @Override
    public Pilot getPilot() {
        return this.pilot;
    }

    @Override
    public void setPilot(Pilot pilot) {
        if (pilot == null) {
            throw new NullPointerException("Pilot cannot be null.");
        }
        this.pilot = pilot;
    }

    @Override
    public double getHealthPoints() {
        return this.healthPoints;
    }

    @Override
    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
    }

    @Override
    public double getAttackPoints() {
        return this.attackPoints;
    }

    @Override
    public double getDefensePoints() {
        return this.defensePoints;
    }

    @Override
    public List<String> getTargets() {
        return this.targets;//todo unmodifiable list?
    }

    @Override
    public void attack(String target) {
        if (target == null || target.isEmpty()) {
            throw new IllegalArgumentException("Attack target cannot be null or empty string.");
        }

        this.targets.add(target);
    }

    /////////////////////
    protected void setAttackPoints(double attackPoints) {
        this.attackPoints = attackPoints;
    }

    protected void setDefensePoints(double defensePoints) {
        this.defensePoints = defensePoints;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format(" *Health: %.2f", this.getHealthPoints()));
        result.append(System.lineSeparator());
        result.append(String.format(" *Attack: %.2f", this.getAttackPoints()));
        result.append(System.lineSeparator());
        result.append(String.format(" *Defense: %.2f", this.getDefensePoints()));
        result.append(System.lineSeparator());
        result.append(" *Targets: ");
        if (this.targets.size() == 0) {
            result.append("None");
        } else {
            for (int i = 0; i < this.targets.size(); i++) {
                if (i == this.targets.size() - 1) {
                    result.append(this.targets.get(i));
                } else {
                    result.append(String.format("%s, ", targets.get(i)));
                }
            }
        }

        return result.toString();
    }
}
