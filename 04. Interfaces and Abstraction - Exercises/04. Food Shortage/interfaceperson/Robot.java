package interfaceperson;

public class Robot implements Identifiable{
    private String id;
    private String model;

    public Robot(String id, String model) {
        setId(id);
        setModel(model);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
