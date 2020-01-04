package aquarium.models.aquariums;

public class FreshwaterAquarium extends BaseAquarium {
    private static final int CAPACITY = 50;
    private static final String TYPE ="FreshwaterAquarium";

    public FreshwaterAquarium(String name) {
        super(name, CAPACITY);
    }

    @Override
    public String getInfo() {
        StringBuilder result = new StringBuilder();
        result.append(this.getName()).append(" (").append(TYPE).append("):");
        result.append(System.lineSeparator());
        result.append(super.getInfo());
        return result.toString().trim();
    }
}
