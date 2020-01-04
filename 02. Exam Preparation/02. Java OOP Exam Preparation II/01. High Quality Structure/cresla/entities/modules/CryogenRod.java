package cresla.entities.modules;

public class CryogenRod extends EnergyModuleImpl {
    private static final String MODULE_TYPE = "CryogenRod";
    private static final String PARAMETER_TYPE = "Energy Output";

    public CryogenRod(int id, int property) {
        super(id, property);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(MODULE_TYPE).append(super.toString());
        result.append(System.lineSeparator());
        result.append(String.format("%s: %d", PARAMETER_TYPE, this.getEnergyOutput()));

        return result.toString();
    }
}
