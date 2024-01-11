package model.expressions;

import model.ADTS.MyIDictionary;
import model.ADTS.MyIHeap;
import model.exceptions.MyExc;
import model.types.Type;
import model.values.Value;

public class ValueExp implements Exp{
    Value e;
    public ValueExp(Value v) { e=v; }


    public Value eval(MyIDictionary<String, Value> tbl) {
        return e;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyExc {
        return e;
    }

    @Override
    public Exp deepCopy() {
        return new ValueExp(e.deepCopy());
    }

    @Override
    public String toString(){ return e.toString();}

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyExc {
        return e.getType();
    }
}
