package parkingSystem;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SoftParkTest {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS SoftPark
    private SoftPark softPark;
    private Car car;
    private static final String CORRECT_PARKING_SPOT = "A4";
    private static final String INCORRECT_PARKING_SPOT = "A5";
    private static final String DEFALT_CAR_MAKE = "Kola";
    private static final String DEFALT_CAR_NUMBER = "0000";

    @Before
    public void setup() {
        softPark = new SoftPark();
        car = new Car(DEFALT_CAR_MAKE, DEFALT_CAR_NUMBER);
    }

    @Test
    public void shouldReturnCorrectCarMake() {
        String result = DEFALT_CAR_MAKE;
        Assert.assertEquals(result, car.getMake());
    }

    @Test
    public void shouldAcceptValidParkingSpot() {
        boolean result = softPark.getParking().containsKey(CORRECT_PARKING_SPOT);

        Assert.assertTrue(result);
    }

    @Test
    public void shouldThrowExceptionIfParkingSpotDoesNotExist() {
        boolean result = softPark.getParking().containsKey(INCORRECT_PARKING_SPOT);

        Assert.assertFalse(result);
    }

    @Test
    public void checkIfParkingSpotIsEmpty() {
        boolean result = this.softPark
                .getParking().get(CORRECT_PARKING_SPOT) == null;

        Assert.assertTrue(result);
    }

    @Test
    public void checkIfParkingSpotIsTaken() {
        this.softPark.parkCar(CORRECT_PARKING_SPOT, car);
        boolean result = this.softPark
                .getParking().get(CORRECT_PARKING_SPOT) != null;

        Assert.assertTrue(result);
    }

    @Test
    public void shouldParkCarOnEmptyParkingSlot() {
        String result = String.format("Car:%s parked successfully!", DEFALT_CAR_NUMBER);
        String actual = this.softPark.parkCar(CORRECT_PARKING_SPOT, car);

        Assert.assertEquals(result, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTryingToParkOnTakenParkingSpot() {
        this.softPark.parkCar(CORRECT_PARKING_SPOT, car);
        this.softPark.parkCar(CORRECT_PARKING_SPOT, car);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTryingToRemoveCarFromEmptyParkingSpot() {
        this.softPark.removeCar(CORRECT_PARKING_SPOT, car);
    }

    @Test
    public void shouldRemoveCarFromTakenParkingSpot() {
        this.softPark.parkCar(CORRECT_PARKING_SPOT, car);
        String actual = this.softPark.removeCar(CORRECT_PARKING_SPOT, car);
        String result = String.format("Remove car:%s successfully!", DEFALT_CAR_NUMBER);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTryingToRemoveWrongCarFromTakenParkingSpot() {
        this.softPark.parkCar(CORRECT_PARKING_SPOT, car);
        Car wrongCar = new Car("Brum", "123123");
        this.softPark.removeCar(CORRECT_PARKING_SPOT, wrongCar);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAcceptIncorrectParkingSpotWhenTryingToRemove() {
        this.softPark.removeCar(INCORRECT_PARKING_SPOT, car);
        shouldThrowExceptionIfParkingSpotDoesNotExist();
    }


}