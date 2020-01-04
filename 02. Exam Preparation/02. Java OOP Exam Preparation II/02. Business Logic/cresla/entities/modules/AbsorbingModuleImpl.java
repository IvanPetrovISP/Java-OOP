package cresla.entities.modules;

import cresla.interfaces.AbsorbingModule;

public abstract class AbsorbingModuleImpl extends ModuleImpl implements AbsorbingModule {
    private int heatAbsorbing;

    protected AbsorbingModuleImpl(int id, int property) {
        super(id);
        this.heatAbsorbing = property;
    }

    @Override
    public int getHeatAbsorbing() {
        return this.heatAbsorbing;
    }

}
