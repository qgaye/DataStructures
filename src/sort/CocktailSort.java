package sort;

/**
 * 鸡尾酒排序
 * 也称作定向冒泡排序
 * O(n^2)
 * @author qgaye
 * @date 2019/05/04
 */
public class CocktailSort {

    /**
     * 同时从两边开始冒泡，从低到高，再从高到低
     */
    public static void cocktailSort(int[] arr) {
        int L = 0;
        int R = arr.length - 1;
        while (L < R) {
            for (int i = L; i < R; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
            R--;
            for (int i = R; i > L; i--) {
                if (arr[i] < arr[i - 1]) {
                    swap(arr, i, i - 1);
                }
            }
            L++;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
