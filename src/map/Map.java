package map;

/**
 * @author qgaye
 * @date 2019/02/26
 */
public interface Map<K, V> {

    Integer getSize();

    boolean isEmpty();

    void add(K key, V value);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    void set(K key, V newValue);
}
