package model.ADTS;

public interface MyIDictionary<K, V> {
    boolean isDefined(K key);

    void put(K key, V value);

    V lookUp(K key);

    void update(K key, V value);

    V add(K id, V defaultValue);


    boolean containsKey(K id);
}
