package interfaceperson;

public class Citizen implements Person {
    private String name;
    private int age;

    public Citizen(String name, int age) {
        setName(name);
        setAge(age);
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
}
