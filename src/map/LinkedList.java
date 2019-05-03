package map;

/**
 * @author qgaye
 * @date 2019/02/26
 */
public class LinkedList<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value) {
            this(key, value, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + " : " + value.toString();
        }
    }

    private Node dummyHead;
    private Integer size;

    public LinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    public Integer getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Node getNode(K key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    public boolean contains(K key) {
        return getNode(key) != null;
    }

    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    public void add(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            Node newNode = new Node(key, value, dummyHead.next);
            dummyHead.next = newNode;
            size++;
        } else {
            node.value = value;
        }
    }

    public void set(K key, V newValue) {
        Node node = getNode(key);
        if (node == null) {
            throw new IllegalArgumentException(key + "not exist.");
        }
        node.value = newValue;
    }

    public V remove(K key) {
        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.key.equals(key)) {
                Node remNode = prev.next;
                prev.next = remNode.next;
                remNode.next = null;
                size--;
                return remNode.value;
            }
            prev = prev.next;
        }
        throw new IllegalArgumentException(key + "not exist.");
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("LinkedList: ");
        Node cur = dummyHead.next;
        while (cur != null) {
            res.append(cur).append(" -> ");
            cur = cur.next;
        }
        res.append("NULL : NULL");
        return res.toString();
    }
}

