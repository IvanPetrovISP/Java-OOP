package cresla.core;

import cresla.entities.modules.CooldownSystem;
import cresla.entities.modules.CryogenRod;
import cresla.entities.modules.HeatProcessor;
import cresla.entities.reactors.CryoReactor;
import cresla.entities.reactors.HeatReactor;
import cresla.interfaces.AbsorbingModule;
import cresla.interfaces.EnergyModule;
import cresla.interfaces.Manager;
import cresla.interfaces.Reactor;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static cresla.common.OutputMessages.*;

public class ManagerImpl implements Manager {
    private static final int INITIAL_ID = 1;
    private int id;
    private Map<Integer, Reactor> reactors;
    private Map<Integer, Object> modules;
    private int cryoReactors;
    private int heatReactors;
    private int energyModules;
    private int absorbingModules;

    public ManagerImpl() {
        this.id = INITIAL_ID;
        this.reactors = new LinkedHashMap<>();
        this.modules = new LinkedHashMap<>();
        this.cryoReactors = 0;
        this.heatReactors = 0;
        this.energyModules = 0;
        this.absorbingModules = 0;
    }

    private int getId() {
        return this.id++;
    }

    @Override
    public String reactorCommand(List<String> arguments) {
        String type = arguments.get(0);
        int additionalParameter = Integer.parseInt(arguments.get(1));
        int moduleCapacity = Integer.parseInt(arguments.get(2));

        int currentID = this.getId();

        Reactor currentReactor = null;

        switch (type) {
            case "Cryo":
                currentReactor = new CryoReactor(currentID, additionalParameter, moduleCapacity);
                cryoReactors++;
                break;
            case "Heat":
                currentReactor = new HeatReactor(currentID, additionalParameter, moduleCapacity);
                heatReactors++;
                break;
        }

        this.reactors.put(currentID, currentReactor);

        return String.format(REACTOR_COMMAND_OUTPUT, type, currentID);
    }

    @Override
    public String moduleCommand(List<String> arguments) {
        int reactorID = Integer.parseInt(arguments.get(0));
        String type = arguments.get(1);
        int additionalParameter = Integer.parseInt(arguments.get(2));

        int currentID = this.getId();

        Reactor currentReactor = this.reactors.get(reactorID);

        switch (type) {
            case "CryogenRod":
                CryogenRod rod = new CryogenRod(currentID, additionalParameter);
                this.modules.put(currentID, rod);
                currentReactor.addEnergyModule(rod);
                energyModules++;
                break;
            case "HeatProcessor":
                HeatProcessor processor = new HeatProcessor(currentID, additionalParameter);
                this.modules.put(currentID, processor);
                currentReactor.addAbsorbingModule(processor);
                absorbingModules++;
                break;
            case "CooldownSystem":
                CooldownSystem cooler = new CooldownSystem(currentID, additionalParameter);
                this.modules.put(currentID, cooler);
                currentReactor.addAbsorbingModule(cooler);
                absorbingModules++;
                break;
        }

        return String.format(MODULE_COMMAND_OUTPUT, type, currentID, reactorID);
    }

    @Override
    public String reportCommand(List<String> arguments) {
        int reportID = Integer.parseInt(arguments.get(0));

        if (this.reactors.containsKey(reportID)) {
            return this.reactors.get(reportID).toString();
        } else {
            return this.modules.get(reportID).toString();
        }
    }

    @Override
    public String exitCommand(List<String> arguments) {
        long totalEnergyOutput = this.reactors.values()
                .stream().mapToLong(Reactor::getTotalEnergyOutput).sum();

        long totalHeatAbsorbing = this.reactors.values()
                .stream().mapToLong(Reactor::getTotalHeatAbsorbing).sum();

        return String.format(EXIT_COMMAND_OUTPUT,
                cryoReactors,
                heatReactors,
                energyModules,
                absorbingModules,
                totalEnergyOutput,
                totalHeatAbsorbing);
    }
}
