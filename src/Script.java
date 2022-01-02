import com.sun.deploy.util.StringUtils;

import javax.swing.*;
import java.util.*;

/**
 * LeetCode 15
 *
 * @title: Script
 * @Author Tan
 * @Date: 2021/12/13 13:14
 * @Version 1.0
 */
public class Script {
    public static void main(String[] args) {
//        int[] arr = new int[]{-1,0,1,2,-1,-4};
        int[] arr = new int[]{-1,0,1,2,-1,-4,-2,-3,3,0,4};
        List<List<Integer>> lists = threeSum(arr);
        System.out.println(lists);


    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null) {
            return null;
        }
        HashSet<String> hashSet = new HashSet<>();
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // 固定一个元素 在剩下的元素中双指针查找
            int j = i + 1;                  // 头指针
            int k = nums.length - 1;        // 尾指针
            while (j < k){
                int sum = - (nums[j] + nums[k]);
                if (sum == nums[i]){
                    // 存在一个 a + b = - c
                    ArrayList<Integer> integers = new ArrayList<>();
                    integers.add(nums[i]);
                    integers.add(nums[j]);
                    integers.add(nums[k]);
                    // 排序
                    Collections.sort(integers);
                    // 转为字符串
                    String a = String.valueOf(integers.get(0));
                    String b = String.valueOf(integers.get(1));
                    String c = String.valueOf(integers.get(2));
                    String s = a + b + c;
                    // 结果没有重复则添加进结果集
                    if (!hashSet.contains(s)) {
                        lists.add(integers);
                        hashSet.add(s);
                    }
                }else if (sum < nums[i]){
                    j++;
                }else {
                    k--;
                }
            }
        }
        return lists;
    }


}





