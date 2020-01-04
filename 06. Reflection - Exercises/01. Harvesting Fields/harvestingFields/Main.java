package harvestingFields;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String input = scanner.nextLine();

		Class harvest = RichSoilLand.class;
		Field[] fields = harvest.getDeclaredFields();
		List<Field> privateFields = new ArrayList<>();
		List<Field> protectedFields = new ArrayList<>();
		List<Field> publicFields = new ArrayList<>();

		Arrays.stream(fields)
				.forEach(f -> {
					if (Modifier.isPrivate(f.getModifiers())) {
						privateFields.add(f);
					} else if (Modifier.isProtected(f.getModifiers())) {
						protectedFields.add(f);
					} else if (Modifier.isPublic(f.getModifiers())) {
						publicFields.add(f);
					}
				});

		while (!"HARVEST".equals(input)) {
			StringBuilder result = new StringBuilder();
			switch (input) {
				case "private":
					for (Field field : privateFields) {
						result
								.append(Modifier.toString(field.getModifiers()))
								.append(" ")
								.append(field.getType().getSimpleName())
								.append(" ")
								.append(field.getName())
								.append(System.lineSeparator());
					}
					break;
				case "protected":
					for (Field field : protectedFields) {
						result
								.append(Modifier.toString(field.getModifiers()))
								.append(" ")
								.append(field.getType().getSimpleName())
								.append(" ")
								.append(field.getName())
								.append(System.lineSeparator());
					}
					break;
				case "public":
					for (Field field : publicFields) {
						result
								.append(Modifier.toString(field.getModifiers()))
								.append(" ")
								.append(field.getType().getSimpleName())
								.append(" ")
								.append(field.getName())
								.append(System.lineSeparator());
					}
					break;
				case "all":
					for (Field field : fields) {
						result
								.append(Modifier.toString(field.getModifiers()))
								.append(" ")
								.append(field.getType().getSimpleName())
								.append(" ")
								.append(field.getName())
								.append(System.lineSeparator());
					}
					break;
			}
			System.out.print(result.toString());
			input = scanner.nextLine();
		}
	}
}
