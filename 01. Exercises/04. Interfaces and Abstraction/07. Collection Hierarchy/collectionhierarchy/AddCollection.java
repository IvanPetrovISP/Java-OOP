package collectionhierarchy;

import java.util.List;

public class AddCollection extends Collection implements Addable {

    public AddCollection() {
    }

    @Override
    public int add(String string) {
        if (items.size() == maxSize) {
            throw new IndexOutOfBoundsException();
        }
        items.add(string);
        return items.size()-1;
    }
}
