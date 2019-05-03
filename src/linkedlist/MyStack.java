package linkedlist;

/**
 * @author qgaye
 * @date 2019/02/16
 */
public interface MyStack<E> {

    Integer getSize();

    Boolean isEmpty();

    void push(E e);

    E pop();

    E peek();
}
