package hell.entities.items;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeItem extends ItemImpl {
    private List<String> commonItems;

    public RecipeItem(String name, int strengthBonus, int agilityBonus, int intelligenceBonus, int hitPointsBonus, int damageBonus, String... commonItems) {
        super(name, strengthBonus, agilityBonus, intelligenceBonus, hitPointsBonus, damageBonus);
        this.commonItems = Arrays.stream(commonItems).collect(Collectors.toList());
    }
}
