package unitTesting;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class RaceEntryTest {
    private static final String EXISTING_RIDER = "Rider %s is already added";
    private static final String RIDER_INVALID = "Rider cannot be null.";
    private static final String RIDER_ADDED = "Rider %s added in race.";
    private static final int MIN_PARTICIPANTS = 2;
    private static final String RACE_INVALID = "The race cannot start with less than %d participants.";

    private UnitMotorcycle dummyCycle;
    private UnitMotorcycle motorcycle;
    private static final String MOTOR_NAME = "Motor";
    private static final String MOTOR_DUMMY_NAME = "MotorDummy";
    private static final int MOTOR_HP = 100;
    private static final double MOTOR_CC = 100;

    private UnitRider rider;
    private UnitRider dummy;
    private static final String RIDER_NAME = "Rider";
    private static final String RIDER_DUMMY_NAME = "RiderDummy";

    private RaceEntry raceEntry;

    @Before
    public void setup() {
        this.raceEntry = new RaceEntry();
        this.motorcycle = new UnitMotorcycle(MOTOR_NAME, MOTOR_HP, MOTOR_CC);
        this.dummyCycle = new UnitMotorcycle(MOTOR_DUMMY_NAME, MOTOR_HP, MOTOR_CC);
        this.rider = new UnitRider(RIDER_NAME, this.motorcycle);
        this.dummy = new UnitRider(RIDER_DUMMY_NAME, this.dummyCycle);
    }

    @Test
    public void getRidersShouldReturnEmptyCollectionOnInit() {
        int size = this.raceEntry.getRiders().size();
        assertEquals(0, size);
    }

    @Test
    public void getRidersShouldReturnAppropriateCount() {
        this.raceEntry.addRider(this.rider);
        int size = this.raceEntry.getRiders().size();
        assertEquals(1, size);
    }

    @Test
    public void addRiderShouldAddIfTheObjectIsValid() {
        String actual = this.raceEntry.addRider(this.rider);
        String expected = String.format(RIDER_ADDED, RIDER_NAME);
        assertEquals(expected, actual);
    }

    @Test
    public void addRiderShouldThrowExceptionIfObjetIsInvalid() {
        String actual = "";
        try {
            this.raceEntry.addRider(null);
        } catch (NullPointerException e) {
            actual = e.getMessage();
        }
        assertEquals(RIDER_INVALID, actual);
    }

    @Test
    public void addRiderShouldThrowExceptionIfTheSameRiderAlreadyExists() {
        String expected = String.format(EXISTING_RIDER, RIDER_NAME);
        String actual = "";
        try {
            this.raceEntry.addRider(this.rider);
            this.raceEntry.addRider(this.rider);
        } catch (IllegalArgumentException e) {
            actual = e.getMessage();
        }
        assertEquals(expected, actual);
    }


    @Test
    public void calcHPShouldThrowExceptionIfNotEnoughRiders() {
        this.raceEntry.addRider(this.rider);
        String expected = String.format(RACE_INVALID, MIN_PARTICIPANTS);
        String actual = "";
        try {
            this.raceEntry.calculateAverageHorsePower();
        } catch (IllegalArgumentException e) {
            actual = e.getMessage();
        }
        assertEquals(expected, actual);
    }

    @Test
    public void calcHPShouldReturnCorrectValue() {
        this.raceEntry.addRider(this.rider);
        this.raceEntry.addRider(this.dummy);
        double actual = this.raceEntry.calculateAverageHorsePower();
        double expected = 100.0;
        assertEquals(expected, actual, 0);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getRidersShouldBeUnmodifiable() {
        this.raceEntry.addRider(this.rider);
        Collection<UnitRider> riders = this.raceEntry.getRiders();
        riders.remove(this.rider);
    }
}
