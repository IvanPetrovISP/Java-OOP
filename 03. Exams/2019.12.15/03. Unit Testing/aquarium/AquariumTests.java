package aquarium;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AquariumTests {
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS Aquarium

    private static final String FISH_NAME = "Fish";
    private Fish fish;

    private static final String AQUARIUM_NAME = "Aquarium";
    private static final int AQUARIUM_CAPACITY = 1;
    private Aquarium aquarium;

    @Before
    public void setUp() {
        this.fish = new Fish(FISH_NAME);
        this.aquarium = new Aquarium(AQUARIUM_NAME, AQUARIUM_CAPACITY);
    }

    @Test
    public void getNameShouldReturnCorrectValue() {
        assertEquals(AQUARIUM_NAME, this.aquarium.getName());
    }

    @Test
    public void getCapacityShouldReturnCorrectValue() {
        assertEquals(AQUARIUM_CAPACITY, this.aquarium.getCapacity());
    }

    @Test
    public void getCountShouldReturnZeroOnInit() {
        assertEquals(0, this.aquarium.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowIfAquariumNameIsNull() {
        String nullName = null;
        Aquarium dummy = new Aquarium(nullName, AQUARIUM_CAPACITY);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowIfAquariumNameIsEmpty() {
        String emptyName = "";
        Aquarium dummy = new Aquarium(emptyName, AQUARIUM_CAPACITY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIfAquariumCapacityIsBelowZero() {
        Aquarium dummy = new Aquarium(AQUARIUM_NAME, -1);
    }

    @Test
    public void addShouldIncreaseCurrentCount() {
        this.aquarium.add(this.fish);
        assertEquals(1, this.aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addShouldThrowIfCapacityIsFull() {
        this.aquarium.add(this.fish);
        this.aquarium.add(this.fish);
    }

    @Test
    public void removeShouldReduceCount() {
        this.aquarium.add(this.fish);
        this.aquarium.remove(FISH_NAME);
        assertEquals(0, this.aquarium.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeShouldThrowIfFishDoesNotExist() {
        this.aquarium.add(this.fish);
        this.aquarium.remove("DUMMY");
    }

    @Test
    public void sellShouldReturnCorrectFish() {
        this.aquarium.add(this.fish);
        Fish soldFish = this.aquarium.sellFish(FISH_NAME);
        assertEquals(this.fish, soldFish);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sellShouldThrowIfFishDoesNotExist() {
        this.aquarium.add(this.fish);
        Fish soldFish = this.aquarium.sellFish("DUMMY");
    }

    @Test
    public void sellShouldSetFishAvailability() {
        this.aquarium.add(this.fish);
        Fish soldFish = this.aquarium.sellFish(FISH_NAME);
        assertFalse(soldFish.isAvailable());
    }

    @Test
    public void reportShouldReport() {
        String expected = String.format("Fish available at %s: %s", AQUARIUM_NAME, FISH_NAME);
        this.aquarium.add(this.fish);
        assertEquals(expected, this.aquarium.report());
    }
}

