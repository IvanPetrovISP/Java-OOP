package entities;

import entities.interfaces.Machine;
import entities.interfaces.Pilot;

import java.util.ArrayList;
import java.util.List;

public class PilotImpl implements Pilot {
    private String name;
    private List<Machine> machines;

    public PilotImpl(String name) {
        setName(name);
        this.machines = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(" Pilot name cannot be null or empty string.");//todo remove space before pilot
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addMachine(Machine machine) {
        if (machine == null) {
            throw new NullPointerException("Null machine cannot be added to the pilot.");
        }

        this.machines.add(machine);
    }

    @Override
    public List<Machine> getMachines() {
        return this.machines; //todo unmodifiable?
    }

    @Override
    public String report() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("%s - %d machines", this.getName(), this.getMachines().size()));
        result.append(System.lineSeparator());
        for (Machine machine : machines) {
            result.append(machine.toString());
            result.append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
