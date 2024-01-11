package model.expressions;

import model.ADTS.MyIDictionary;
import model.ADTS.MyIHeap;
import model.exceptions.MyExc;
import model.types.Type;
import model.values.Value;

public interface Exp {
    Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer, Value> hp) throws MyExc;
    Exp deepCopy();
    String toString();
    Type typecheck(MyIDictionary<String,Type> typeEnv) throws MyExc;

}
