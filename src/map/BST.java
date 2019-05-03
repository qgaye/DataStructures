package map;

/**
 * @author qgaye
 * @date 2019/02/26
 */
public class BST<K extends Comparable<K>, V> {

    private class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }

        public Node() {
            this(null, null);
        }
    }

    private Node root;
    private Integer size;

    public BST() {
        root = null;
        size = 0;
    }

    public Integer getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            Node newNode = new Node(key, value);
            size++;
            return newNode;
        }
        if (key.equals(node.key)) {
            node.value = value;
            return node;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        }
        if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        }
        return node;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.equals(node.key)) {
            return node;
        }
        if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + "not exist.");
        } else {
            node.value = newValue;
        }
    }

    private Node minimum(Node node){
        if(node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            size --;
            node.right = null;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    public V remove(K key) {
        root = remove(root, key);
        return get(key);
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {    // key.compareTo(node.key) == 0
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            } else {    // node的左右节点都不为null
                Node successor = minimum(node.right);
                successor.right = removeMin(node.right);
                successor.left = node.left;

                node.left = node.right = null;

                return successor;
            }

        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, Integer depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth)).append("null : null\n");
            return;
        }
        res.append(generateDepthString(depth)).append(node.key).append(" : ").append(node.value).append("\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(Integer depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            if (i != depth - 1) {
                res.append("    ");
            } else {
                res.append("+---");
            }
        }
        return res.toString();
    }
}
