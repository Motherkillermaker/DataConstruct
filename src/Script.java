import WestLake.ListNode;

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

//        String word = "abcd";
//        char ch = 'z';
//        String s = reversePrefix(word, ch);
//        System.out.println(s);

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(9);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(2);
        ListNode node6 = new ListNode(4);
        ListNode node7 = new ListNode(1);
        ListNode node8 = new ListNode(8);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;


//        System.out.println("原始链表：" + node1);
//        ListNode listNode = sortList(node1);
//        System.out.println("排序后链表" + listNode);
        System.out.println(node1);
        ListNode reverseNode = reverseList(node1);
        System.out.println(reverseNode);


//        ListNode listNode = sortTwoList(node1, node5);
//        System.out.println("排序后链表"  + listNode);


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
            if (b) {
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

    public static ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        int length = 0;
        ListNode temp = head;
        ListNode result = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        for (int i = 0; i < length - k; i++) {
            result = result.next;
        }
        return result;
    }

    public static void reverseString(char[] s) {
        int low = 0;
        int high = s.length - 1;
        while (low < high) {
            char a = s[high];
            s[high] = s[low];
            s[low] = a;
            low++;
            high--;
        }
    }

    public static String reversePrefix(String word, char ch) {
        if (word == null) {
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

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        // A B 都不为空
        // 统计 两个链表的长度
        int lenA = 0;
        int lenB = 0;
        ListNode tempA = headA;
        ListNode tempB = headB;
        while (tempA != null) {
            lenA++;
            tempA = tempA.next;
        }
        while (tempB != null) {
            lenB++;
            tempB = tempB.next;
        }
        // 让长链表先走
        tempA = headA;
        tempB = headB;
        int step = lenA - lenB;
        if (step < 0) { // 链表B 长
            for (int i = 0; i < Math.abs(step); i++) {
                tempB = tempB.next;
            }
        } else if (step > 0) {     // 链表A 长
            for (int i = 0; i < Math.abs(step); i++) {
                tempA = tempA.next;
            }
        }
        while (tempA != null && tempB != null && tempA != tempB) {
            tempA = tempA.next;
            tempB = tempB.next;
        }
        return tempA;

    }

    // 待做
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode low = head.next;
        ListNode high = head;
        ListNode result = head;
        while (high != null) {
            high = high.next;
        }
        while (low != high) {

        }

    }

    public static int[] twoSum(int[] numbers, int target) {
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[]{low, high};
            } else if (sum < target) {
                low++;
            } else {
                high--;
            }
        }
        return null;

    }

    public static boolean isPalindrome(ListNode head) {
        if (head.next == null) {
            // 只有一个结点
            return true;
        }
        // 寻找到中点
        ListNode midNode = findMidNode(head);
        // 中点后面的链表反转
        ListNode reverseHead = reverseNode(midNode);
        // 比对两个链表
        while (reverseHead != null) {
            if (reverseHead.val == head.val) {
                reverseHead = reverseHead.next;
                head = head.next;
            } else {
                return false;
            }
        }
        return true;
    }

    public static ListNode findMidNode(ListNode head) {
        if (head.next == null){
            return head;
        }
        // 快慢指针寻找中间节点(基数为中间，偶数为中间两个的右边一个)
        // 初始值设为不同 => 重要
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = slow.next;
        return slow;
    }

    public static ListNode reverseNode(ListNode head) {
        // 反转链表
        if (head == null || head.next == null) {
            return null;
        }
        ListNode current = head;
        ListNode currentNext = null;
        ListNode reverseHead = new ListNode(-1);
        while (current != null) {
            // 1.保留下一结点
            currentNext = current.next;
            // 2.搬操作
            current.next = reverseHead.next;
            // 3.连操作
            reverseHead.next = current;
            // 4.指针后移
            current = currentNext;
        }
        // 5.丢弃中间节点 reverseHead
        reverseHead = reverseHead.next;
        return reverseHead;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        // 快指针先走 n 步
        int i = 0;
        while (fast != null && i < n) {
            fast = fast.next;
            i++;
        }
        if (fast == null) {
            // 删除的是第一个结点
            head = head.next;
            return head;
        }
        // 快慢指针一起走
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    public static ListNode sortTwoList(ListNode head1, ListNode head2){
        // 合并两个有序链表
        if (head1 == null && head2 == null){
            // 空链表
            return null;
        }
        // 定义新链表
        ListNode newHead = new ListNode(-1);
        // 定义指针
        ListNode temp = newHead;
        ListNode temp1 = head1;
        ListNode temp2 = head2;
        // 双指针扫描，将小数加入新链表
        while (temp1 != null && temp2 != null){
            if (temp1.val <= temp2.val){
                temp.next = temp1;
                temp = temp.next;
                temp1 = temp1.next;
            }else {
                temp.next = temp2;
                temp = temp.next;
                temp2 = temp2.next;
            }
        }
        if (temp1 != null){
            temp.next = temp1;
        }
        if (temp2 != null){
            temp.next = temp2;
        }
        // 去掉初始头结点
        newHead = newHead.next;
        return newHead;
    }

    public static ListNode sortList(ListNode head) {
        // 链表排序 => 归并排序
        // 条件判断
        if (head == null){
            return null;
        }
        if (head.next == null){
            return head;
        }
        // 链表分成两半(head、midNode)
        ListNode midNode = findMidNode(head);
        // 定义链表指针 （注：对链表的操作都需要指针）
        ListNode tempHead = head;
        while (tempHead.next != midNode){
            tempHead = tempHead.next;
        }
        tempHead.next = null;
        // 第一段链表有序
        ListNode firstList = sortList(head);
        // 第二段链表有序
        ListNode twoList = sortList(midNode);
        // 归并排序好的链表
        ListNode listNode = sortTwoList(firstList, twoList);
        return listNode;
    }

    public static void moveZeroes(int[] nums) {
        if (nums.length == 0){
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - (1 + i); j++) {
                if (nums[j] == 0){
                    swap(nums,j, j+1);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        if (root != null){
            infixOrder(root,list);
        }else {
            return list;
        }
        return list;
    }

    public static void infixOrder(TreeNode root,ArrayList<Integer> list){
        if (root.left != null){
            infixOrder(root.left,list);
        }
        list.add(root.val);
        if (root.right != null){
            infixOrder(root.right,list);
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

    public static ListNode reverseList(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null){
            //1.记录下一结点
            ListNode curNext = cur.next;
            //2.反转指针
            cur.next = pre;
            //3.往前走
            pre = cur;
            cur = curNext;
        }
        //4.pre为新链表表头 => 返回
        return pre;
    }



}







