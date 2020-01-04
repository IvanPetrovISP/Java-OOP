package heroRepository;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeroRepositoryTest {

    private static final String INITIAL_NAME = "Hercules";
    private static final int INITIAL_LEVEL = 2;
    private static final int INIT_STRENGTH = 5;
    private static final int INIT_AGILITY = 5;
    private static final int INIT_INTELLIGENCE = 5;
    private HeroRepository heroRepository;
    private Hero hero;
    private Item item;
    private Hero additionalHero;


    @Before
    public void setUp() {
        this.item = new Item(INIT_STRENGTH, INIT_AGILITY, INIT_INTELLIGENCE);
        this.hero = new Hero(INITIAL_NAME, INITIAL_LEVEL, this.item);
        this.heroRepository = new HeroRepository();


        Item additionalItem = new Item(2, 3, 6);
        this.additionalHero = new Hero("Achilles", 10, additionalItem);
    }


    @Test
    public void shouldCreateEmptyArrayListWhenInitialised() {
        assertEquals(0, this.heroRepository.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfTryingToAddExistingHero() {
        this.heroRepository.add(hero);
        this.heroRepository.add(hero);
    }

    @Test
    public void shouldAddSuccessfullyAHero() {
        this.heroRepository.add(hero);
        assertEquals(1, this.heroRepository.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenTryingToRemoveNonExistingHero() {
        this.heroRepository.remove("Zeus");
    }

    @Test
    public void shouldRemoveAHeroByName() {
        this.heroRepository.add(hero);
        this.heroRepository.remove(INITIAL_NAME);

        assertEquals(0, heroRepository.getCount());
    }

    @Test
    public void shouldReturnHeroWithHighestStrength() {
        this.heroRepository.add(this.additionalHero);
        this.heroRepository.add(this.hero);
        assertEquals(this.hero, this.heroRepository.getHeroWithHighestStrength());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionIfThereIsNoHeroWithHighestStrength() {
        this.heroRepository.getHeroWithHighestStrength();
    }

    @Test
    public void shouldReturnHeroWithHighestAgility() {
        this.heroRepository.add(this.additionalHero);
        this.heroRepository.add(this.hero);
        assertEquals(this.hero, this.heroRepository.getHeroWithHighestAgility());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionIfThereIsNoHeroWithHighestAgility() {
        this.heroRepository.getHeroWithHighestAgility();
    }

//    @Test
//    public void shouldReturnHeroWithHighestIntelligence() {
//        this.heroRepository.add(this.additionalHero);
//        this.heroRepository.add(this.hero);
//        assertEquals(this.additionalHero, this.heroRepository.getHeroWithHighestIntelligence());
//    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionIfThereIsNoHeroWithHighestIntelligence() {
        this.heroRepository.getHeroWithHighestIntelligence();
    }

    @Test
    public void shouldReturnCorrectCount() {
        this.heroRepository.add(hero);
        this.heroRepository.add(additionalHero);
        assertEquals(2, this.heroRepository.getCount());
    }

    @Test
    public void duplicateHeroesMessage() {
        this.heroRepository.add(hero);
        String result = "";
        try {
            this.heroRepository.add(hero);
        } catch (IllegalArgumentException e) {
            result = e.getMessage();
        }

        assertEquals("Duplicate heroes!", result);
    }

    @Test
    public void test() {
        String expected = this.hero.toString();
        String actual = this.heroRepository.toString();

        boolean result = true;
        for (int i = 0; i < Math.min(expected.length(), actual.length()); i++) {
            if (expected.charAt(i) != actual.charAt(i)) {
                result = false;
                break;
            }
        }
        assertTrue(result);
    }
}