package model.types;

import model.values.BoolValue;

public class BoolType implements Type {

    @Override
    public Type deepCopy() { return new BoolType(); }

    @Override
    public String toString() { return "bool"; }

    @Override
    public BoolValue defaultValue() {
        return new BoolValue(false);
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
