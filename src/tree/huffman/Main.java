package tree.huffman;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qgaye
 * @date 2019/04/28
 */
public class Main {

    public static void main(String[] args) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        hashMap.put('A', 8);
        hashMap.put('C', 7);
        hashMap.put('B', 15);
        hashMap.put('D', 5);
        hashMap.put('E', 6);
        HuffmanTree<Character> huffmanTree = new HuffmanTree<>(hashMap);
        Map<Character, String> map = huffmanTree.encode();
        for (Character c : map.keySet()) {
            System.out.println(c + " : " + map.get(c));
        }
    }
}
