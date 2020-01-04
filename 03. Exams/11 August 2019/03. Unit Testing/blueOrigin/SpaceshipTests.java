package blueOrigin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpaceshipTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS Spaceship

    //  Astronaut Class Tests
    private static final String ASTRONAUT_NAME = "Sarcastronaut";
    private static final double ASTRONAUT_OXYGEN_IN_PERCENTAGE = 3.14;
    private Astronaut astronaut;

    @Before
    public void astronautSetup() {
        this.astronaut = new Astronaut(ASTRONAUT_NAME, ASTRONAUT_OXYGEN_IN_PERCENTAGE);
    }

    @Test
    public void getNameShouldReturnCorrectAstronautValue() {
        assertEquals(ASTRONAUT_NAME, this.astronaut.getName());
    }

    @Test
    public void getOxygenInPercentageShouldReturnCorrectAstronautValue() {
        assertEquals(ASTRONAUT_OXYGEN_IN_PERCENTAGE, this.astronaut.getOxygenInPercentage(), 0.0);
    }

    //  Spaceship Class Tests
    private static final String SPACESHIP_NAME = "SarcastroShip";
    private static final int SPACESHIP_CAPACITY = 5;
    private static final int ZERO_CAPACITY = 0;

    private Spaceship spaceship;

    @Before
    public void spaceShipSetup() {
        this.spaceship = new Spaceship(SPACESHIP_NAME, SPACESHIP_CAPACITY);
    }

    //  Name Tests
    @Test(expected = NullPointerException.class)
    public void setNameShouldThrowExceptionIfValueIsEmpty() {
        Spaceship dummyShip = new Spaceship("", SPACESHIP_CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void setNameShouldThrowExceptionIfValueIsNull() {
        String name = null;
        Spaceship dummyShip = new Spaceship(name, SPACESHIP_CAPACITY);
    }

    @Test
    public void getNameShouldReturnCorrectSpaceshipValue() {
        assertEquals(SPACESHIP_NAME, this.spaceship.getName());
    }

    //  Capacity Tests
    @Test(expected = IllegalArgumentException.class)
    public void setCapacityShouldThrowExceptionIfValueIsBelowZeroCapacity() {
        Spaceship dummyShip = new Spaceship(SPACESHIP_NAME, -1);
    }

    @Test
    public void getCapacityShouldReturnCorrectSpaceshipValue() {
        assertEquals(SPACESHIP_CAPACITY, this.spaceship.getCapacity());
    }

    //  Count Tests
    @Test
    public void getCountShouldReturnZeroOnEmptyCollection() {
        assertEquals(0, this.spaceship.getCount());
    }

    //  Add Astronaut Tests
    @Test
    public void shouldAddAstronautWhenSpaceshipIsNotFull() {
        int currentCount = this.spaceship.getCount();
        this.spaceship.add(this.astronaut);
        assertEquals(currentCount+1, this.spaceship.getCount());
    }

    @Test
    public void shouldAddMoreThanOneAstronautWhenSpaceshipIsNotFull() {
        this.spaceship.add(this.astronaut);
        this.spaceship.add(new Astronaut("Chochko", 55.5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTryingToAddAstronautThatAlreadyExists() {
        this.spaceship.add(this.astronaut);
        this.spaceship.add(this.astronaut);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTryingToAddAstronautWhenSpaceshipIsFull() {
        for (int i = 0; i < SPACESHIP_CAPACITY; i++) {
            String name = "Astro" + i;
            this.spaceship.add(new Astronaut(name, ASTRONAUT_OXYGEN_IN_PERCENTAGE));
        }
        this.spaceship.add(this.astronaut);
    }

    //  Remove Astronaut Tests
    @Test
    public void shouldReturnTrueIfAstronautExists() {
        this.spaceship.add(this.astronaut);
        boolean result = this.spaceship.remove(this.astronaut.getName());
        assertTrue(result);
    }

    @Test
    public void shouldReduceCountWhenRemovingExistingAstronaut() {
        this.spaceship.add(this.astronaut);
        int current = this.spaceship.getCount();
        this.spaceship.remove(this.astronaut.getName());
        assertEquals(current-1, this.spaceship.getCount());
    }

    @Test
    public void shouldReturnFalseIfAstronautDoesNotExist() {
        boolean result = this.spaceship.remove(this.astronaut.getName());
        assertFalse(result);
    }

    @Test
    public void shouldNotReduceCountWhenFailingToRemoveAstronaut() {
        this.spaceship.add(this.astronaut);
        int current = this.spaceship.getCount();
        this.spaceship.remove("RandomDummyAstronautName");
        assertEquals(current, this.spaceship.getCount());
    }

}