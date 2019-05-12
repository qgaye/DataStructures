package sort;

/**
 * 插入排序
 * O(n^2)
 * @author qgaye
 * @date 2019/05/04
 */
public class InsertSort {

    /**
     * 将一个未排序的元素插入到已排序好的列中的正确位置
     * 当元素比前一个元素小的时候，意味着需要插入到前面，此时比待插入元素大的排好序的元素都需要向后移一格
     */
    public static void insertSort1(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j;
            for (j = i - 1; j >= 0 && arr[j] > key; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = key;
        }
    }

    /**
     * 使用swap函数交换，插入过程类似冒泡
     */
    public static void insertSort2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j - 1] > arr[j]; j--) {
                swap(arr, j, j - 1);
            }
        }    
    }

    /**
     * 使用二分查找法来找到元素待插入的位置
     * 但我感觉这个算法对效率没有任何改进
     */
    public static void insertSort3(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int L = 0;
            int R = i - 1;
            // 此处L=R时也进入循环，为了保证L最后就是带插入位置
            while (L <= R) {
                int mid = L + (R - L) / 2;
                if (arr[mid] > key) {
                    R = mid - 1;
                } else {
                    L = mid + 1;
                }
            }
            for (int j = i - 1; j >= L; j--) {
                arr[j + 1] = arr[j];
            }
            arr[L] = key;
        }
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
