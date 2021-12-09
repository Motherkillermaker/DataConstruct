package LeetCode;

import org.junit.Test;

/**
 * @author CJYong
 * @create 2021-12-08 22:10
 */
public class TwoSum {

    /*
    两数之和: 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     */

    @Test
    public void test(){

        int[] arr = new int[]{1,3,5,7,9};
        int target = 16;
        int[] out = Twosum(arr, target);
        for (int i = 0; i < out.length; i++) {
            System.out.println(out[i]);
        }

    }

    public int[] Twosum(int[] arr, int target){
        int[] out = new int[2];
        for (int i = 0; i < arr.length; i++){
            for (int j = i + 1; j < arr.length; j++){
                int sum = arr[i] + arr[j];
                if ( sum == target){
                    out[0] = i;
                    out[1] = j;
                }
            }
        }
        return out;
    }

}
