package model.expressions;

import model.ADTS.MyIDictionary;
import model.ADTS.MyIHeap;
import model.exceptions.DivisionByZeroException;
import model.exceptions.MyExc;
import model.exceptions.VarNotOfTypeError;
import model.types.IntType;
import model.values.IntValue;
import model.values.Value;

public class ArithExp implements Exp {
    Exp e1;
    Exp e2;
    char op;

    public ArithExp(char operator, Exp exp1, Exp exp2) {
        this.e1 = exp1;
        this.e2 = exp2;
        this.op = operator;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyExc {
        Value v1, v2;
        v1 = e1.eval(tbl, hp);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl, hp);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if (op == '+') return new IntValue(n1 + n2);
                if (op == '-') return new IntValue(n1 - n2);
                if (op == '*') return new IntValue(n1 * n2);
                if (op == '/')
                    if (n2 == 0) throw new DivisionByZeroException();
                    else return new IntValue(n1 / n2);
                else throw new MyExc("operation not ok");
            } else
                throw new VarNotOfTypeError("second operand", "integer");
        } else
            throw new VarNotOfTypeError("first operand", "integer");
    }

    @Override
    public Exp deepCopy() {
        return new ArithExp(op, e1.deepCopy(), e2.deepCopy());
    }

    @Override
    public String toString(){
        if (op == '+') return e1.toString()+"+"+e2.toString();
        if (op == '-') return e1.toString()+"-"+e2.toString();
        if (op == '*') return e1.toString()+"*"+e2.toString();
        if (op == '/') return e1.toString()+"/"+e2.toString();
        return null;
    }
}
