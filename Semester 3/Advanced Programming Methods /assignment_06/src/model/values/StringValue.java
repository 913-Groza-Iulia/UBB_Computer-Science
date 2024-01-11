package model.values;

import model.types.StringType;
import model.types.Type;

public class StringValue implements Value{
    String val;

    public StringValue(String v) { val = v; }

    @Override
    public Type getType() {
        return new StringType();
    }
    @Override
    public Value deepCopy() { return new StringValue(val); }
    public String getVal() { return val; }

    public String toString() { return val; }
    @Override
    public boolean equals(Value another) {
        return another instanceof StringValue;
    }

}
