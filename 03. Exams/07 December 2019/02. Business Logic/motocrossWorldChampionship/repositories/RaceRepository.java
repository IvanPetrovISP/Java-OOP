package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.entities.interfaces.Race;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.*;

import static motocrossWorldChampionship.common.ExceptionMessages.*;

public class RaceRepository implements Repository<Race> {
    private Map<String, Race> races;

    public RaceRepository() {
        this.races = new LinkedHashMap<>();
    }

    @Override
    public Race getByName(String name) {
        if (!this.races.containsKey(name)) {
            throw new NullPointerException(String.format(RACE_NOT_FOUND, name));
        } else {
            return this.races.get(name);
        }
    }

    @Override
    public Collection<Race> getAll() {
        return Collections.unmodifiableCollection(this.races.values());
    }

    @Override
    public void add(Race model) {
        if (this.races.containsKey(model.getName())) {
            throw new IllegalArgumentException(String.format(RACE_EXISTS, model.getName()));
        }

        this.races.put(model.getName(), model);
    }

    @Override
    public boolean remove(Race model) {
        return this.races.remove(model.getName(), model);
    }
}
