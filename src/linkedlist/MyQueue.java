package linkedlist;

/**
 * @author qgaye
 * @date 2019/02/20
 */
public interface MyQueue<E> {

    Integer getSize();

    Boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();
}
