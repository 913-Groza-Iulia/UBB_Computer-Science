package model.expressions;

import model.ADTS.MyIDictionary;
import model.ADTS.MyIHeap;
import model.exceptions.MyExc;
import model.exceptions.VarNotOfTypeError;
import model.types.IntType;
import model.types.Type;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.Value;

public class RelationalExp implements Exp{
    Exp exp1;
    Exp exp2;
    int op;//1-<, 2-<=, 3-==, 4-!=, 5->, 6->=

    public RelationalExp(Exp expression1, Exp expression2, int operator)
    {
        this.exp1 = expression1;
        this.exp2 = expression2;
        this.op = operator;}


    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyExc {
        Value v1;
        Value v2;
        v1 = exp1.eval(tbl, hp);
        if(v1.getType().equals(new IntType()))
        {
            v2 = exp2.eval(tbl, hp);
            if(v2.getType().equals(new IntType()))
            {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                return new BoolValue(evalInt(i1, i2, op));

            }else
                throw new VarNotOfTypeError("second operator", "int");
        }else
            throw new VarNotOfTypeError("first operator", "int");
    }

    private boolean evalInt(IntValue i1, IntValue i2, int op) throws MyExc {
        if(op == 1) return i1.getVal()< i2.getVal();
        else if(op == 2) return i1.getVal()<= i2.getVal();
        else if(op == 3) return i1.getVal() == i2.getVal();
        else if(op == 4) return i1.getVal() != i2.getVal();
        else if(op == 5) return i1.getVal()> i2.getVal();
        else if(op == 6) return i1.getVal()>= i2.getVal();
        else throw new MyExc("operator not supported");
    }

    @Override
    public Exp deepCopy() {
        return new RelationalExp(exp1.deepCopy(), exp2.deepCopy(), op);
    }

    public String toString(){
        if (op == 1) return exp1.toString()+" < "+exp2.toString();
        if (op == 2) return exp1.toString()+" <= "+exp2.toString();
        if (op == 3) return exp1.toString()+" == "+exp2.toString();
        if (op == 4) return exp1.toString()+" != "+exp2.toString();
        if (op == 5) return exp1.toString()+" > "+exp2.toString();
        if (op == 6) return exp1.toString()+" >= "+exp2.toString();
        return null;
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyExc {
        Type type1,type2;
        type1=exp1.typecheck(typeEnv);
        type2=exp2.typecheck(typeEnv);
        if (type1.equals(new IntType()))
        {
            if (type2.equals(new IntType()))
                return new IntType();
            else
                throw new MyExc("second operand is not an integer");
        }
        else
            throw new MyExc("first operand is not an integer");
    }
}
