package queue;

import array.MyArray;

/**
 * @author qgaye
 * @date 2019/02/17
 */
public class ArrayQueue<T> implements MyQueue<T> {

    private MyArray<T> myArray;

    public ArrayQueue(Integer capacity) {
        myArray = new MyArray<>(capacity);
    }

    public ArrayQueue() {
        myArray = new MyArray<>();
    }

    @Override
    public Integer getSize() {
        return myArray.getSize();
    }

    public Integer getCapacity() {
        return myArray.getCapacity();
    }

    @Override
    public Boolean isEmpty() {
        return myArray.isEmpty();
    }

    @Override
    public void enqueue(T t) {
        myArray.addLast(t);
    }

    @Override
    public void dequeue() {
        myArray.removeFirst();
    }

    @Override
    public T getFront() {
        return myArray.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for (int i = 0; i < myArray.getSize(); i++) {
            res.append(myArray.get(i));
            if (i != myArray.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }
}
