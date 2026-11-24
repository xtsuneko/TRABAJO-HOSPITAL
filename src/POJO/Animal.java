package POJO;

public abstract class Animal {
    private int id;
    private String name;
    private String color;
    private int age;
    private double weight;
    private boolean active = true;   // baja lógica

    public Animal(int id, String name, String color, int age, double weight) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.age = age;
        this.weight = weight;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setEdad(int newAge) {
        this.age = newAge;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        return id == ((Animal) o).id;
    }

    @Override
    public int hashCode() { return Integer.hashCode(id); }

    @Override
    public String toString() {
        return name + " (" + age + " años, " + weight + " kg)";
    }
}