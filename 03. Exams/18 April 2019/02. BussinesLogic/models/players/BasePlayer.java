package models.players;

import models.players.interfaces.Player;
import repositories.interfaces.CardRepository;

public abstract class BasePlayer implements Player {
    private String username;
    private int health;
    private CardRepository cardRepository;
    private boolean isDead;

    protected BasePlayer(CardRepository cardRepository, String username, int health) {
        setCardRepository(cardRepository);
        setUsername(username);
        setHealth(health);
        this.setDead(false);
    }

    public void setUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Player's username cannot be null or an empty string. ");
        }
        this.username = username;
    }

    @Override
    public void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException("Player's health bonus cannot be less than zero.");
        }
        this.health = health;
    }

    public void setCardRepository(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public CardRepository getCardRepository() {
        return this.cardRepository;
    }

    @Override
    public boolean isDead() {
        return this.isDead;
    }

    public void setDead(boolean dead) {
        this.isDead = dead;
    }

    @Override
    public void takeDamage(int damagePoints) {
        if (damagePoints < 0) {
            throw new IllegalArgumentException("Damage points cannot be less than zero.");
        }
        if (this.getHealth() - damagePoints < 0) {
            this.setHealth(0);
            this.setDead(true);
        } else {
            this.setHealth(this.getHealth() - damagePoints);
        }
    }
}
