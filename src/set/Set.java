package set;

/**
 * @author qgaye
 * @date 2019/02/26
 */
public interface Set<E> {

    Integer getSize();

    boolean isEmpty();

    void add(E e);

    void remove(E e);

    boolean contains(E e);
}
