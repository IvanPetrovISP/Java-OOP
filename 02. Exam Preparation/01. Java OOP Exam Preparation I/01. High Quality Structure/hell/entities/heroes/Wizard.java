package hell.entities.heroes;

public class Wizard extends HeroImpl{
    private static final int INITIAL_STRENGTH = 25;
    private static final int INITIAL_AGILITY = 25;
    private static final int INITIAL_INTELLIGENCE = 100;
    private static final int INITIAL_HITPOINTS = 100;
    private static final int INITIAL_DAMAGE = 250;

    public Wizard(String name) {
        super(name, INITIAL_STRENGTH
                , INITIAL_AGILITY
                , INITIAL_INTELLIGENCE
                , INITIAL_HITPOINTS
                , INITIAL_DAMAGE);
    }
}
