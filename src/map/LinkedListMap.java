package map;

/**
 * @author qgaye
 * @date 2019/02/26
 */
public class LinkedListMap<K, V> implements Map<K, V> {

    private LinkedList<K, V> linkedList;

    public LinkedListMap() {
        linkedList = new LinkedList<>();
    }

    @Override
    public Integer getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public void add(K key, V value) {
        linkedList.add(key, value);
    }

    @Override
    public V remove(K key) {
        return linkedList.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return linkedList.contains(key);
    }

    @Override
    public V get(K key) {
        return linkedList.get(key);
    }

    @Override
    public void set(K key, V newValue) {
        linkedList.set(key, newValue);
    }
}
