package model.values;

import model.types.BoolType;
import model.types.Type;

public class BoolValue implements Value {
    boolean val;

    public BoolValue(boolean v) { val = v; }

    public boolean getVal() {
        return val;
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    @Override
    public Value deepCopy() {
        return new BoolValue(val);
    }

    @Override
    public boolean equals(Value another) {
        return another instanceof BoolValue;
    }

    @Override
    public String toString() {
        return val ? "True" : "False";
    }
}
