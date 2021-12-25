package LeetCode.LinkedList;

import java.util.Arrays;

/**
 * LeetCode 21
 */
public class MergeLinkedList {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(4);
        // 链表1
        node1.next = node2;
        node2.next = node3;
        // 链表2
        node4.next = node5;
        node5.next = node6;
        System.out.println(node1);
        System.out.println(node4);
        // 合并链表
        ListNode node = mergeLinkedList(node1, node4);
        System.out.println(node);

    }


    public static ListNode mergeLinkedList(ListNode node1, ListNode node2) {
        if (node1 == null || node2 == null) {
            return node1 == null ? node2 : node1;
        }
        ListNode currNode1 = node1;         // 链表1 指针
        ListNode currNode2 = node2;         // 链表2 指针
        ListNode headNode = null;           // 新链表表头
        ListNode temp = headNode;

        while (currNode1 != null && currNode2 != null) {
            // currNode1 的值小 => 加入新链表
            if (currNode1.val <= currNode2.val) {
                // 第一次添加 => 让新链表表头为链表1表头，并将指针指向表头（注意表头不能动）
                if (headNode == null) {
                    headNode = currNode1;
                    temp = headNode;
                    currNode1 = currNode1.next;
                } else {
                    // 不是第一次添加
                    temp.next = currNode1;
                    temp = temp.next;
                    currNode1 = currNode1.next;
                }
            }
            //currNode2 的值小 => 加入新链表
            else {
                // 第一次添加
                if (headNode == null) {
                    headNode = currNode2;
                    temp = headNode;
                    currNode2 = currNode2.next;
                } else {
                    // 不是第一次添加
                    temp.next = currNode2;
                    temp = temp.next;
                    currNode2 = currNode2.next;
                }
            }
        }
        // temp2 为空，将temp1接在新链表后面
        if (currNode1 != null) {
            temp.next = currNode1;
            // temp1 为空，将temp2接在新链表后面
        } else {
            temp.next = currNode2;
        }
        return headNode;
    }


}


