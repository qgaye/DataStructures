package map;

/**
 * @author qgaye
 * @date 2019/03/24
 */
public class AVLMap<K extends Comparable<K>, V> implements Map<K, V> {

    private AVLTree<K, V> avlTree;

    public AVLMap() {
        avlTree = new AVLTree<>();
    }

    @Override
    public Integer getSize() {
        return avlTree.getSize();
    }

    @Override
    public boolean isEmpty() {
        return avlTree.isEmpty();
    }

    @Override
    public void add(K key, V value) {
        avlTree.add(key, value);
    }

    @Override
    public V remove(K key) {
        return avlTree.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return avlTree.contains(key);
    }

    @Override
    public V get(K key) {
        return avlTree.get(key);
    }

    @Override
    public void set(K key, V newValue) {
        avlTree.set(key, newValue);
    }
}
