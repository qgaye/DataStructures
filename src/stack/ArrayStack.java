package stack;

import array.MyArray;

/**
 * @author qgaye
 * @date 2019/02/16
 */
public class ArrayStack<T> implements MyStack<T> {

    private MyArray<T> myArray;

    public ArrayStack(Integer capacity) {
        myArray = new MyArray<>(capacity);
    }

    public ArrayStack() {
        myArray = new MyArray<>();
    }

    @Override
    public Integer getSize() {
        return myArray.getSize();
    }

    @Override
    public Boolean isEmpty() {
        return myArray.isEmpty();
    }

    public Integer getCapacity() {
        return myArray.getCapacity();
    }

    @Override
    public void push(T t) {
        myArray.addLast(t);
    }

    @Override
    public void pop() {
        myArray.removeLast();
    }

    @Override
    public T peek() {
        return myArray.getLast();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append("[");
        for (int i = 0; i < myArray.getSize(); i++) {
            res.append(myArray.get(i));
            if (i != myArray.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }
}
