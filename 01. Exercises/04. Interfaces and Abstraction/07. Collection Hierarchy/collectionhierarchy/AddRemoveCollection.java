package collectionhierarchy;

import java.util.ArrayList;

public class AddRemoveCollection extends Collection implements AddRemovable {

    public AddRemoveCollection() {
    }

    @Override
    public String remove() {
        if (items.size() == 0) {
            throw new IndexOutOfBoundsException();
        }
        return items.remove(items.size()-1);
    }

    @Override
    public int add(String string) {
        if (items.size() == maxSize) {
            throw new IndexOutOfBoundsException();
        }

        if (items.size() == 0) {
            items.add(string);
        } else {
            ArrayList<String> temp = new ArrayList<>(items);
            items.clear();
            items.add(string);
            items.addAll(temp);
        }
        return 0;
    }
}
