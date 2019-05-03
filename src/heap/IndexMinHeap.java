package heap;

/**
 * 最小索引堆
 * 有两个数组，一个数组存放数据，另一个数组根据索引值找到存放的数据进行排序
 * 最小索引堆可以更新
 * 即将data中的数据根据一定排序，将排序结果以索引形式在indexs数组中展示，并且data中的数据索引不一定要出现在indexs数组中排序
 * @author qgaye
 * @date 2019/04/27
 */
public class IndexMinHeap<E extends Comparable<E>> {

    // 存放索引对应的数据
    private E[] data;
    // 索引排序处
    private Integer[] indexs;
    private int count;
    private int capacity;

    public IndexMinHeap(int capacity) {
        data = (E[]) new Comparable[capacity];
        indexs = new Integer[capacity];
        this.count = 0;
        this.capacity = capacity;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * 通过索引获取data中数据
     */
    public E getData(int index) {
        return data[index];
    }

    /**
     * 我认为在索引堆中不应该存在offer方法，在索引堆中所有操作都应该和索引有关
     * 每次更新会先检查该索引是否在最小堆中了，不在加入，若已经在了，则先shiftUp再shiftDown
     */
    public void update(int index, E e) {
        if (index < 0 || index >= capacity) {
            throw new IllegalArgumentException("Index - " + index + " is illegal.");
        }
        data[index] = e;
        for (int i = 0; i < count; i++) {
            if (indexs[i] == index) {
                shiftUp(i);
                shiftDown(i);
                return;
            }
        }
        indexs[count] = index;
        shiftUp(count);
        count++;
    }

    public Integer peek() {
        if (count == 0) {
            throw new IllegalArgumentException("IndexMinHeap is empty.");
        }
        return indexs[0];
    }

    // 最小索引堆中返回的应该是索引值，通过索引值再去获取data中的数据
    public Integer poll() {
        if (count == 0) {
            throw new IllegalArgumentException("IndexMinHeap is empty.");
        }
        int retIndex = indexs[0];
        swapIndex(0, count - 1);
        indexs[count - 1] = null;
        count--;
        shiftDown(0);
        return retIndex;
    }

    private void swapIndex(int i, int j) {
        int temp = indexs[i];
        indexs[i] = indexs[j];
        indexs[j] = temp;
    }

    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index - 0 doesn't have parent");
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    private void shiftUp(int k) {
        while (k > 0 && data[indexs[k]].compareTo(data[indexs[parent(k)]]) < 0) {
            swapIndex(k, parent(k));
            k = parent(k);
        }
    }

    private void shiftDown(int k) {
        while (leftChild(k) < count) {
            int j = leftChild(k);
            if (j + 1 < count && data[indexs[j + 1]].compareTo(data[indexs[j]]) < 0) {
                j = rightChild(k);
            }
            if (data[indexs[j]].compareTo(data[indexs[k]]) < 0) {
                swapIndex(j, k);
                k = j;
            } else {
                break;
            }
        }
    }
}
