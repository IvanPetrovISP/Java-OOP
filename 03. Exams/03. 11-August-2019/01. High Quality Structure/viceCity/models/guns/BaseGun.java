package viceCity.models.guns;

public abstract class BaseGun implements Gun {
    private String name;
    private int bulletsPerBarrel;
    private int totalBullets;
    private int bulletsPerShot;

    protected BaseGun(String name, int bulletsPerBarrel, int totalBullets) {
        this.setName(name);
        this.setBulletsPerBarrel(bulletsPerBarrel);
        this.setTotalBullets(totalBullets);
        this.bulletsPerShot = bulletsPerBarrel / 10;
    }

    public void setName(String name) {
        if (name== null || name.isEmpty()) {
            throw new NullPointerException("Name cannot be null or whitespace!");
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setBulletsPerBarrel(int bulletsPerBarrel) {
        if (bulletsPerBarrel < 0) {
            throw new IllegalArgumentException("Bullets cannot be below zero!");
        }
        this.bulletsPerBarrel = bulletsPerBarrel;
    }

    @Override
    public int getBulletsPerBarrel() {
        return this.bulletsPerBarrel;
    }

    public void setTotalBullets(int totalBullets) {
        if (totalBullets < 0) {
            throw new IllegalArgumentException("Total bullets cannot be below zero!");
        }
        this.totalBullets = totalBullets;
    }

    @Override
    public int getTotalBullets() {
        return this.totalBullets;
    }

    @Override
    public boolean canFire() {
        return this.getTotalBullets() > 0;
    }

    @Override
    public int fire() {
        this.setTotalBullets(this.getTotalBullets() - bulletsPerShot);
        return bulletsPerShot;
    }
}
