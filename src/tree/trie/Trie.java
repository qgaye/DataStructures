package tree.trie;

import java.util.TreeMap;

/**
 * 前缀树/字典树
 * @author qgaye
 * @date 2019/03/15
 */
public class Trie {

    private class Node {
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private Integer size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    public void add(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            size++;
        }
    }

    public void addRec(String word) {
        addRec(word, 0, root);
    }

    private void addRec(String word, Integer index, Node cur) {
        if (index > word.length() - 1) {
            return;
        }
        if (cur.next.get(word.charAt(index)) == null) {
            cur.next.put(word.charAt(index), new Node());
        }
        cur = cur.next.get(word.charAt(index));
        if (index == word.length() - 1 && !cur.isWord) {
            cur.isWord = true;
            size++;
        }
        addRec(word, index + 1, cur);
    }

    public boolean contains(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }

    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (char c : prefix.toCharArray()) {
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }

    public Integer getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateTireString(root, '$', 0, res);
        return res.toString();
    }

    private void generateTireString(Node node, Character c, Integer depth, StringBuilder res) {
        res.append(generateDepthString(depth)).append(c).append(node.isWord ? "(W)" : "").append("\n");
        if (node.next == null) {
            return;
        }
        for (Character key : node.next.keySet()) {
            generateTireString(node.next.get(key), key,depth + 1, res);
        }
    }

    private String generateDepthString(Integer depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            if (i != depth - 1) {
                res.append("   ");
            } else {
                res.append("+--");
            }
        }
        return res.toString();
    }
}
