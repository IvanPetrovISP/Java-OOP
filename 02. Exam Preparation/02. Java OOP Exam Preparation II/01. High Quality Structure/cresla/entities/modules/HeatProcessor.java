package cresla.entities.modules;

import cresla.interfaces.AbsorbingModule;

public class HeatProcessor extends AbsorbingModuleImpl {
    private static final String MODULE_TYPE = "HeatProcessor";
    private static final String PARAMETER_TYPE = "Heat Absorbing";

    public HeatProcessor(int id, int property) {
        super(id, property);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(MODULE_TYPE).append(super.toString());
        result.append(System.lineSeparator());
        result.append(String.format("%s: %d", PARAMETER_TYPE, this.getHeatAbsorbing()));

        return result.toString();
    }
}
