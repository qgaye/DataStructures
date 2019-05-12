package sort;

/**
 * 选择排序
 * O(n^2)
 * @author qgaye
 * @date 2019/05/04
 */
public class SelectSort {

    /**
     * 从所有未被排序的元素中选择最小的元素，依次和第一个第二个...做交换，从而得到最小的在最前面的排序结果
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
