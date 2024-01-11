package model.expressions;

import model.ADTS.MyIDictionary;
import model.ADTS.MyIHeap;
import model.exceptions.MyExc;
import model.types.RefType;
import model.types.Type;
import model.values.RefValue;
import model.values.Value;


public class ReadHeapExp implements Exp {
    private Exp expression;

    public ReadHeapExp(Exp exp) {
        this.expression = exp;
    }

    @Override
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer, Value> hp) throws MyExc {
        Value val = expression.eval(tbl, hp);

        if (!(val instanceof RefValue)) {
            throw new MyExc("Expression is not a reference value");}

        RefValue ref = (RefValue) val;

        if (!hp.isDefined(ref.getVal())) {
            throw new MyExc("Address is not defined in the heap");}

        return hp.lookup(ref.getVal());
    }

    @Override
    public Exp deepCopy() {
        return new ReadHeapExp(expression);
    }

    @Override
    public String toString() {
        return "readHeap(" +
                "varName='" +
                expression +
                '\'' +
                ')'; }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyExc {
        Type type=expression.typecheck(typeEnv);
        if (type instanceof RefType) {
            RefType reft = (RefType) type;
            return reft.getInner();
        } else
            throw new MyExc("the rH argument is not a Ref Type");
    }

}