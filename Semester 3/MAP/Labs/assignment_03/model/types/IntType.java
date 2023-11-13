package model.types;

import model.values.IntValue;

public class IntType implements Type {
    public IntType() {}

    @Override
    public Type deepCopy() {
        return new IntType();
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public IntValue defaultValue() {
        return new IntValue(0);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        return this.toString().equals(obj.toString());
    }
}
