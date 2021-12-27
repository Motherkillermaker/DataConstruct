package LeetCode.Array;

/**
 * LeetCode 35
 */
public class SearchInSertPosition {

    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (target < nums[0]){
                return 0;
            }
            if (nums[i] >= target){
                return i;
            }
        }
        return nums.length;
    }
}
