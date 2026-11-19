package POJO;

import INTERFACES.Adoptable;

public class Exotico extends Animal {
    private String type;

    public Exotico(int id, String name, String color, int age, double weight, String type) {
        super(id, name, color, age, weight);
        this.type = type;
    }

    public Exotico(int id, String name, String color, int age, double weight) {
        super(id, name, color, age, weight);
    }

    public String getTipo() {
        return type;
    }

    public void setTipo(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Exotico{" +
                "tipo='" + type + '\'' +
                "} " + super.toString();
    }

    ///METHODS///
}
