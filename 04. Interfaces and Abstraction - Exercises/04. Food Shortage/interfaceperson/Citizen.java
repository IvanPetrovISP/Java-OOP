package interfaceperson;

public class Citizen implements Person, Identifiable, Birthable, Buyer {
    private String name;
    private int age;
    private String id;
    private String birthDate;
    private int food;

    public Citizen(String name, int age, String id, String birthdate) {
        setName(name);
        setAge(age);
        setId(id);
        setBirthDate(birthdate);
        this.food = 0;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setFood(int food) {
        this.food = food;
    }

    @Override
    public String getBirthDate() {
        return this.birthDate;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }

    @Override
    public void buyFood() {
        setFood(getFood() + 10);
    }

    @Override
    public int getFood() {
        return this.food;
    }
}
