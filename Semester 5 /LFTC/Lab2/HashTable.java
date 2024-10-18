package src;
import java.util.ArrayList;

public class HashTable<T>{
    private final ArrayList<ArrayList<T>> hashTable;
    private final int size;

    public HashTable(int size) {
        this.size = size;
        this.hashTable = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            this.hashTable.add(new ArrayList<>());
        }
    }

    public int generateHashForIntegers(int key){
        return key%size;
    }

    public int generateHashForStrings(String key){
        int sum =0;
        for(int i=0;i<key.length();i++){
            sum+= key.charAt(i);
        }
        return sum%size;
    }

    public boolean containsElement(T key){
        int hashValue = computeHashValue(key);
        return hashTable.get(hashValue).contains(key);

    }
    public int computeHashValue(T key){
        int hashValue = -1;
        if(key instanceof Integer){
            hashValue = generateHashForIntegers((Integer) key);
        }
        else if (key instanceof String){
            hashValue = generateHashForStrings((String) key);
        }

        return hashValue;
    }

    public Pairs<Integer,Integer> add(T key) throws Exception{
        int hashValue = computeHashValue(key);
        if(!hashTable.contains(key)){
            hashTable.get(hashValue).add(key);
            return new Pairs<>(hashValue, hashTable.get(hashValue).indexOf(key));
        }
        throw new Exception("Key " + key + " already exists in the table!");
    }

    public Pairs<Integer, Integer> getPosition(T key) {
        if (this.containsElement(key)) {
            int hashValue = computeHashValue(key);
            return new Pairs<>(hashValue, hashTable.get(hashValue).indexOf(key));
        }
        return new Pairs<>(-1, -1);
    }

    public boolean remove(T key) {
        int hashValue = computeHashValue(key);
        return hashTable.get(hashValue).remove(key);
    }

    @Override
    public String toString() {
        return "HashTable{" + "items=" + hashTable + '}';
    }
}
