package barracksWars.core.commands;

import barracksWars.interfaces.Repository;
import barracksWars.interfaces.Unit;
import barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

import java.lang.reflect.InvocationTargetException;

public class Add extends Command {
    protected Add(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() throws NoSuchMethodException, IllegalAccessException, InstantiationException, ExecutionControl.NotImplementedException, InvocationTargetException, ClassNotFoundException {
        String type = this.getData()[1];
        Unit unit = this.getUnitFactory().createUnit(type);
        this.getRepository().addUnit(unit);

        return type + " added!";
    }
}
