package stack;

/**
 * @author qgaye
 * @date 2019/02/16
 */
public class Main {

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>(4);

        stack.push(1);

        System.out.println(stack);

        stack.pop();
        stack.pop();

        System.out.println(stack);
    }

}
