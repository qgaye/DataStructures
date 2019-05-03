package tree.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二分搜索树
 * @author qgaye
 * @date 2019/02/22
 */
public class BST<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left;
        public Node right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
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

    public Boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        if (root == null) {
            root = new Node(e);
            size++;
        } else {
            add(root, e);
        }
    }

    /**
     * 递归
     */
    private void add(Node node, E e) {
        if (e.equals(node.e)) {
            return;
        }
        if (e.compareTo(node.e) < 0 && node.left == null) {
            node.left = new Node(e);
            size++;
            return;
        }
        if (e.compareTo(node.e) > 0 && node.right == null) {
            node.right = new Node(e);
            size++;
            return;
        }

        if (e.compareTo(node.e) < 0) {
            add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            add(node.right, e);
        }
    }

    public void addEasy(E e) {
        root = addEasy(root, e);
    }

    /**
     * 递归,更精炼
     */
    private Node addEasy(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = addEasy(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = addEasy(node.right, e);
        }
        return node;
    }

    /**
     * 线性
     */
    public void addLinear(E e) {
        Node prev = root;
        while (true) {
            if (prev == null) {
                root = new Node(e);
                break;
            }
            if (e.equals(prev.e)) {
                break;
            }
            if (e.compareTo(prev.e) < 0) {
                if (prev.left == null) {
                    prev.left = new Node(e);
                    break;
                } else {
                    prev = prev.left;
                    continue;
                }
            }
            if (e.compareTo(prev.e) > 0) {
                if (prev.right == null) {
                    prev.right = new Node(e);
                    break;
                } else {
                    prev = prev.right;
                    continue;
                }
            }
        }
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            return contains(node.right, e);
        } else {   // e == next.e
            return true;
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历 -> 值由大到小遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 非递归的前序遍历
     * 也称深度优先遍历
     */
    public void preOrderLinaer() {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            System.out.println(cur.e);
            // 必须先插入右节点
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    /**
     * 层序遍历
     * 也称广度优先遍历
     */
    public void levelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();

            System.out.println(cur.e);
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
    }

    /**
     * 寻找最小元素
     */
    public E minimum() {
        if (size.equals(0)) {
            throw new IllegalArgumentException("BST is empty");
        }
        return minimum(root).e;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 寻找最大元素
     */
    public E maximum() {
        if (size.equals(0)) {
            throw new IllegalArgumentException("BST is empty");
        }
        return maximum(root).e;
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    /**
     * 删除最小元素
     */
    public E removeMin() {
        E rem = minimum();
        root = removeMin(root);
        return rem;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public E removeMax() {
        E rem = maximum();
        root = removeMax(root);
        return rem;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {   // e.compareTo(next.e) == 0
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
            } else {
                // 左右节点都有元素
                // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
                // 用这个节点顶替待删除节点的位置
                Node successor = minimum(node.right);
                successor.right = removeMin(node.right);
                successor.left = node.left;

                node.left = null;
                node.right = null;
                return successor;
            }
        }
    }

    public Integer calculateSize() {
        return calculateSize(root);
    }

    private Integer calculateSize(Node node) {
        if (node == null) {
            return 0;
        }
        return calculateSize(node.left) + calculateSize(node.right) + 1;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    private void generateBSTString(Node node, Integer depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth)).append("null\n");
            return;
        }
        res.append(generateDepthString(depth)).append(node.e).append("\n");
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
