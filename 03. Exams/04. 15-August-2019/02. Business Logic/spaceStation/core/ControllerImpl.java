package spaceStation.core;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;
import spaceStation.repositories.Repository;

import java.util.Collection;
import java.util.stream.Collectors;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Astronaut> astronautRepository;
    private Repository<Planet> planetRepository;
    private Mission mission;

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
        this.mission = new MissionImpl();
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        switch (type) {
            case "Biologist":
                this.astronautRepository.add(new Biologist(astronautName));
                break;
            case "Geodesist":
                this.astronautRepository.add(new Geodesist(astronautName));
                break;
            case "Meteorologist":
                this.astronautRepository.add(new Meteorologist(astronautName));
                break;
            default:
                throw new IllegalArgumentException(ASTRONAUT_INVALID_TYPE);
        }
        return String.format(ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);
        this.planetRepository.add(planet);
        for (String item : items) {
            this.planetRepository.findByName(planetName).getItems().add(item);
        }

        return String.format(PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        Astronaut astronaut = this.astronautRepository.findByName(astronautName);
        if (astronaut == null) {
            throw new IllegalArgumentException(String.format(ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }

        this.astronautRepository.remove(astronaut);
        return String.format(ASTRONAUT_RETIRED, astronautName);
    }

    private int exploredPlanets = 0;

    @Override
    public String explorePlanet(String planetName) {
        Planet planet = this.planetRepository.findByName(planetName);
        Collection<Astronaut> models = this.astronautRepository
                .getModels()
                .stream().filter(a -> a.getOxygen() >= 60).collect(Collectors.toList());
        if (models.size() <= 0) {
            throw new IllegalArgumentException(PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }

        this.mission.explore(planet, models);

        exploredPlanets++;

        long countOfDead = models.stream().filter(a -> a.getOxygen() == 0).count();

        return String.format(PLANET_EXPLORED, planet.getName(), countOfDead);
    }

    @Override
    public String report() {
        StringBuilder result = new StringBuilder(String.format(REPORT_PLANET_EXPLORED, exploredPlanets));
        result.append(System.lineSeparator())
                .append(REPORT_ASTRONAUT_INFO);

        for (Astronaut model : this.astronautRepository.getModels()) {
            result.append(System.lineSeparator());
            result.append(String.format(REPORT_ASTRONAUT_NAME, model.getName()));
            result.append(System.lineSeparator());
            result.append(String.format(REPORT_ASTRONAUT_OXYGEN, model.getOxygen()));
            result.append(System.lineSeparator());
            if (model.getBag().getItems().size() == 0) {
                result.append(String.format(REPORT_ASTRONAUT_BAG_ITEMS, "none"));
            } else {
                result.append(String.format(REPORT_ASTRONAUT_BAG_ITEMS,
                        String.join(REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER, model.getBag().getItems())));
            }
        }

        return result.toString().trim();
    }
}
