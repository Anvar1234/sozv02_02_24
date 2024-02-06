package task4;

import java.util.Iterator;

class Human implements Comparable<Human> {
    private final String name;
    private final int age;
    private Music type;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Human(int age) {
        this("Ivan", age);
    }

    public int getAge() {
        return age;
    }

    public Music getType() {
        return type;
    }

    public void setType(Music type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode(); // поле 1
        result = 31 * result + Integer.hashCode(age); // поле 2
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Human anotherHuman = (Human) obj;
        if (age != (anotherHuman.age)) return false;
        else if (!name.equals(anotherHuman.name)) return false;
        return true;
    }

    @Override
    public int compareTo(Human o) {
        int nameComparison = name.compareTo(o.name);
        if (nameComparison == 0) {
            int ageComparison = Integer.compare(age, o.age);
            if (ageComparison != 0) return ageComparison;
        }
        return nameComparison;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", type=" + type +
                '}';
    }
}
