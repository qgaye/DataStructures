package array;

/**
 * @author qgaye
 * @date 2019/02/15
 */
public class Main {

    public static void main(String[] args) {
        MyArray<Integer> myArray = new MyArray<>(4);
        myArray.addFirst(4);
        myArray.addLast(100);
        System.out.println(myArray);
        myArray.removeLast();
        System.out.println(myArray);
    }
}
