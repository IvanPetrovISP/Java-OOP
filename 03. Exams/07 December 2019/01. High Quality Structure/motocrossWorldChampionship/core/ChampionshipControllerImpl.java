package motocrossWorldChampionship.core;

import motocrossWorldChampionship.core.interfaces.ChampionshipController;
import motocrossWorldChampionship.entities.PowerMotorcycle;
import motocrossWorldChampionship.entities.RaceImpl;
import motocrossWorldChampionship.entities.RiderImpl;
import motocrossWorldChampionship.entities.SpeedMotorcycle;
import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.entities.interfaces.Rider;
import motocrossWorldChampionship.repositories.MotorcycleRepository;
import motocrossWorldChampionship.repositories.RaceRepository;
import motocrossWorldChampionship.repositories.RiderRepository;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static motocrossWorldChampionship.common.ExceptionMessages.*;
import static motocrossWorldChampionship.common.OutputMessages.*;

public class ChampionshipControllerImpl implements ChampionshipController {
    private Repository<Motorcycle> motorcycleRepository;
    private Repository<Rider> riderRepository;
    private Repository<Race> raceRepository;

    private static final int MIN_PARTICIPANTS_COUNT = 3;

    public ChampionshipControllerImpl() {
        this.motorcycleRepository = new MotorcycleRepository();
        this.riderRepository = new RiderRepository();
        this.raceRepository = new RaceRepository();
    }

    @Override
    public String createRider(String riderName) {
        this.riderRepository.add(new RiderImpl(riderName));
        return String.format(RIDER_CREATED, riderName);
    }

    @Override
    public String createMotorcycle(String type, String model, int horsePower) {
        Motorcycle motorcycle;
        String typeName = "";
        switch (type) {
            case "Speed":
                motorcycle = new SpeedMotorcycle(model, horsePower);
                typeName = "SpeedMotorcycle";
                break;
            case "Power":
                motorcycle = new PowerMotorcycle(model, horsePower);
                typeName = "PowerMotorcycle";
                break;
            default:
                motorcycle = null;
        }

        this.motorcycleRepository.add(motorcycle);
        return String.format(MOTORCYCLE_CREATED, typeName, model);
    }

    @Override
    public String addMotorcycleToRider(String riderName, String motorcycleModel) {
        Rider rider = this.riderRepository.getByName(riderName);
        Motorcycle motorcycle = this.motorcycleRepository.getByName(motorcycleModel);

        rider.addMotorcycle(motorcycle);

        return String.format(MOTORCYCLE_ADDED, riderName, motorcycleModel);
    }

    @Override
    public String addRiderToRace(String raceName, String riderName) {
        Race race = this.raceRepository.getByName(raceName);
        Rider rider = this.riderRepository.getByName(riderName);

        race.addRider(rider);

        return String.format(RIDER_ADDED, riderName, raceName);
    }

    @Override
    public String createRace(String name, int laps) {
        Race race = new RaceImpl(name, laps);
        this.raceRepository.add(race);

        return String.format(RACE_CREATED, name);
    }

    @Override
    public String startRace(String raceName) {
        Race race = this.raceRepository.getByName(raceName);
        List<Double> collect = this.riderRepository.getAll()
                .stream()
                .mapToDouble(r -> r.getMotorcycle().calculateRacePoints(race.getLaps()))
                .sorted().boxed().collect(Collectors.toList());

        if (collect.size() < MIN_PARTICIPANTS_COUNT) {
            throw new IllegalArgumentException(String.format(RACE_INVALID, raceName, MIN_PARTICIPANTS_COUNT));
        }

        Collections.reverse(collect);
        List<Double> limit = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            limit.add(collect.get(i));
        }

        List<Rider> winners = new ArrayList<>();

        int counter = 0;
        while (counter < 3) {
            for (Rider rider : this.riderRepository.getAll()) {

                if (rider.getMotorcycle().calculateRacePoints(race.getLaps()) == limit.get(counter)) {
                    winners.add(rider);
                    counter++;
                    break;
                }
            }
        }


        StringBuilder result = new StringBuilder();
        result.append(String.format(RIDER_FIRST_POSITION, winners.get(0).getName(), raceName)).append(System.lineSeparator());
        result.append(String.format(RIDER_SECOND_POSITION, winners.get(1).getName(), raceName)).append(System.lineSeparator());
        result.append(String.format(RIDER_THIRD_POSITION, winners.get(2).getName(), raceName));

        return result.toString().trim();
    }
}
