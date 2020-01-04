package aquarium.models.aquariums;

public class SaltwaterAquarium extends BaseAquarium {
    private static final int CAPACITY = 25;
    private static final String TYPE ="SaltwaterAquarium";

    public SaltwaterAquarium(String name) {
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
