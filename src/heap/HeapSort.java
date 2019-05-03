package heap;

/**
 * @author qgaye
 * @date 2019/03/22
 */
public class HeapSort {

    public static void sort(int[] arr) {
        Integer[] arrs = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arrs[i] = arr[i];
        }
        MaxHeap maxHeap = new MaxHeap(arrs);
        for (int i = 0; i < arrs.length; i++) {
            System.out.println(maxHeap.extractMax());
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 3, 7, 4, 4};
        HeapSort.sort(arr);
    }

}
