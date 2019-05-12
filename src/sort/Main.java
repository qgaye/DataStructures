package sort;

/**
 * @author qgaye
 * @date 2019/05/04
 */
public class Main {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 6, 2, -1, 3, 5, 2, 5};
//        BubbleSort.bubbleSort3(arr);
//        CocktailSort.cocktailSort(arr);
//        SelectSort.selectSort(arr);
        InsertSort.insertSort3(arr);
        printArr(arr);
    }

    private static void printArr(int[] arr) {
        for (int i : arr) {
            System.out.printf("%d ", i);
        }
    }

}
