package array;

/**
 * 动态数组
 * @author qgaye
 * @date 2019/02/15
 */
public class MyArray<E> {

    private E[] data;
    private Integer size;

    public MyArray(Integer capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public MyArray() {
        this(10);
    }

    public MyArray(E[] arr) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        size = data.length;
    }

    /**
     * 获取数组中元素个数
     */
    public Integer getSize() {
        return size;
    }

    /**
     * 获取数组的容量
     */
    public Integer getCapacity() {
        return data.length;
    }

    public Boolean isEmpty() {
        return size == 0;
    }

    public void  addLast(E element) {
        add(size, element);
    }

    public void addFirst(E element) {
        add(0, element);
    }

    public void add(Integer index, E element) {
        if (size == data.length) {
            resize(data.length * 2);
        }
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index > getSize");
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = element;
        size++;
    }

    public E get(Integer index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal");
        }
        return data[index];
    }

    public E getLast() {
        return get(size - 1);
    }

    public E getFirst() {
        return get(0);
    }

    public void set(Integer index, E element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index is illegal");
        }
        data[index] = element;
    }

    public Integer find(E element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public Boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    public void removeElement(E element) {
        Integer index = find(element);
        if (!index.equals(-1)) {
            throw new IllegalArgumentException("Find element failed. Element not exist");
        } else {
            remove(index);
        }
        if (size < data.length / 2) {
            resize(data.length / 2);
        }
    }

    public void remove(Integer index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal");
        }
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        data[size] = null;
        if (size < data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2); 
        }
    }

    public void removeFirst() {
        if (size == 0) {
            return;
        }
        remove(0);
    }

    public void removeLast() {
        if (size == 0) {
            return;
        }
        remove(size - 1);
    }

    public void swap(Integer i, Integer j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        E e = data[i];
        data[i] = data[j];
        data[j] = e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: getSize = %d, capacity = %d\n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }

    private void resize(Integer newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

}
