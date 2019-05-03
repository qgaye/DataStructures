package graph.weighted;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author qgaye
 * @date 2019/04/26
 */
public class MinHeap<E extends Comparable<E>> {

    private ArrayList<E> data;

    public MinHeap(int capacity) {
        data = new ArrayList<>(capacity);
    }

    public MinHeap() {
        this(10);
    }

    public MinHeap(E[] arr) {
        this(arr.length);
        data.addAll(Arrays.asList(arr));
        // 从最后一个非叶子节点开始做下沉(siftDown)
        for (int i = parent(data.size() - 1); i >= 0 ; i--) {
            shiftDown(i);
        }

    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index 0 doesn't have parent");
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    public void offer(E e) {
        data.add(e);
        shiftUp(data.size() - 1);
    }

    public void update(int index, E e) {
        if (index < 0 || index >= data.size()) {
            throw new IllegalArgumentException("index - " + index + " is illegal.");
        }
        data.set(index, e);
        shiftUp(index);
        shiftUp(index);
    }

    public E get(int index) {
        if (index < 0 || index >= data.size()) {
            throw new IllegalArgumentException("index - " + index + " is illegal.");
        }
        return data.get(index);
    }

    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return data.get(0);
    }

    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E ret = data.get(0);
        swap(0, data.size() - 1);
        data.remove(data.size() - 1);
        shiftDown(0);
        return ret;
    }

    private void swap(int i, int j) {
        E temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }

    private void shiftUp(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) > 0) {
            swap(k, parent(k));
            k = parent(k);
        }
    }

    private void shiftDown(int k) {
        while (leftChild(k) < data.size()) {
            int j = leftChild(k);
            if (j + 1 < data.size() && data.get(j + 1).compareTo(data.get(j)) < 0) {
                j = rightChild(k);
            }
            if (data.get(k).compareTo(data.get(j)) > 0) {
                swap(k, j);
                k = j;
            } else {
                break;
            }
        }
    }
}
