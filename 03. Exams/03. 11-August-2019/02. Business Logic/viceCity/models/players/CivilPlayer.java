package viceCity.models.players;

public class CivilPlayer extends BasePlayer {
    private static final int INITIAL_LIFEPOINTS = 50;

    public CivilPlayer(String name) {
        super(name, INITIAL_LIFEPOINTS);
    }
}
