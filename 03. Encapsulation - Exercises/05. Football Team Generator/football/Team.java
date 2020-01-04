package football;

import java.util.ArrayList;

public class Team {
    private String name;
    private ArrayList<Player> players;

    public Team(String name) {
        setName(name);
        this.players = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new IllegalArgumentException("A name should not be empty.");
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(String name) {
        boolean containsPlayer = false;

        for (Player player : players) {
            if (player.getName().equals(name)){
                containsPlayer = true;
                this.players.remove(player);
                break;
            }
        }

        if (!containsPlayer) {
            throw new IllegalArgumentException(String.format("Player %s is not in %s team.", name, getName()));
        }
    }

    public double getRating() {
        double result = 0.0;
        for (Player player : players) {
            result += player.overallSkillLevel();
        }
        return Math.round(result);
    }

}
