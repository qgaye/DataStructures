package linkedlist;

/**
 * @author qgaye
 * @date 2019/02/20
 */
public class  LinkedListWithTail<E> {

    private class Node {

        public E e;

        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyHead;

    private Node tail;

    private Integer size;

    public Integer getSize() {
        return size;
    }

    public Boolean isEmpty() {
        return dummyHead == tail;
    }

    public LinkedListWithTail() {
        dummyHead = new Node();
        tail = dummyHead;
        size = 0;
    }

    public E getFirst() {
        return dummyHead.next.e;
    }

    public E removeFirst() {
        Node remNode = dummyHead.next;

        if (remNode == null) {
            throw new IllegalArgumentException("LinkedList is empty");
        }
        if (remNode == tail) {
            tail = dummyHead;
        }
        dummyHead.next = remNode.next;
        remNode.next = null;
        size--;
        return remNode.e;
    }

    public void addLast(E e) {
        Node addNode = new Node(e);
        tail.next = addNode;
        tail = addNode;
        size++;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("LinkedListForStack: ");
        Node cur = dummyHead.next;
        while (cur != null) {
            res.append(cur.e).append("->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}
