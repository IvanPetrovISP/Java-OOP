package aquarium.core;

import aquarium.models.aquariums.Aquarium;
import aquarium.models.aquariums.FreshwaterAquarium;
import aquarium.models.aquariums.SaltwaterAquarium;
import aquarium.models.decorations.Decoration;
import aquarium.models.decorations.Ornament;
import aquarium.models.decorations.Plant;
import aquarium.models.fish.Fish;
import aquarium.models.fish.FreshwaterFish;
import aquarium.models.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;

import java.util.ArrayList;
import java.util.Collection;

public class ControllerImpl implements Controller{
    private DecorationRepository decorations;
    private Collection<Aquarium> aquariums;

    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new ArrayList<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {
        Aquarium aquarium = null;

        if (aquariumType.equals("FreshwaterAquarium")) {
            aquarium = new FreshwaterAquarium(aquariumName);
        } else if (aquariumType.equals("SaltwaterAquarium")) {
            aquarium = new SaltwaterAquarium(aquariumName);
        } else {
            throw new IllegalArgumentException("Invalid aquarium type.");
        }

        this.aquariums.add(aquarium);

        return String.format("Successfully added %s.", aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        Decoration decoration = null;

        if (type.equals("Ornament")) {
            decoration = new Ornament();
        } else if (type.equals("Plant")) {
            decoration = new Plant();
        } else {
            throw new IllegalArgumentException("Invalid decoration type.");
        }

        this.decorations.add(decoration);

        return String.format("Successfully added %s.", type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        Decoration decoration = this.decorations.findByType(decorationType);

        if (decoration == null) {
            throw new IllegalArgumentException(String.format("There isn't a decoration of type %s.", decorationType));
        }

        this.aquariums.stream()
                .filter(a->a.getName().equals(aquariumName))
                .forEach(a->a.addDecoration(decoration));
        this.decorations.remove(decoration);

        return String.format("Successfully added %s to %s.", decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
        Fish fish = null;
        Aquarium aquarium = this.aquariums.stream().findAny()
                .filter(a->a.getName().equals(aquariumName)).orElse(null);

        switch (fishType) {
            case "FreshwaterFish":
                fish = new FreshwaterFish(fishName, fishSpecies,price);
                if (aquarium != null && aquarium.getName().equals("SaltwaterAquarium")) {
                    return "Water not suitable.";
                }
                break;
            case "SaltwaterFish":
                fish = new SaltwaterFish(fishName, fishSpecies,price);
                if (aquarium != null && aquarium.getName().equals("FreshwaterAquarium")) {
                    return "Water not suitable.";
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid fish type.");
        }

        aquarium.addFish(fish);

        return String.format("Successfully added %s to %s.", fishType, aquariumName);
    }

    @Override
    public String feedFish(String aquariumName) {
        int count = 0;
        Aquarium aquarium1 = this.aquariums.stream().findFirst()
                .filter(a -> a.getName().equals(aquariumName)).orElse(null);

        assert aquarium1 != null;
        aquarium1.feed();
        count = aquarium1.getFish().size();

        return String.format("Fish fed: %d", count);
    }

    @Override
    public String calculateValue(String aquariumName) {
        Aquarium aquarium1 = this.aquariums.stream().findFirst()
                .filter(a -> a.getName().equals(aquariumName)).orElse(null);

        assert aquarium1 != null;
        double sum = 0;

        double fishPrice = aquarium1.getFish().stream().mapToDouble(Fish::getPrice).sum();
        double decoPrice = aquarium1.getDecorations().stream().mapToDouble(Decoration::getPrice).sum();

        sum = fishPrice + decoPrice;

        return String.format("The value of Aquarium %s is %.2f.", aquariumName, sum);
    }

    @Override
    public String report() {
        StringBuilder result = new StringBuilder();
        for (Aquarium aquarium : aquariums) {
            result.append(aquarium.getInfo()).append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
