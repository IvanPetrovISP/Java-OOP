package blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Scanner scanner = new Scanner(System.in);
        Class box = BlackBoxInt.class;

        @SuppressWarnings("unchecked")
        Constructor constructor = box.getDeclaredConstructor(int.class);
        constructor.setAccessible(true);

        BlackBoxInt boxInstance = (BlackBoxInt)constructor.newInstance(0);

        Field currentValue = boxInstance.getClass().getDeclaredField("innerValue");
        currentValue.setAccessible(true);

        Method[] methods = boxInstance.getClass().getDeclaredMethods();

        String line = scanner.nextLine();
        while (!line.equals("END")) {
            String[] tokens = line.split("_");
            String command = tokens[0];
            int number = Integer.parseInt(tokens[1]);

            Method method = Arrays.stream(methods)
                    .filter(m -> m.getName().equals(command)).findFirst().get();

            method.setAccessible(true);
            method.invoke(boxInstance, number);

            System.out.println(currentValue.getInt(boxInstance));

            line = scanner.nextLine();
        }
    }
}
