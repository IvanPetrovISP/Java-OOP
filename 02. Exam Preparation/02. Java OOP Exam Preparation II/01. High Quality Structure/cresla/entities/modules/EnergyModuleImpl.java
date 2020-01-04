package cresla.entities.modules;

import cresla.interfaces.EnergyModule;

public abstract class EnergyModuleImpl extends ModuleImpl implements EnergyModule {
    private int energyOutput;

    protected EnergyModuleImpl(int id, int property) {
        super(id);
        this.energyOutput = property;
    }

    @Override
    public int getEnergyOutput() {
        return this.energyOutput;
    }
}
