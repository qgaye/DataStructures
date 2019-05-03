package stack;

/**
 * @author qgaye
 * @date 2019/02/16
 */
public interface MyStack<T> {

    Integer getSize();

    Boolean isEmpty();

    void push(T t);

    void pop();

    T peek();
}
