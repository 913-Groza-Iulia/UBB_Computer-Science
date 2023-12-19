package model.types;

import model.values.StringValue;
import model.values.Value;

public class StringType implements Type{

    @Override
    public Type deepCopy() {
        return new StringType();
    }

    @Override
    public Value defaultValue() {
        return new StringValue(" ");
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();

    }
    public String toString() { return "string"; }
}
