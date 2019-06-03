package tree.fenwick;

/**
 * Fenwick Tree俗称树状数组，又称Binary Indexed Tree
 * 功能与Segment Tree线段树相似，都是解决数值动态变化的求和问题，但是树状数组更节省空间
 * 树状数组能解决的问题线段树都能解决的，反之则不能
 * 复杂度O(logn)
 *                        _________  8
 *                     /      ____ / |
 *            2 ---  4      6       /
 *          /     /       /       /
 * tree   1      3       5       7
 *        ^  ^   ^   ^   ^  ^    ^  ^
 * arr    1  2   3   4   5  6    7  8
 *
 * @author qgaye
 * @date 2019/06/03
 */
public class FenwickTree {
    // 原始数据
    private int[] nums;
    // 树状数组
    private int[] tree;

    /**
     * x的二进制表达式中最低位的1所对应的值
     */
    private int lowBit(int x) {
        return x & (-x);
    }

    public FenwickTree(int[] nums) {
        this.nums = nums;
        // 树状数组索引从1开始，1表示索引为0的和
        this.tree = new int[nums.length + 1];
        // 遍历每个值，构建树状数组
        for (int i = 0; i < nums.length; i++) {
            add(i, nums[i]);
        }
    }

    /**
     * 构建树状数组，从该节点一次向上遍历累加填充
     */
    private void add(int index, int num) {
        while (index < nums.length) {
            tree[index + 1] += num;
            index = (index + 1) + lowBit(index + 1) - 1;
        }
    }

    /**
     * 更新操作，与add相似，只是更新时每次只需加上更新造成的差值即可
     */
    public void update(int index, int num) {
        int diff = nums[index] - num;
        nums[index] = num;
        while (index < nums.length) {
            tree[index + 1] -= diff;
            index = (index + 1) + lowBit(index + 1) - 1;
        }

    }

    /**
     * 求解(0 - index)的和
     */
    private int getSum(int index) {
        int sum = 0;
        while (index >= 0){
            sum += tree[index + 1];
            index = (index + 1) - lowBit(index + 1) - 1;
        }
        return sum;
    }

    /**
     * 求解范围内的和
     */
    public int rangeSum(int indexL, int indexR) {
        if (indexL > indexR) {
            throw new IllegalArgumentException("indexL must large than indexR");
        }
        // 由于getSum计算的是到该索引的综合，因此需要indexL - 1
        return getSum(indexR) - getSum(indexL - 1);
    }
}
