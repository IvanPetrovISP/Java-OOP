package cresla.entities.reactors;

import cresla.entities.containers.ModuleContainer;
import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.EnergyModule;
import cresla.interfaces.Reactor;

public abstract class ReactorImpl implements Reactor {
    private ModuleContainer moduleContainer;
    private int id;

    protected ReactorImpl(int id, int capacity) {
        this.moduleContainer = new ModuleContainer(capacity);
        this.id = id;
    }

    @Override
    public long getTotalEnergyOutput() {
        return this.moduleContainer.getTotalEnergyOutput();
    }

    @Override
    public long getTotalHeatAbsorbing() {
        return this.moduleContainer.getTotalHeatAbsorbing();
    }

    @Override
    public int getModuleCount() {
        return this.moduleContainer.getModuleByInputCount();
    }

    @Override
    public void addEnergyModule(EnergyModule energyModule) {
        this.moduleContainer.addEnergyModule(energyModule);
    }

    @Override
    public void addAbsorbingModule(AbsorbingModule absorbingModule) {
        this.moduleContainer.addAbsorbingModule(absorbingModule);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format(" - %d", this.getId()));
        result.append(System.lineSeparator());
        result.append(String.format("Energy Output: %d", this.getTotalEnergyOutput()));
        result.append(System.lineSeparator());
        result.append(String.format("Heat Absorbing: %d", this.getTotalHeatAbsorbing()));
        result.append(System.lineSeparator());
        result.append(String.format("Modules: %d", this.getModuleCount()));

        return result.toString();
    }
}
