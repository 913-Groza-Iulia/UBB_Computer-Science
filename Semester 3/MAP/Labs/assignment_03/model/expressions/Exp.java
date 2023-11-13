package model.expressions;

import model.ADTS.MyIDictionary;
import model.exceptions.MyExc;
import model.values.Value;

public interface Exp {
    Value eval(MyIDictionary<String,Value> tbl) throws MyExc;
    Exp deepCopy();
    String toString();

}
