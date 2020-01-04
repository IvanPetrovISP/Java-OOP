package models.players;

import repositories.interfaces.CardRepository;

public class Beginner extends BasePlayer {
    private static final int defaultHealthPoints = 50;

    public Beginner(CardRepository cardRepository, String username) {
        super(cardRepository, username, defaultHealthPoints);
    }

}
