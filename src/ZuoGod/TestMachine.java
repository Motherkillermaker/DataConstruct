package ZuoGod;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author CJYong
 * @create 2021-12-27 22:18
 */
public class TestMachine {
    public static void main(String[] args) {
        //测试次数
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            //产生随机数组
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);

            //测试的方法
            bubbleSort(arr1);
            //绝对正确的方法
            comparator(arr2);
            //比较器
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

    private static int[] copyArray(int[] arr1) {
        int[] arr2 = new int[arr1.length];
        System.arraycopy(arr1, 0, arr2, 0, arr1.length);
        return arr2;
    }

    //实现比对的方法 ，比较两个数组的每个数是否相等
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int e = arr.length - 1; e > 0; e--) {//范围每次缩减1，因为每次都排好了一个数
            for (int i = 0; i < e; i++) {//从头到e进行两两比较
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);//（前面比后面大就进行交换）
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {//两两交换
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


}
