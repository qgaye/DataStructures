package queue;

/**
 * @author qgaye
 * @date 2019/02/17
 */
public class LoopQueue<T> implements MyQueue<T> {

    private T[] data;
    private Integer front;
    private Integer tail;
    private Integer size;

    public LoopQueue(Integer capacity) {
        data = (T[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    @Override
    public Integer getSize() {
        return size;
    }

    @Override
    public Boolean isEmpty() {
        return front.equals(tail);
    }

    public Integer getCapacity() {
        return data.length - 1;
    }


    @Override
    public void enqueue(T t) {
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        data[tail] = t;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public void dequeue() {
        if (isEmpty()) {
            return;
        }
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
    }

    @Override
    public T getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty");
        }
        return data[front];
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: getSize = %d, capacity = %d\n", size, getCapacity()));
        res.append("front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    private void resize(Integer newCapacity) {
        T[] newData = (T[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }
}
