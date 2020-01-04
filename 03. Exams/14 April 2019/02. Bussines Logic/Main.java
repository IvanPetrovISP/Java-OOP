import core.MachineFactoryImpl;
import core.MachinesManagerImpl;

import core.PilotFactoryImpl;
import core.interfaces.MachineFactory;
import core.interfaces.PilotFactory;
import core.interfaces.MachinesManager;
import entities.interfaces.Machine;
import entities.interfaces.Pilot;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PilotFactory pilotFactory = new PilotFactoryImpl(); //TODO change null with your implementation
        MachineFactory machineFactory = new MachineFactoryImpl(); //TODO change null with your implementation
        Map<String, Pilot> pilots = new LinkedHashMap<>();
        Map<String, Machine> machines = new LinkedHashMap<>();

        MachinesManager machinesManager = new MachinesManagerImpl(pilotFactory, machineFactory, pilots, machines);

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        while (!input.equals("Over")) {
            String[] tokens = input.split("\\s+");
            String command = tokens[0];

            String result = "";
            switch (command) {
                case "Hire":
                    result = machinesManager.hirePilot(tokens[1]);
                    break;
                case "Report":
                   result = machinesManager.pilotReport(tokens[1]);
                   break;
                case "ManufactureTank":
                    result = machinesManager.manufactureTank(tokens[1], Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
                    break;
                case "ManufactureFighter":
                    result = machinesManager.manufactureFighter(tokens[1], Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]));
                    break;
                case "Engage":
                    result = machinesManager.engageMachine(tokens[1], tokens[2]);
                    break;
                case "Attack":
                    result = machinesManager.attackMachines(tokens[1], tokens[2]);
                    break;
                case "AggressiveMode":
                    result = machinesManager.toggleFighterAggressiveMode(tokens[1]);
                    break;
                case "DefenseMode":
                    result = machinesManager.toggleTankDefenseMode(tokens[1]);
                    break;
            }

            System.out.println(result);
            input = scanner.nextLine();
        }
    }
}
