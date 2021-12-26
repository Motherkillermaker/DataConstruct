package ZuoGod;

import java.util.Arrays;

/**
 * @author CJYong
 * @create 2021-12-26 22:34
 */
public class two {
    public static void main(String[] args) {
        int[] array = getRandomArray(10);
        System.out.println(Arrays.toString(array));
        mergeSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void mergeSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        process(arr,0,arr.length - 1);
    }

    /**
     * 递归过程
     * @param arr 待排序数组
     * @param L 需要排序的起始位置
     * @param R 需要排序的结尾位置
     */
    public static void process(int[] arr,int L,int R){
        if (L == R){
            // 一个数 => 直接返回
            return;
        }
        // 求取中点位置 L + (R - L) / 2
        int mid = L + ((R - L) >> 1);
        process(arr,L,mid);
        process(arr,mid + 1,R);
        merge(arr,L,mid,R);
    }

    /**
     * 合并两个有序数组（递归后的合并过程）
     * @param arr 数组
     * @param L 左边位置
     * @param M 第一个数组的最后一个位置
     * @param R 第二个数组的最后一个位置
     */
    public static void merge(int[] arr,int L,int M,int R){
        int[] help = new int[R - L + 1];
        // help 数组指针
        int i = 0;
        // 左侧数组指针
        int p1 = L;
        // 右侧数组指针
        int p2 = M + 1;
        // p1 和 p2 都没越界时
        while (p1 <= M && p2 <= R){
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++]:arr[p2++];
        }
        // p2 越界 => 将 p1 剩余部分拷贝进数组
        while (p1 <= M){
            help[i++] = arr[p1++];
        }
        // p1 越界 => 将 p2 剩余部分拷贝进数组
        while (p2 <= R){
            help[i++] = arr[p2++];;
        }
        // 将新数组替换原来数组
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
    }

    /**
     * 获取随机数组
     * @param maxSize
     * @return
     */
    public static int[] getRandomArray(int maxSize){
        int[] array = getArray(maxSize);
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * array.length * 10 );
        }
        return array;
    }

    /**
     * 获取数组
     * @param maxSize
     * @return
     */
    public static int[] getArray(int maxSize){
        return new int[maxSize];
    }

}
