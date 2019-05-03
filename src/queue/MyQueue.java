package queue;

/**
 * @author qgaye
 * @date 2019/02/17
 */
public interface MyQueue<T> {

    Integer getSize();

    Boolean isEmpty();

    void enqueue(T t);

    void dequeue();

    T getFront();
}
