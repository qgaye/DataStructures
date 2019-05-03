package queue;

/**
 * @author qgaye
 * @date 2019/02/17
 */
public class Main {

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(4);

        queue.enqueue(1);
        queue.enqueue(3);
        System.out.println(queue);
        queue.enqueue(2);
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
        System.out.println(queue.getFront());
    }
}
