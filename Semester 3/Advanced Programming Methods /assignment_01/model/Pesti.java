package model;

import exceptions.NegativeAgeException;

import java.util.Objects;

public class Pesti implements Acvariu {
    private int age;

    public Pesti(int age) throws NegativeAgeException {
        if (age < 0) {
            throw new NegativeAgeException("Fish age cannot be negative");
        }
        this.age = age;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Fish with age = " + getAge();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pesti pesti = (Pesti) o;
        return age == pesti.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age);
    }
}
