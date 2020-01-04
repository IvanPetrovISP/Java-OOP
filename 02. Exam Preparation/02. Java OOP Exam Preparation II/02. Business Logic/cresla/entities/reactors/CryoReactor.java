package cresla.entities.reactors;

public class CryoReactor extends ReactorImpl {
    private static final String REACTOR_TYPE = "CryoReactor";
    private int cryoProductionIndex;

    public CryoReactor(int id, int specialIndex, int capacity) {
        super(id, capacity);
        this.cryoProductionIndex = specialIndex;
    }

    @Override
    public long getTotalEnergyOutput() {
        long result = super.getTotalEnergyOutput() * this.cryoProductionIndex;
        if (result > super.getTotalHeatAbsorbing()) {
            return 0;
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(REACTOR_TYPE).append(super.toString());
        return result.toString();
    }
}
