package tree.huffman;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 赫夫曼树
 * TODO: 权值weight也应该是泛型，此时合并生成parent子树时的合并操作需要自定义接口以实现
 * @author qgaye
 * @date 2019/04/28
 */
public class HuffmanTree<E> {

    private class Node<E> implements Comparable<Node> {
        // 存储的数据
        public E data;
        // 权值
        public int weight;
        // Huffman编码中的二进制码
        public String code;
        public Node left;
        public Node right;

        public Node(E data, int weight) {
            this.data = data;
            this.weight = weight;
            this.code = "";
            this.left = null;
            this.right = null;
        }

        public Node(int weight) {
            this(null, weight);
        }

        @Override
        public int compareTo(Node another) {
            return weight - another.weight;
        }
    }

    private Node root;

    /**
     * HuffmanTree主要作用是生成Haffman编码，故传入的值应是一次性的，故不准备add方法
     */
    public HuffmanTree(Map<E, Integer> maps) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (E e : maps.keySet()) {
            priorityQueue.add(new Node(e, maps.get(e)));
        }

        // 最小的两棵子树合并成一棵子树，然后再将这棵子树加入最小堆中
        while (priorityQueue.size() > 1) {
            Node parent = merge(priorityQueue.poll(), priorityQueue.poll());
            priorityQueue.offer(parent);
        }

        root = priorityQueue.poll();
    }

    /**
     * 返回huffman编码
     */
    public Map<E, String> encode() {
        HashMap<E, String> hashMap = new HashMap<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            // 当左右节点不为空时，将左右节点入队，并更新左右节点的code编码
            if (node.left != null) {
                queue.offer(node.left);
                node.left.code += node.code;
            }
            if (node.right != null) {
                queue.offer(node.right);
                node.right.code += node.code;
            }
            // 当左右节点都为空，表示是叶子节点，叶子节点当code为待返回的huffman编码
            if (node.left == null && node.right == null) {
                hashMap.put((E)node.data, node.code);
            }
        }
        return hashMap;
    }

    private Node<E> merge(Node left, Node right) {
        Node parent = new Node(left.weight + right.weight);
        parent.left = left;
        parent.right = right;
        // 将左节点设为0，右节点设为1
        parent.left.code = "0";
        parent.right.code = "1";
        return parent;
    }
}
