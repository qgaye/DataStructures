package linkedlist;

/**
 * @author qgaye
 * @date 2019/02/20
 */
public class LinkedListStack<E> implements MyStack<E> {

    private LinkedList<E> linkedList;

    public LinkedListStack() {
        linkedList = new LinkedList<>();
    }

    @Override
    public Integer getSize() {
        return linkedList.getSize();
    }

    @Override
    public Boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return linkedList.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: top ");
        res.append(linkedList);
        res.delete(11, 23);
        return res.toString();
    }
}
