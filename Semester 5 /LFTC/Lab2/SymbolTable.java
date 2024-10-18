package src;
import src.Pairs;

public class SymbolTable {
    private final HashTable<String> identifiersHashTable ;
    private final HashTable<Integer> constantsHashTable;

    public SymbolTable(int size){
        this.identifiersHashTable = new HashTable<>(size);
        this.constantsHashTable = new HashTable<>(size);
    }

    public Pairs<Integer, Integer> addIdentifier(String name) throws Exception {
        return this.identifiersHashTable.add(name);
    }

    public Pairs<Integer, Integer> addConstant(Integer name) throws Exception{
        return this.constantsHashTable.add(name);
    }

    public boolean removeIdentifier(String identifier) throws Exception {
        return identifiersHashTable.remove(identifier);
    }

    public boolean removeConstant(Integer constant) throws Exception {
        return constantsHashTable.remove(constant);
    }

    public boolean hasIdentifier(String name) {
        return this.identifiersHashTable.containsElement(name);
    }

    public boolean hasConstant(Integer name) {
        return constantsHashTable.containsElement(name);
    }

    public Pairs<Integer, Integer> findPositionIdentifier(String identifier) {
        return identifiersHashTable.getPosition(identifier);
    }

    public Pairs<Integer, Integer> findPositionConstant(Integer constant) {
        return constantsHashTable.getPosition(constant);
    }

    @Override
    public String toString() {
        return "SymbolTable{" +
                "identifiers=" + identifiersHashTable +
                "\nconstants=" + constantsHashTable +
                '}';
    }
}
