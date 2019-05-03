package heap;

import array.MyArray;

/**
 * @author qgaye
 * @date 2019/03/01
 */
public class MaxHeap<E extends Comparable<E>> {

    private MyArray<E> data;

    public MaxHeap(Integer capacity) {
        data = new MyArray<>(capacity);
    }

    public MaxHeap() {
        data = new MyArray<>();
    }

    public MaxHeap(E[] arr) {
        data = new MyArray<>(arr);
        // 从最后一个非叶子节点开始做下沉(siftDown)
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public Integer getSize() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    private Integer parent(Integer index) {
        if (index.equals(0)) {
            throw new IllegalArgumentException("index-0 doesn't have parent");
        }
        return (index - 1) / 2;
    }

    private Integer leftChild(Integer index) {
        return index * 2 + 1;
    }

    private Integer rightChild(Integer index) {
        return index * 2 + 2;
    }

    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private void siftUp(Integer k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    public E findMax() {
        if (data.getSize().equals(0)) {
            throw new IllegalArgumentException("Heap is empty");
        }
        return data.get(0);
    }

    public E extractMax() {
        E rem = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return rem;
    }

    private void siftDown(Integer k) {
        while (leftChild(k) < data.getSize()) {
            Integer j = leftChild(k);
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j = rightChild(k);
            }
            if (data.get(k).compareTo(data.get(j)) < 0) {
                data.swap(k, j);
                k = j;
            } else {
                break;
            }
        }
    }

    /**
     * 取出堆中最大元素，并替换为e
     */
    public E replace(E e) {
        E re = findMax();
        data.set(0, e);
        siftDown(0);
        return re;
    }
}
