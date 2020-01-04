package hell.entities.heroes;

public class Assassin extends HeroImpl {
    private static final int INITIAL_STRENGTH = 25;
    private static final int INITIAL_AGILITY = 100;
    private static final int INITIAL_INTELLIGENCE = 15;
    private static final int INITIAL_HITPOINTS = 150;
    private static final int INITIAL_DAMAGE = 300;

    public Assassin(String name) {
        super(name, INITIAL_STRENGTH
                , INITIAL_AGILITY
                , INITIAL_INTELLIGENCE
                , INITIAL_HITPOINTS
                , INITIAL_DAMAGE);
    }
}
