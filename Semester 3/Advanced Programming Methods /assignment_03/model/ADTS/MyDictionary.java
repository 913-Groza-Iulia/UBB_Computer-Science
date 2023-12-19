package model.ADTS;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyDictionary<K, V> implements MyIDictionary<K, V>{
    HashMap<K,V> dictionary;
    public MyDictionary() {
        dictionary = new HashMap<>();
    }
    @Override
    public boolean isDefined(K key) {
        return dictionary.containsKey(key);
    }

    @Override
    public void put(K key, V value) {
        dictionary.put(key, value);
    }

    @Override
    public V lookUp(K key) {
        return dictionary.get(key);
    }

    @Override
    public void update(K key, V value) {
        dictionary.put(key, value);
    }

    @Override
    public V add(K id, V v) {
        return dictionary.put(id, v);
    }

    @Override
    public boolean containsKey(K id) {
        return dictionary.containsKey(id);
    }

    @Override
    public V remove(K id) {
        return dictionary.remove(id);}

    @Override
    public Set<K> getKeys() {
        return dictionary.keySet();
    }

    public String toString() {
        return "MyDictionary{" +
                "dictionary=" + dictionary +
                '}';
    }
}
