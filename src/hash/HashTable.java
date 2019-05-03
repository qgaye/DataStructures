package hash;

import java.util.TreeMap;

/**
 * @author qgaye
 * @date 2019/03/28
 */
public class HashTable<K, V> {

    /**
     * 使用素数可使得分布更平均
     */
    private static final int INIT_CAPACITY = 97;
    private static final int UPPER_TOL = 10;
    private static final int LOWER_TOL = 2;

    private TreeMap<K, V>[] hashTable;
    private int size;
    private int M;

    public HashTable(int M) {
        hashTable = new TreeMap[M];
        this.M = M;
        size = 0;
        for (int i = 0; i < M; i++) {
            hashTable[i] = new TreeMap<>();
        }
    }

    public HashTable() {
        this(INIT_CAPACITY);
    }

    private int hash(K key) {
        // 将负数转为正数
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize() {
        return size;
    }

    public void add(K key, V value) {
        TreeMap<K, V> map = hashTable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            map.put(key, value);
            size++;

            if (size >= M * UPPER_TOL) {
                resize(M * 2);
            }
        }
    }

    public V remove(K key) {
        V ret = null;
        TreeMap<K, V> map = hashTable[hash(key)];
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;

            if (size <= M * LOWER_TOL && M / 2 >= INIT_CAPACITY) {
                resize(M / 2);
            }
        }
        return ret;
    }

    public void set(K key, V value) {
        TreeMap<K, V> map = hashTable[hash(key)];
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + " doesn't exist.");
        }
        map.put(key, value);
    }

    public boolean contains(K key) {
        return hashTable[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return hashTable[hash(key)].get(key);
    }

    /**
     * hashTable中的容量应为素数，以使分布更均匀
     * 但此处使用的是素数的两倍，是合数，因此还可以优化
     */
    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) {
            newHashTable[i] = new TreeMap<>();
        }

        int oldM = this.M;
        this.M = newM;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashTable[i];
            for (K key : map.keySet()) {
                newHashTable[hash(key)].put(key, map.get(key));
            }
        }
        this.hashTable = newHashTable;
    }
}
