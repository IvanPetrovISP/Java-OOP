package viceCity.core;

import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;
import viceCity.repositories.GunRepository;
import static viceCity.common.ConstantMessages.*;

import java.util.*;

public class ControllerImpl implements Controller {
    private Player mainPlayer;
    private Map<String, Player> civilians;
    private ArrayDeque<Gun> guns;
    private Neighbourhood neighbourhood;

    public ControllerImpl() {
        this.mainPlayer = new MainPlayer();
        this.civilians = new LinkedHashMap<>();
        this.guns = new ArrayDeque<>();
        this.neighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
        this.civilians.put(name, new CivilPlayer(name));
        return String.format(PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {

        switch (type) {
            case "Pistol":
                this.guns.offer(new Pistol(name));
                break;
            case "Rifle":
                this.guns.offer(new Rifle(name));
                break;
            default:
                return GUN_TYPE_INVALID;
        }

        return String.format(GUN_ADDED, name, type);
    }

    @Override
    public String addGunToPlayer(String name) {

        if (this.guns.isEmpty()) {
            return GUN_QUEUE_IS_EMPTY;
        }

        if (!this.civilians.containsKey(name)) {
            return CIVIL_PLAYER_DOES_NOT_EXIST;
        }

        String gun = this.guns.peek().getName();

        if (name.equals("Vercetti")) {
            this.mainPlayer.getGunRepository().add(this.guns.poll());
            return String.format(GUN_ADDED_TO_MAIN_PLAYER, gun, this.mainPlayer.getName());
        } else {
            this.civilians.get(name).getGunRepository().add(this.guns.poll());
            return String.format(GUN_ADDED_TO_CIVIL_PLAYER, gun, this.civilians.get(name).getName());
        }
    }

    @Override
    public String fight() {
        this.neighbourhood.action(this.mainPlayer, this.civilians.values());
        long deadCivilPlayers = this.civilians.values()
                .stream()
                .filter(p -> !p.isAlive())
                .count();

        StringBuilder result = new StringBuilder();

        boolean areTakenLifePoints = this.civilians.values().stream().allMatch(p -> p.getLifePoints() == 50);

        if (this.mainPlayer.getLifePoints() == 100 && areTakenLifePoints) {
            result.append(FIGHT_HOT_HAPPENED);
        } else {
            result.append(FIGHT_HAPPENED)
                    .append(System.lineSeparator())
                    .append(String.format(MAIN_PLAYER_LIVE_POINTS_MESSAGE, this.mainPlayer.getLifePoints()))
                    .append(System.lineSeparator())
                    .append(String.format(MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, deadCivilPlayers))
                    .append(System.lineSeparator())
                    .append(String.format(CIVIL_PLAYERS_LEFT_MESSAGE, this.civilians.size() - deadCivilPlayers));
        }

        return result.toString().trim();
    }
}
