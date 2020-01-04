package interfaceperson;

public abstract class BuyerImpl implements Buyer {
    private String name;
    private int food;

    //public BuyerImpl(String name) {
    //    this.name = name;
    //    this.food = 0;
    //}

    public String getName() {
        return this.name;
    }

}
