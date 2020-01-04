package barracksWars.core.commands;

import barracksWars.interfaces.Repository;
import barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

import java.lang.reflect.InvocationTargetException;

public class Retire extends Command{
    protected Retire(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() throws NoSuchMethodException, IllegalAccessException, InstantiationException, ExecutionControl.NotImplementedException, InvocationTargetException, ClassNotFoundException {
        String type = this.getData()[1];
        StringBuilder result = new StringBuilder();

        try {
            this.getRepository().removeUnit(type);
            result.append(type).append(" retired!");
        } catch (IllegalArgumentException exception) {
            result.append(exception.getMessage());
        }

        return result.toString();
    }
}
