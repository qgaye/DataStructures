package set;

import java.util.ArrayList;

/**
 * @author qgaye
 * @date 2019/02/26
 */
public class Main {

    public static void main(String[] args) {
        LinkedListSet<Integer> set = new LinkedListSet<>();
        set.add(111);
        set.add(222);
        set.add(111);

        System.out.println(set);
    }
}
