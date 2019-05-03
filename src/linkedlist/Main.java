package linkedlist;

/**
 * @author qgaye
 * @date 2019/02/18
 */
public class Main {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(1);
        System.out.println(linkedList);

        LinkedListWithTail<Integer> listForStack = new LinkedListWithTail<>();
        listForStack.addLast(1);
        System.out.println(listForStack);

        LinkedListStack<Integer> stack = new LinkedListStack<>();
        stack.push(1);
        stack.push(2);
        stack.pop();
        System.out.println(stack);

        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.enqueue(111);
        queue.enqueue(222);
        queue.dequeue();
        System.out.println(queue);
    }
}
