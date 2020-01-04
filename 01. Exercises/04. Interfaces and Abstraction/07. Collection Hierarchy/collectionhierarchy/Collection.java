package collectionhierarchy;

import java.util.ArrayList;
import java.util.List;

public abstract class Collection {
    protected final int maxSize = 100;
    protected List<String> items;

    public Collection() {
        this.items = new ArrayList<>();
    }

    private void setItems(List<String> items) {
        if (items.size() > maxSize){
            throw new IndexOutOfBoundsException();
        }
        this.items = items;
    }

    protected List<String> getItems() {
        return this.items;
    }
}
