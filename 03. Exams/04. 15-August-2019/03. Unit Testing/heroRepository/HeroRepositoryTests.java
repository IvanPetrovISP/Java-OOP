package heroRepository;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeroRepositoryTests {
    private static final int INIT_COUNT = 0;
    //TODO: TEST ALL THE FUNCTIONALITY OF THE PROVIDED CLASS HeroRepository
    private HeroRepository heroRepository;
    private Hero hero;
    private Hero heroDummy;

    @Before
    public void setHeroRepository() {
        this.heroRepository = new HeroRepository();
        this.hero = new Hero("Ivan", 876);
    }

    @Test
    public void shouldReturnSizeZeroWhenInitialised() {
        assertEquals(INIT_COUNT, this.heroRepository.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenCreateNullHero() {
        this.heroRepository.create(this.heroDummy);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTryingToCreateHeroThatAlreadyExists() {
        this.heroRepository.create(this.hero);
        this.heroRepository.create(this.hero);
    }

    @Test
    public void createMethodShouldReturnCorrectMessageWhenIsSuccessfullyAdded() {
        String expectedMessage = String.format("Successfully added hero %s with level %d", this.hero.getName(), this.hero.getLevel());
        assertEquals(expectedMessage, this.heroRepository.create(this.hero));
    }

    @Test
    public void createMethodShouldIncreaseRepositoryCount() {
        this.heroRepository.create(this.hero);
        assertEquals(INIT_COUNT + 1, this.heroRepository.getCount());
    }

    @Test (expected = NullPointerException.class)
    public void removeMethodShouldThrowExceptionIfHeroIsNull() {
        this.heroRepository.remove(this.heroDummy.getName());
    }

    @Test
    public void removeMethodShouldReturnTrueAfterSuccessfullyRemovingHeroByName() {
        this.heroRepository.create(this.hero);
        assertTrue(this.heroRepository.remove(this.hero.getName()));
    }

    @Test
    public void removeMethodShouldReturnFalseAfterUnsuccessfullyRemovingHeroByName() {
        this.heroRepository.create(this.heroDummy);
        assertFalse(this.heroRepository.remove(this.hero.getName()));
    }

    @Test
    public void shouldReturnHeroWithHighestLevel() {
        this.heroDummy = new Hero("Asen", 25);
        this.heroRepository.create(this.hero);
        this.heroRepository.create(this.heroDummy);
        Hero best = this.heroRepository.getHeroWithHighestLevel();
        assertEquals(this.hero, best);
    }

    @Test
    public void getHighestLevelHeroShouldReturnNullIfCollectionIsEmpty() {
        assertNull(this.heroRepository.getHeroWithHighestLevel());
    }

    @Test
    public void getHeroShouldReturnNonNullIfSuchHeroExists() {
        this.heroRepository.create(this.hero);
        assertEquals(this.hero, this.heroRepository.getHero(this.hero.getName()));
    }

    @Test
    public void getHeroShouldReturnNullIfSuchHeroDoesNotExist() {
        assertNull(this.heroRepository.getHero(this.hero.getName()));
    }

    @Test
    public void getHeroesShouldReturnCollection() {
        this.heroRepository.create(this.hero);

        int expected = this.heroRepository.getCount();
        int actual = this.heroRepository.getHeroes().size();
        assertEquals(expected, actual);
    }
}

