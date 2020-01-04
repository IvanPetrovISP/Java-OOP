package motocrossWorldChampionship.entities;

import static motocrossWorldChampionship.common.ExceptionMessages.INVALID_HORSE_POWER;

public class SpeedMotorcycle extends MotorcycleImpl {
    private static final double CUBIC_CENTIMETERS = 125;
    private static final int MIN_HP = 50;
    private static final int MAX_HP = 69;

    public SpeedMotorcycle(String model, int horsePower) {
        super(model, horsePower, CUBIC_CENTIMETERS);
        validateHP(horsePower);
    }

    private void validateHP(int horsePower) {
        if (horsePower < MIN_HP || horsePower > MAX_HP) {
            throw new IllegalArgumentException(String.format(INVALID_HORSE_POWER, horsePower));
        }
    }
}
