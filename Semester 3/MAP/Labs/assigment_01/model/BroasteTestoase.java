package model;

import exceptions.NegativeAgeException;

import java.util.Objects;

public class BroasteTestoase implements Acvariu{
    private final int age;

    public BroasteTestoase(int age) throws NegativeAgeException
    {
        if(age < 0)
        {
            throw new NegativeAgeException("Turtle age cannot be negative");
        }
        this.age = age;
    }
    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Turtle with age = " + getAge();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BroasteTestoase that = (BroasteTestoase) o;
        return age == that.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age);
    }
}
