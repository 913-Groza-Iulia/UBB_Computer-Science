package model.ADTS;

import java.util.Set;

public interface MyIDictionary<K, V> {
    boolean isDefined(K key);

    void put(K key, V value);

    V lookUp(K key);

    void update(K key, V value);

    V add(K id, V defaultValue);


    boolean containsKey(K id);

    V remove(K ifile);

    Set<K> getKeys();
}
