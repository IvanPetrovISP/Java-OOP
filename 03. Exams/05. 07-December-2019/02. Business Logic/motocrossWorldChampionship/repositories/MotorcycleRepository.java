package motocrossWorldChampionship.repositories;

import motocrossWorldChampionship.entities.interfaces.Motorcycle;
import motocrossWorldChampionship.repositories.interfaces.Repository;

import java.util.*;

import static motocrossWorldChampionship.common.ExceptionMessages.*;

public class MotorcycleRepository implements Repository<Motorcycle> {
    private Map<String, Motorcycle> motorcycles;

    public MotorcycleRepository() {
        this.motorcycles = new LinkedHashMap<>();
    }

    @Override
    public Motorcycle getByName(String name) {
        if (!this.motorcycles.containsKey(name)) {
            throw new NullPointerException(String.format(MOTORCYCLE_NOT_FOUND, name));
        } else {
            return this.motorcycles.get(name);
        }
    }

    @Override
    public Collection<Motorcycle> getAll() {
        return Collections.unmodifiableCollection(this.motorcycles.values());
    }

    @Override
    public void add(Motorcycle model) {
        if (this.motorcycles.containsKey(model.getModel())) {
            throw new IllegalArgumentException(String.format(MOTORCYCLE_EXISTS, model.getModel()));
        }

        this.motorcycles.put(model.getModel(), model);
    }

    @Override
    public boolean remove(Motorcycle model) {
        return this.motorcycles.remove(model.getModel(), model);
    }
}
