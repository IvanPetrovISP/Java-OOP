package collectionhierarchy;

import java.util.ArrayList;
import java.util.List;

public class MyListImpl extends Collection implements MyList {

    public MyListImpl() {
    }

    @Override
    public int getUsed() {
        return items.size();
    }

    @Override
    public String remove() {
        if (items.size() == 0) {
            throw new IndexOutOfBoundsException();
        }

        ArrayList<String> temp = new ArrayList<>();
        String result = items.get(0);

        for (int i = 0; i < items.size()-1; i++) {
            temp.add(i, items.get(i+1));
        }
        items.clear();
        items.addAll(temp);

        return result;
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
