package core;

import core.interfaces.MachineFactory;
import core.interfaces.PilotFactory;
import core.interfaces.MachinesManager;

import entities.FighterImpl;
import entities.TankImpl;
import entities.interfaces.Machine;
import entities.interfaces.Pilot;

import java.util.Map;

import static common.OutputMessages.*;

public class MachinesManagerImpl implements MachinesManager {
    private PilotFactory pilotFactory;
    private MachineFactory machineFactory;
    private Map<String, Pilot> pilots;
    private Map<String, Machine> machines;

    public MachinesManagerImpl(PilotFactory pilotFactory, MachineFactory machineFactory,
                               Map<String, Pilot> pilots, Map<String, Machine> machines) {

        //TODO: Implement me
        this.pilotFactory = pilotFactory;
        this.machineFactory = machineFactory;
        this.pilots = pilots;
        this.machines = machines;
    }

    @Override
    public String hirePilot(String name) {
        if (!this.pilots.containsKey(name)) {
            this.pilots.put(name, this.pilotFactory.createPilot(name));
            return String.format(pilotHired, name);
        } else {
            return String.format(pilotExists, name);
        }
    }

    @Override
    public String manufactureTank(String name, double attackPoints, double defensePoints) {
        if (!this.machines.containsKey(name)) {
            this.machines.put(name, this.machineFactory.createTank(name, attackPoints, defensePoints));
            return String.format(tankManufactured, name, attackPoints, defensePoints);
        } else {
            return String.format(machineExists, name);
        }
    }

    @Override
    public String manufactureFighter(String name, double attackPoints, double defensePoints) {
        if (!this.machines.containsKey(name)) {
            this.machines.put(name, this.machineFactory.createFighter(name, attackPoints, defensePoints));
            return String.format(fighterManufactured, name, attackPoints, defensePoints);
        } else {
            return String.format(machineExists, name);
        }
    }

    @Override
    public String engageMachine(String selectedPilotName, String selectedMachineName) {
        if (!this.pilots.containsKey(selectedPilotName)) {
            return String.format(pilotNotFound, selectedPilotName);
        }
        if (!this.machines.containsKey(selectedMachineName)) {
            return String.format(machineNotFound, selectedMachineName);
        }

        Pilot pilot = this.pilots.get(selectedPilotName);
        Machine machine = this.machines.get(selectedMachineName);

        if (machine.getPilot() != null) {
            return String.format(machineHasPilotAlready, selectedMachineName);
        }

        pilot.addMachine(machine);
        machine.setPilot(pilot);

        return String.format(machineEngaged, selectedPilotName, selectedMachineName);
    }

    @Override
    public String attackMachines(String attackingMachineName, String defendingMachineName) {

        if (!this.machines.containsKey(attackingMachineName)) {
            return String.format(machineNotFound, attackingMachineName);
        }

        if (!this.machines.containsKey(defendingMachineName)) {
            return String.format(machineNotFound, defendingMachineName);
        }

        Machine attacker = this.machines.get(attackingMachineName);
        Machine defender = this.machines.get(defendingMachineName);

        attacker.attack(defendingMachineName);

        if (attacker.getAttackPoints() > defender.getDefensePoints()) {
            defender.setHealthPoints(defender.getHealthPoints() - attacker.getAttackPoints());
            if (defender.getHealthPoints() <= 0) {
                defender.setHealthPoints(0);
            }
        }

        return String.format(attackSuccessful, defendingMachineName, attackingMachineName, defender.getHealthPoints());
    }

    @Override
    public String pilotReport(String pilotName) {
        if (!this.pilots.containsKey(pilotName)) {
            return String.format(pilotNotFound, pilotName);
        }

        return this.pilots.get(pilotName).report();
    }

    @Override
    public String toggleFighterAggressiveMode(String fighterName) {
        if (!this.machines.containsKey(fighterName)) {
            return String.format(machineNotFound, fighterName);
        }

        if (!(this.machines.get(fighterName) instanceof FighterImpl)) {
            return String.format(notSupportedOperation, fighterName);
        } else {
            ((FighterImpl)this.machines.get(fighterName)).toggleAggressiveMode();
            return String.format(fighterOperationSuccessful, fighterName);
        }
    }

    @Override
    public String toggleTankDefenseMode(String tankName) {
        if (!this.machines.containsKey(tankName)) {
            return String.format(machineNotFound, tankName);
        }

        if (!(this.machines.get(tankName) instanceof TankImpl)) {
            return String.format(notSupportedOperation, tankName);
        } else {
            ((TankImpl)this.machines.get(tankName)).toggleDefenseMode();
            return String.format(tankOperationSuccessful, tankName);
        }
    }
}
