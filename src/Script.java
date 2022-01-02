import LeetCode.LinkedList.ListNode;
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
//        int[] arr = new int[]{-1,0,1,2,-1,-4,-2,-3,3,0,4};
//        List<List<Integer>> lists = threeSum(arr);
//        System.out.println(lists);

//        String s = "lrloseumgh";
//        int k = 6;
//        String s1 = reverseLeftWords(s, k);
//        System.out.println(s1);

//        String[] words = new String[]{"def","ghi"};
//        String s = firstPalindrome(words);
//        System.out.println(s);

        String word = "abcd";
        char ch = 'z';
        String s = reversePrefix(word, ch);
        System.out.println(s);


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
            while (j < k) {
                int sum = -(nums[j] + nums[k]);
                if (sum == nums[i]) {
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
                } else if (sum < nums[i]) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return lists;
    }


    public static String reverseLeftWords(String s, int n) {
        char[] s1 = s.toCharArray();
        char[] s2 = new char[s1.length];
        int l1 = n;
        int l2 = 0;
        while (l1 < s1.length) {
            s2[l2] = s1[l1];
            l1++;
            l2++;
        }
        int i = 0;
        while (l2 < s2.length) {
            s2[l2] = s1[i];
            l2++;
            i++;

        }
        return new String(s2);
    }

    public static String firstPalindrome(String[] words) {
        for (int i = 0; i < words.length; i++) {
            String s = words[i];
            boolean b = isPalindrome(s);
            if (b){
                return s;
            }
        }
        String s = "";
        return s;
    }

    public static boolean isPalindrome(String s) {
        // 首尾指针不相等时，依次遍历
        int low = 0, high = s.length() - 1;
        while (low < high) {
            if (s.charAt(low++) != s.charAt(high--)) {
                return false;
            }
        }
        return true;
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null){
            return null;
        }
        int length = 0;
        ListNode temp = head;
        ListNode result = head;
        while (temp != null){
            length++;
            temp = temp.next;
        }
        for (int i = 0; i < length - k; i++) {
            result = result.next;
        }
        return result;
    }

    public void reverseString(char[] s) {
        int low = 0;
        int high = s.length - 1;
        while (low < high){
            char a = s[high];
            s[high] = s[low];
            s[low] =a;
            low++;
            high--;
        }
    }

    public static String reversePrefix(String word, char ch) {
        if (word == null){
            return null;
        }
        char[] chars = word.toCharArray();
        int loc = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ch) {
                loc = i;
                break;
            }
        }
        int low = 0;
        int high = loc;
        while (low < high) {
            char a = chars[high];
            chars[high] = chars[low];
            chars[low] = a;
            low++;
            high--;
        }
        return new String(chars);
    }





}





