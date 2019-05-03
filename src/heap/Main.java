package heap;

/**
 * @author qgaye
 * @date 2019/03/03
 */
public class Main {

    public static void main(String[] args) {
        IndexMinHeap<Integer> indexMinHeap = new IndexMinHeap<>(8);
        indexMinHeap.update(0, 100);
        indexMinHeap.update(1, 50);
        indexMinHeap.update(7, 1);
        System.out.println(indexMinHeap.poll());
        indexMinHeap.update(3, 200);
        indexMinHeap.update(4, 5);
        System.out.println(indexMinHeap.poll());
        indexMinHeap.update(1, 300);
        indexMinHeap.update(2, 66);
        System.out.println(indexMinHeap);

    }
}
