package ZuoGod;

import java.util.Arrays;

/**
 * @author CJYong
 * @create 2021-12-26 22:26
 */
public class one {

    public static void main(String[] args) {
        int[] array = getRandomArray(10);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 交换两个数的位置 => 前提是 i 和 j 不相等
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr,int i,int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
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
