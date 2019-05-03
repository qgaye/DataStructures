package unionfind;

/**
 * @author qgaye
 * @date 2019/03/19
 */
public interface UF {

    Integer getSize();

    boolean isConnected(int p, int q);

    void unionElements(int p, int q);

}
