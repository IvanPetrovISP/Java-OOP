package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.Collection;

public class MissionImpl implements Mission {

    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {
        for (Astronaut astronaut : astronauts) {
        while (planet.getItems().iterator().hasNext()){
           // for (String planetItem : planet.getItems()) {
                astronaut.breath();
                String planetItem = planet.getItems().iterator().next();
                astronaut.getBag().getItems().add(planetItem);
                planet.getItems().remove(planetItem);
                if (!astronaut.canBreath()) {
                    break;
                }
            }
        }
    }
}
