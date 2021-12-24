package LeetCode.LinkedList;

import java.util.Arrays;

public class MergeLinkedList {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(6);
        ListNode node5 = new ListNode(7);
        ListNode node6 = new ListNode(8);
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
        ListNode currNode1 = node1;
        ListNode currNode2 = node2;
        ListNode currNode = null;
        while (currNode1 != null && currNode2 != null) {
            // currNode1 的值小 => 加入新链表
            if (currNode1.val <= currNode2.val) {
                // 第一次添加
                if (currNode == null) {
                    currNode = currNode1;
                    currNode1 = currNode1.next;
                } else {
                    // 不是第一次添加
                    currNode.next = currNode1;
                    currNode = currNode.next;
                    currNode1 = currNode1.next;
                }
                //currNode2 的值小 => 加入新链表
            } else {
                // 第一次添加
                if (currNode == null) {
                    currNode = currNode2;
                    currNode2 = currNode2.next;
                } else {
                    // 不是第一次添加
                    currNode.next = currNode2;
                    currNode = currNode.next;
                    currNode1 = currNode1.next;
                }
            }
        }
        // temp2 为空，将temp1接在新链表后面
        if (currNode1 != null) {
            currNode.next = currNode1;
            // temp1 为空，将temp2接在新链表后面
        } else {
            currNode.next = currNode2;
        }
        return currNode;
    }


}


