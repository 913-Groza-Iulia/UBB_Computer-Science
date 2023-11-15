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
        if (getClass() != obj.getClass()) {
            return false;
        }
        return this.toString().equals(obj.toString());
    }
    public String toString() { return "string"; }
}
