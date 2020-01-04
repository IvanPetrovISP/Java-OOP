package aquarium.models.aquariums;

import aquarium.models.decorations.Decoration;
import aquarium.models.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseAquarium implements Aquarium{
    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;

    protected BaseAquarium(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();
    }

    private void setName(String name) {
        if (name== null || name.isEmpty()) {
            throw new NullPointerException("Aquarium name cannot be null or empty.");
        }
        this.name = name;
    }


    @Override
    public int calculateComfort() {
        return this.decorations.stream().mapToInt(Decoration::getComfort).sum();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addFish(Fish fish) {
        if (this.fish.size() >= this.capacity){
            throw new IllegalStateException("Not enough capacity.");
        }
        this.fish.add(fish);//fixme unique names
    }

    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        this.fish.forEach(Fish::eat);
    }

    @Override
    public String getInfo() {
        StringBuilder result = new StringBuilder();

        result.append("Fish:");

        if (this.fish.size() == 0) {
            result.append(" none");
        } else {
            for (Fish fish1 : fish) {
                result.append(" ").append(fish1.getName());
            }
        }

        result.append(System.lineSeparator());
        result.append("Decorations: ").append(this.decorations.size());
        result.append(System.lineSeparator());
        result.append("Comfort: ").append(this.calculateComfort());

        return result.toString();
    }

    @Override
    public Collection<Fish> getFish() {
        return this.fish;
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return this.decorations;
    }
}
