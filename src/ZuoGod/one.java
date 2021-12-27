package ZuoGod;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author CJYong
 * @create 2021-12-26 22:26
 */
public class one {

    public static void main(String[] args) {
        int[] array = new int[]{5, 6, 2, 3, 4, 1, 9, 10, 12, 5, 3, 8, 5};
        System.out.println("测试单指针");
        arrange(array, 5);
        System.out.println(Arrays.toString(array));
        System.out.println("测试双指针");
        sortColors(array, 5);
        System.out.println(Arrays.toString(array));
        System.out.println("测试快速排序");
//        QuickSort(array);
        System.out.println(Arrays.toString(array));
        System.out.println("测试选择排序");
//        selectionSort(array);
        System.out.println(Arrays.toString(array));
        System.out.println("测试插入排序");
        insertionSort(array);
        System.out.println(Arrays.toString(array));

    }

    // 插入排序
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //有多少张新牌需要插入，每次插入一张牌
        for (int i = 1; i < arr.length; i++) {
            //从排好序的最后面依次往前开始比较，新牌小就换到比较牌的前面
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    // 选择排序
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //范围每次缩小1，从前往后缩。
        for (int i = 0; i < arr.length - 1; i++) {
            //找范围内最小值，最开始默认是第一个
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                //是否比目前最小值还小，如果是，则交换，否则不交换；
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            //将最小值放到范围的第一个数
            swap(arr, i, minIndex);
        }
    }

    /**
     * 单指针
     * 给定一个数组arr，和一个数num，请把小于等于num的数放在数组的左边，大于num的数放在数组的右边。
     * 要求额外空间复杂度O(1)，时间复杂度O(N)。
     *
     * @param arr
     * @param num
     */
    public static void arrange(int[] arr, int num) {
        // 初始 x 区域的下标 => 指针
        int less = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= num) {
                // 前++是先自加再使用, 而后++是先使用再自加
                // x 先 + 1， 然后与 i 位置上的值交换 =>  i 与 x 的下一个位置进行交换
                swap(arr, i, ++less);
            }
        }
    }

    /** 双指针
     * 给定一个数组arr，和一个数num，请把小于等于num的数放在数组的左边，大于num的数放在数组的右边，等于num的数放中间
     *
     * @param arr
     * @param n
     */
    public static void sortColors(int[] arr, int n) {
        // 小于区域指针
        int less = -1;
        // 大于区域指针
        int more = arr.length;
        // 数组指针
        int i = 0;
        // 指针遇到大于区域的前一个位置 => 停止循环
        while (i < more) {
            // 当前数字大于给定数字，数字与大于区域的下一个交换
            if (arr[i] > n) {
                swap(arr, i, --more);
            } else if (arr[i] < n) {
                // 当前数字小于给定数字，数字与小于区域的下一个交换
                swap(arr, i, ++less);
            } else {
                // 当前数组等于给定数字，指针向后移动
                i++;
            }
        }
    }

    public static void QuickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        qicksortV2(arr, 0, arr.length - 1);
    }

    public static void qicksortV2(int[] arr, int L, int R) {
        // 双指针基础上 左右递归进行
        if (L < R) {
            //先随机取出一个数放到最后(取出下标)
            int randloc = (int) (Math.random() * (R - L + 1));
            swap(arr, L + randloc, R);
            // 局部确定中枢 （0位置为第一个中枢位置，1位置为最后一个中枢位置）
            int[] pivotloc = partition(arr, L, R);
            // 局部左边有序
            qicksortV2(arr, L, pivotloc[0] - 1);
            // 局部右边有序
            qicksortV2(arr, pivotloc[1] + 1, R);
        }
    }

    /**
     * 快速排序确定中枢位置 （中枢开始的位置和结束的位置） => 双指针经典用法
     *
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public static int[] partition(int[] arr, int low, int high) {
        // 小于区域指针
        int less = low - 1;
        // 大于区域指针
        int more = high;
        // low为数组指针
        while (low < more) {
            // 当前数字大于给定数字，当前数字与大于区域的下一个交换
            if (arr[low] > arr[high]) {
                // 前++是先自加再使用, 而后++是先使用再自加
                swap(arr, low,--more);
            } else if (arr[low] < arr[high]) {
                // 当前数字小于给定数字，当前数字与小于区域的下一个交换，指针向前移动
                swap(arr, low++, ++less);
            } else {
                // 当前数字等于给定数字，指针向后移动
                low++;
            }
        }
        // 循环结束，less指向中枢的前一个位置，more指向
        swap(arr,more,high);
        return new int[]{less + 1, more};
    }


    /**
     * 交换两个数的位置 => 前提是 i 和 j 不相等
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    /**
     * 获取随机数组
     *
     * @param maxSize
     * @return
     */
    public static int[] getRandomArray(int maxSize) {
        int[] array = getArray(maxSize);
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * array.length);
        }
        return array;
    }

    /**
     * 获取数组
     *
     * @param maxSize
     * @return
     */
    public static int[] getArray(int maxSize) {
        return new int[maxSize];
    }

}
