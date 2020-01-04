package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.entities.interfaces.Rider;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.*;

import static motocrossWorldChampionship.common.ExceptionMessages.*;

public class RiderRepository implements Repository<Rider> {
    private Map<String, Rider> riders;

    public RiderRepository() {
        this.riders = new LinkedHashMap<>();
    }

    @Override
    public Rider getByName(String name) {
        if (!this.riders.containsKey(name)) {
            throw new NullPointerException(String.format(RIDER_NOT_FOUND, name));
        } else {
            return this.riders.get(name);
        }
    }

    @Override
    public Collection<Rider> getAll() {
        return Collections.unmodifiableCollection(this.riders.values());
    }

    @Override
    public void add(Rider model) {
        if (this.riders.containsKey(model.getName())) {
            throw new IllegalArgumentException(String.format(RIDER_EXISTS, model.getName()));
        }

        this.riders.put(model.getName(), model);
    }

    @Override
    public boolean remove(Rider model) {
        return this.riders.remove(model.getName(), model);
    }
}
