package heap;

/**
 * @author qgaye
 * @date 2019/03/03
 */
public interface Queue<E> {

    Integer getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();
}
