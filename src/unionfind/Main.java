package unionfind;

/**
 * @author qgaye
 * @date 2019/03/19
 */
public class Main {

    public static void main(String[] args) {
        UnionFindFour unionFind = new UnionFindFour(20);

        unionFind.unionElements(11, 10);
        unionFind.unionElements(10, 8);
        unionFind.unionElements(8, 9);
        System.out.println(unionFind);


        unionFind.unionElements(5, 4);
        unionFind.unionElements(2, 4);
        unionFind.unionElements(6, 3);
        unionFind.unionElements(4, 3);
        System.out.println(unionFind);

        unionFind.unionElements(3, 9);
        System.out.println(unionFind);
    }
}
