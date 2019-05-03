package set;

/**
 * @author qgaye
 * @date 2019/03/24
 */
public class AVLSet<K extends Comparable<K>, V> implements Set<K> {

    private AVLTree<K, V> avlTree;

    public AVLSet() {
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
    public void add(K k) {
        avlTree.add(k, null);
    }

    @Override
    public void remove(K k) {
        avlTree.remove(k);
    }

    @Override
    public boolean contains(K k) {
        return avlTree.contains(k);
    }
}
