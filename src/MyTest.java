import java.util.Arrays;

/**
 * @author CJYong
 * @create 2022-01-14 20:38
 */
public class MyTest {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7};
        reverseArray(arr);
        System.out.println(Arrays.toString(arr));

    }
    public static void moveZeroes(int[] nums) {
        int numsSize = nums.length;
        int i = 0,j = 0;
        for(i = 0 ; i < numsSize; i++)
        {
            if(nums[i] != 0)
            {
                nums[j++] = nums[i];
            }
        }
        while(j < numsSize)
        {
            nums[j++] = 0;
        }
    }

    public static void moveZeroes1(int[] nums) {
        int numsSize = nums.length;
        int i = 0,j = 0;
        for(i = 0 ; i < numsSize; i++)
        {
            if(nums[i] != 0)
            {
                nums[j++] = nums[i];
            }
        }
        while(j < numsSize)
        {
            nums[j++] = 0;
        }
    }

    public static int[] reverseArray(int[] arr){
        // 双指针反转数组
        int low = 0;
        int high = arr.length - 1;
        while (low < high){
            swap(arr,low,high);
            low++;
            high--;
        }
        return arr;
    }

    public static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

}
