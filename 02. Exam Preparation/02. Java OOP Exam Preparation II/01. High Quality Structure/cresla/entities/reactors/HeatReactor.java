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
        long result = super.getTotalHeatAbsorbing() + this.heatReductionIndex;
        if (super.getTotalEnergyOutput() > result) {
            return 0;
        }//fixme
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(REACTOR_TYPE).append(super.toString());
        return result.toString();
    }
}
