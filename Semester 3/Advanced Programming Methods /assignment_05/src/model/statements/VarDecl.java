package model.statements;

import model.ADTS.MyIDictionary;
import model.PrgState;
import model.exceptions.VarAlreadyInSymTableError;
import model.types.Type;
import model.values.Value;

public class VarDecl implements IStmt {
    String id;
    Type type;

    public VarDecl(String identifier, Type type) {
        this.id = identifier;
        this.type = type;
    }

    @Override
    public PrgState execute(PrgState state) throws VarAlreadyInSymTableError {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        if (!symTable.isDefined(id)) {
                Value defaultValue = type.defaultValue();
                symTable.add(id, defaultValue);

        } else throw new VarAlreadyInSymTableError(id);
        return null;
    }

    @Override
    public IStmt deepCopy() {
        return new VarDecl(id, type.deepCopy());
    }

    @Override
    public String toString() {
        return type.toString() + " " + id;
    }
}
