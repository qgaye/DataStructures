package tree.redblack;

/**
 * @author qgaye
 * @date 2019/03/27
 */
public class RedBlackTree2<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public K key;
        public V value;
        public Node right;
        public Node left;
        public Node parent;
        public boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            parent = null;
            // 每一个新节点都是要先做融合的
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RedBlackTree2() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmtpy() {
        return size == 0;
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    /**
     *    node                        x
     *    /  \       左旋转          /  \
     *   T1   x    --------->     node  T3
     *      /  \                 /   \
     *     T2  T3               T1   T2
     */
    private Node leftRotate(Node node) {
        Node x = node.right;

        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    /**
     *      node                     x
     *      /  \      右旋转        /  \
     *     x   T2   --------->    y   node
     *    / \                         /  \
     *   y  T1                       T1  T2
     */
    private Node rightRotate(Node node) {
        Node x = node.left;

        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    private void flipColor(Node node) {
        node.left.color = BLACK;
        node.right.color = BLACK;
        node.color = RED;
    }

    private void flipColor2(Node node) {
        node.left.color = RED;
        node.right.color = RED;
        node.color = BLACK;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK;
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
            node.left.parent = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
            node.right.parent = node;
        } else {
            node.value = value;
        }

        if (isRed(node.left)) {
            if (isRed(node.left.right) && !isRed(node.right)) {
                node.left = leftRotate(node.left);
            }
            if (isRed(node.left.left) && !isRed(node.right)) {
                node = rightRotate(node);
            }
        }

        if (isRed(node.right)) {
            if (isRed(node.right.left) && !isRed(node.left)) {
                node.right = rightRotate(node.right);
            }
            if (isRed(node.right.right) && !isRed(node.left)) {
                node = leftRotate(node);
            }
        }

        if (isRed(node.left) && isRed(node.right) && (isRed(node.left.left) || isRed(node.left.right) || isRed(node.right.left) || isRed(node.right.right))) {
            flipColor(node);
        }

        return node;
    }

//    public void remove(K key) {
//        root = remove(root, key);
//    }
//
//    private Node remove(Node node, K key) {
//        if (node == null) {
//            return null;
//        }
//
//        Node retNode;
//        if (key.compareTo(node.key) < 0) {
//            node.left = remove(node.left, key);
//        } else if (key.compareTo(node.key) > 0) {
//            node.right = remove(node.right, key);
//        } else {
//            if (isRed(node) && node.left == null && node.right == null) {
//                return null;
//            }
//            if (!isRed(node) && node.left == null && node.right == null) {
//                if (node.parent == null) {
//                    return null;
//                } else {
//                    Node father = node.parent;
//                    Node brother = father.left == node ? father.right : father.left;
//                    if (!isRed(brother) && isRed(brother.left) && brother.right == null) {
//                        brother.left = father;
//                        brother.color = BLACK;
//                        brother.
//                    }
//                }
//            }
//        }
//        return node;
//    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, Integer depth, StringBuilder res) {
        if (node == null) {
//            res.append(generateDepthString(depth)).append("null(B)\n");
            return;
        }
        res.append(generateDepthString(depth)).append(node.key).append("(").append(node.color == RED ? "R" : "B").append(")").append("\n");
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
