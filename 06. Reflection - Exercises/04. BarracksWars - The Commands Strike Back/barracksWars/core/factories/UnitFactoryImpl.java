package barracksWars.core.factories;

import barracksWars.data.UnitRepository;
import barracksWars.interfaces.Unit;
import barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UnitFactoryImpl implements UnitFactory {

    private static final String UNITS_PACKAGE_NAME =
            "barracksWars.models.units.";

    @Override
    public Unit createUnit(String unitType) {
        // TODO: implement for problem 3
        Unit unit = null;

        try {
            Class unitTypeClass = Class.forName(UNITS_PACKAGE_NAME + unitType);
            @SuppressWarnings("unchecked")
            Constructor constructor = unitTypeClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            unit = (Unit) constructor.newInstance();
        } catch (InstantiationException | InvocationTargetException |
                NoSuchMethodException | IllegalAccessException |
                ClassNotFoundException e) {
            e.printStackTrace();
        }

        assert unit != null;
        return unit;
    }
}
