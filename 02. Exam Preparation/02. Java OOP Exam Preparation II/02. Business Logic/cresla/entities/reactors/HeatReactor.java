package cresla.entities.reactors;

public class HeatReactor extends ReactorImpl {
    private static final String REACTOR_TYPE = "HeatReactor";
    private int heatReductionIndex;

    public HeatReactor(int id, int specialIndex, int capacity) {
        super(id, capacity);
        this.heatReductionIndex = specialIndex;
    }

    @Override
    public long getTotalHeatAbsorbing() {
        return super.getTotalHeatAbsorbing() + this.heatReductionIndex;
    }

    @Override
    public long getTotalEnergyOutput() {
        if (super.getTotalEnergyOutput() > this.getTotalHeatAbsorbing()) {
            return 0;
        }
        return super.getTotalEnergyOutput();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(REACTOR_TYPE).append(super.toString());
        return result.toString();
    }
}
