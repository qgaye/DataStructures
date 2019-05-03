package linkedlist;

/**
 * @author qgaye
 * @date 2019/02/20
 */
public class LinkedListQueue<E> implements MyQueue<E> {

    private LinkedListWithTail<E> linkedList;

    public LinkedListQueue() {
        linkedList = new LinkedListWithTail<>();
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
    public void enqueue(E e) {
        linkedList.addLast(e);
    }

    @Override
    public E dequeue() {
        return linkedList.removeFirst();
    }

    @Override
    public E getFront() {
        return linkedList.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: top");
        res.append(linkedList);
        res.delete(10, 29);
        return res.toString();
    }
}
