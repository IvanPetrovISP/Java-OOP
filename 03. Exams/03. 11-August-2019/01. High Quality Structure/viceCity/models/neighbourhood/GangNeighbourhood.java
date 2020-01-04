package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;

import java.util.Collection;

public abstract class GangNeighbourhood implements Neighbourhood {


    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {
        boolean mainPlayerCanShoot = !mainPlayer.getGunRepository().getModels().isEmpty();
        boolean civilPlayersExist = !civilPlayers.isEmpty();
        int guns = mainPlayer.getGunRepository().getModels().size();

        while (mainPlayerCanShoot && civilPlayersExist) {
            for (Player civilPlayer : civilPlayers) {
                while (civilPlayer.isAlive()) {
                    for (Gun gun : mainPlayer.getGunRepository().getModels()) {
                        while (gun.canFire()) {
                            civilPlayer.takeLifePoints(gun.fire());
                        }
                    }
                }
            }
        }

        if (civilPlayersExist) {
            while (mainPlayer.isAlive()) {
                for (Player civilPlayer : civilPlayers) {
                    for (Gun gun : civilPlayer.getGunRepository().getModels()) {
                        while (gun.canFire()) {
                            mainPlayer.takeLifePoints(gun.fire());
                        }
                    }
                }
            }
        }
    }
}
