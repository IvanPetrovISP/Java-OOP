package motocrossWorldChampionship.entities;

import motocrossWorldChampionship.entities.interfaces.Motorcycle;

import static motocrossWorldChampionship.common.ExceptionMessages.*;

public abstract class MotorcycleImpl implements Motorcycle {
    private static final int MIN_NAME_LENGTH = 4;
    private String model;
    private int horsePower;
    private double cubicCentimeters;

    protected MotorcycleImpl(String model, int horsePower, double cubicCentimeters) {
        setModel(model);
        setHorsePower(horsePower);
        setCubicCentimeters(cubicCentimeters);
    }

    private void setModel(String model) {
        if (model==null || model.trim().isEmpty() || model.length() < MIN_NAME_LENGTH) {
            throw new IllegalArgumentException(String.format(INVALID_MODEL, model, MIN_NAME_LENGTH));
        }
        this.model = model;
    }

    private void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    private void setCubicCentimeters(double cubicCentimeters) {
        this.cubicCentimeters = cubicCentimeters;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public int getHorsePower() {
        return this.horsePower;
    }

    @Override
    public double getCubicCentimeters() {
        return this.cubicCentimeters;
    }

    @Override
    public double calculateRacePoints(int laps) {
        return this.getCubicCentimeters() / this.getHorsePower() * laps;
    }
}
