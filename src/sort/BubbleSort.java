package sort;

/**
 * 冒泡排序
 * O(n^2)
 * @author qgaye
 * @date 2019/05/04
 */
public class BubbleSort {

    /**
     * 最普通的冒泡排序算法
     */
    public static void bubbleSort1(int[] arr) {
        for (int end = arr.length - 1; end > 0; end--) {
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
        }
    }

    /**
     * 引入变量isSort，当某次冒泡操作发现数组已无需交换元素时，表明数组已排序成功，直接退出冒泡循环
     */
    public static void bubbleSort2(int[] arr) {
        for (int end = arr.length - 1; end > 0; end--) {
            boolean isSort = true;
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    isSort = false;
                }
            }
            if (isSort) {
                break;
            }
        }
    }

    /**
     * 当每次冒泡操作时，若在一次交换后后面的数组不再需要交换时，表示后面的数组元素已经排序成功，此时下一次冒泡只需要冒泡到上一次冒泡操作中最后一次交换的位置
     */
    public static void bubbleSort3(int[] arr) {
        for (int end = arr.length - 1; end > 0; end--) {
            int border = 0;
            for (int i = 0; i < end; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    border = i + 1;
                }
            }
            end = border;
        }

    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
