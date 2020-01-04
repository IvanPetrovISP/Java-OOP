package shoppingspree;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        this.products = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    private double getMoney() {
        return this.money;
    }

    private void setName(String name) {
        if (name.trim().isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    private void setMoney(double money) {
        if (money < 0){
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }

    public void buyProduct(Product product) {
        if (product.getCost() <= this.getMoney()){
            this.products.add(product);
            this.setMoney(this.getMoney() - product.getCost());
        } else {
            throw new IllegalArgumentException(String.format("%s can't afford %s", this.getName(), product.getName()));
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(String.format("%s - ", this.getName()));
        if (this.products.isEmpty()){
            result.append("Nothing bought").append(System.lineSeparator());
        } else {
            for (int i = 0; i < products.size(); i++) {
                result.append(products.get(i).getName());
                if (i < products.size() -1) {
                    result.append(", ");
                }
            }
            result.append(System.lineSeparator());
        }

        return result.toString();
    }
}
