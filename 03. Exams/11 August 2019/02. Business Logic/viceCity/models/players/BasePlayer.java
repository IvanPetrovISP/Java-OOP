package viceCity.models.players;

import viceCity.models.guns.Gun;
import viceCity.repositories.GunRepository;
import viceCity.repositories.interfaces.Repository;

public abstract class BasePlayer implements Player {
    private String name;
    private int lifePoints;
    private Repository<Gun> gunRepository;

    protected BasePlayer(String name, int lifePoints) {
        this.setName(name);
        this.setLifePoints(lifePoints);
        this.gunRepository = new GunRepository();
    }


    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new NullPointerException("Player's name cannot be null or a whitespace!");
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setLifePoints(int lifePoints) {
        if (lifePoints < 0) {
            throw new IllegalArgumentException("Player life points cannot be below zero!");
        }
        this.lifePoints = lifePoints;
    }

    @Override
    public int getLifePoints() {
        return this.lifePoints;
    }

    @Override
    public boolean isAlive() {
        return this.lifePoints > 0;
    }

    @Override
    public Repository<Gun> getGunRepository() {
        return this.gunRepository;
    }

    @Override
    public void takeLifePoints(int points) {
        if (this.lifePoints < points) {
            this.setLifePoints(0);
        } else {
            this.setLifePoints(this.getLifePoints() - points);
        }

    }
}
