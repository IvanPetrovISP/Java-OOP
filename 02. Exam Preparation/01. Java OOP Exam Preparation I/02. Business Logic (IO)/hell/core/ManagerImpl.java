package hell.core;

import hell.entities.heroes.Assassin;
import hell.entities.heroes.Barbarian;
import hell.entities.heroes.Wizard;
import hell.entities.items.CommonItem;
import hell.entities.items.RecipeItem;
import hell.interfaces.Hero;
import hell.interfaces.Item;
import hell.interfaces.Manager;

import java.util.*;
import java.util.stream.Collectors;

public class ManagerImpl implements Manager {
    private Map<String, Hero> heroes;

    public ManagerImpl() {
        this.heroes = new LinkedHashMap<>();
    }

    @Override
    public String addHero(List<String> arguments) {
        String type = arguments.get(2);
        Hero hero = null;
        switch (type) {
            case "Barbarian":
                hero = new Barbarian(arguments.get(1));
                break;
            case "Assassin":
                hero = new Assassin(arguments.get(1));
                break;
            case "Wizard":
                hero = new Wizard(arguments.get(1));
                break;
        }
        this.heroes.put(arguments.get(1), hero);
        return String.format("Created %s - %s", arguments.get(2), arguments.get(1));
    }

    @Override
    public String addItem(List<String> arguments) {
        String itemName = arguments.get(1);
        int str = Integer.parseInt(arguments.get(3));
        int agi = Integer.parseInt(arguments.get(4));
        int inte = Integer.parseInt(arguments.get(5));
        int hp = Integer.parseInt(arguments.get(6));
        int dmg = Integer.parseInt(arguments.get(7));
        Item item = new CommonItem(itemName, str, agi, inte, hp, dmg);
        String heroName = arguments.get(2);
        this.heroes.get(heroName).addItem(item);
        return String.format("Added item - %s to Hero - %s", itemName, heroName);
    }

    @Override
    public String addRecipe(List<String> arguments) {
        String itemName = arguments.get(1);
        int str = Integer.parseInt(arguments.get(3));
        int agi = Integer.parseInt(arguments.get(4));
        int inte = Integer.parseInt(arguments.get(5));
        int hp = Integer.parseInt(arguments.get(6));
        int dmg = Integer.parseInt(arguments.get(7));


        Item item = new RecipeItem(itemName, str, agi, inte, hp, dmg,
                arguments.stream().skip(8).collect(Collectors.joining()));
        String heroName = arguments.get(2);
        this.heroes.get(heroName).addItem(item);

        return String.format("Added recipe - %s to Hero - %s", itemName, heroName);
    }

    @Override
    public String inspect(List<String> arguments) {
        String name = arguments.get(1);
        return this.heroes.get(name).toString();
    }

    @Override
    public String quit() {
        return "mamma mia";
    }
}
