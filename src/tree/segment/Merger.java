package tree.segment;

/**
 * @author qgaye
 * @date 2019/03/05
 */
public interface Merger<E> {

    /**
     * 融合两个节点
     */
    E merge(E o1, E o2);

    // TODO: 添加单参数的merge方法，处理当节点的左索引等于右索引的情况

}
