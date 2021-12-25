package LeetCode.LinkedList;

import java.util.*;

/** LeetCode 82 83
 * @author CJYong
 * @create 2021-12-25 13:42
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(4);
        ListNode node7 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
//        node4.next = node5;
//        node5.next = node6;
//        node6.next = node7;
        ListNode node = removeDuplicate(node1);
        System.out.println(node);


    }


    /**
     * 删除链表中重复元素，使得所有元素只出现一次
     */
    public static ListNode removeDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode temp = head;
        while (temp.next != null) {
            if (temp.val == temp.next.val) {
                if (temp.next.next == null) {
                    // 末尾元素相同
                    temp.next = null;
                    // 已经到表尾，结束循环
                    break;
                } else {
                    // 发现重复元素   => 中间情况 / 开头情况
                    temp.next = temp.next.next;
                }
            } else {
                // 相邻元素不同 => 移动指针
                temp = temp.next;
            }
        }
        return head;
    }

    /**
     * 删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现的数字
     */
    public static ListNode removeDuplicate(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode temp = head;
        // 用来保存重复元素的队列
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        // 删除重复元素
        while (temp.next != null) {
            if (temp.val == temp.next.val) {
                linkedHashSet.add(temp.val);
                if (temp.next.next == null) {
                    // 末尾元素相同
                    temp.next = null;
                    // 已经到表尾，结束循环
                    break;
                } else {
                    // 发现重复元素   => 中间情况 / 开头情况
                    temp.next = temp.next.next;
                }
            } else {
                // 相邻元素不同 => 移动指针
                temp = temp.next;
            }
        }
        // 在链表中删除队列中的元素(重复的元素)
        ListNode newhead = head;
        if (linkedHashSet.size() > 0) {
            for (Integer value : linkedHashSet) {
                newhead = delete(newhead, value);
            }
            return newhead;
        }else {
            return head;
        }
    }


    /**
     * 在链表中删除一个元素
     */
    public static ListNode delete(ListNode head, int value) {
        ListNode temp = head;
        boolean flag = false;                    // 标识是否找到被删除的结点
        if (head.val == value) {
            return head.next;
        }
        while (true) {
            if (temp.next == null) {
                // 已经遍历到表尾
                break;
            }
            if (temp.next.val == value) {
                // 找到了待删除结点 （此时 temp 指向待删除结点的前一个结点）
                flag = true;
                break;
            }
            temp = temp.next;                   // temp 后移，遍历链表
        }
        if (flag) {
            // 找到待删除结点
            temp.next = temp.next.next;
        }
        return head;
    }
}
