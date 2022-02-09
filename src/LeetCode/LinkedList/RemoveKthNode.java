package LeetCode.LinkedList;

import WestLake.ListNode;

/**
 * LeetCode 19
 */
public class RemoveKthNode {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(4);
        // 链表1
        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
        // 删除结点测试
        ListNode head = removeNthFromEnd(node1, 2);
        System.out.println(head);


    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            // 链表为空，直接返回
            return null;
        }
        int length = getLength(head);
        if (n <= 0 || n > length) {
            // n 的值输入不合法
            return null;
        }
        if (length == 1) {
            //链表长度为 1 => 返回空表
            head = null;
            return head;
        }
        ListNode current = head;
        // length - n 时刚好指向被删除节点（删除的话要让指针指向待删除结点的前一个结点）
        int k = length - n - 1;
        if (k < 0){
            // 删除头节点 => 开头情况
            current = current.next;
            head = current;
            return head;
        }
        // k >= 0   =>  删除的不是头节点
        for (int i = 0; i < length - n - 1; i++) {
            current = current.next;
        }
        // 循环结束指针指向待删除结点的前一个结点
        if (current.next.next == null){
            // 删除的是最后一个结点 => 结尾的情况
            current.next = null;
        }else {
            // 不是删除最后一个结点 => 中间的情况
            current.next = current.next.next;
        }
        return head;
    }

    // 获取单链表节点的个数
    public static int getLength(ListNode head) {
        if (head == null) {
            return 0;
        }
        int length = 0;                    // 定义一个 num 统计有效节点的个数
        ListNode currentNode = head;
        while (currentNode != null) {
            length++;
            currentNode = currentNode.next;
        }
        return length;
    }


}
