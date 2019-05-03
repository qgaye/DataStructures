package tree.trie;

/**
 * @author qgaye
 * @date 2019/03/15
 */
public class Main {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.addRec("aaa");
        trie.addRec("aaaacc");
        trie.addRec("abb");
        trie.addRec("abc");
        trie.addRec("ccc");
        trie.addRec("cas");
        System.out.println(trie);
    }
}
