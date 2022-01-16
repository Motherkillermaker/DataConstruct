package WestLake.LinkedList;

import LeetCode.LinkedList.ListNode;

import java.util.ArrayList;
import java.util.Currency;

/**
 * @author CJYong
 * @create 2022-01-16 21:17
 */
public class LinkedList {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        ArrayList<ListNode> reverse = reverse(node1, node4, null);
        ListNode node = reverse.get(0);
        System.out.println(node);

    }

    /**
     * 反转链表(双指针思想)
     * @param head：头结点
     * @param tail：尾结点
     * @param terminal：尾结点后面的结点
     * @return
     */
    public static ArrayList<ListNode> reverse(ListNode head, ListNode tail,ListNode terminal){
        ArrayList<ListNode> listNodes = new ArrayList<>();
        ListNode cur = head;
        ListNode pre = null;
        while (cur != terminal){
            //1.记录下一结点
            ListNode curNext = cur.next;
            //2.反转指针
            cur.next = pre;
            //3.往前走
            pre = cur;
            cur = curNext;
        }
        // 依次添加头结点和尾结点
        listNodes.add(tail);
        listNodes.add(head);
        return listNodes;
    }

    /**
     * 反转链表(头插法)
     * @param head
     * @return
     */
    public static ListNode reverse(ListNode head) {
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

    public static void headInsert(ListNode head,ListNode node){
        // 头插法
        ListNode temp = head;
        //1.头结点后所有节点搬至新节点后面
        node.next = temp.next;
        //2.新节点接在头节点后面
        temp.next = node;
    }

    public static void tailInsert(ListNode head,ListNode node){
        // 尾插法
        ListNode temp = head;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = node;
    }

    public static void beforeInsert(ListNode head,ListNode node){
        // temp为待插入位置的前一元素
        ListNode temp = head;
        while (temp.next != null && temp.next != node){
            temp = temp.next;
        }
        if (temp.next == null){
            System.out.println("无法找到指定元素");
        }else {
            // 记录当前元素防止丢失
            ListNode cur = temp.next;
            temp.next = node;
            node.next = cur;
        }
    }

    public static void afterInsert(ListNode head,ListNode node){
        // temp为待插入位置的前一元素
        ListNode temp = head;
        while (temp.next != null && temp != node){
            temp = temp.next;
        }
        if (temp.next == null){
            System.out.println("无法找到指定元素");
        }else {
            ListNode cur = temp.next;
            temp.next = node;
            node.next = cur;
        }
    }

    public static void delete(ListNode head,ListNode node){
        if (head == null){
            return;
        }
        // temp为待删除位置的前一元素
        ListNode temp = head;
        while (temp.next != null && temp.next != node){
            temp = temp.next;
        }
        if (temp.next == null){
            System.out.println("无法找到待删除元素");
        }else {
            temp.next = temp.next.next;
        }
    }

    public static void update(ListNode head,ListNode node,int value){
        if (head == null){
            return;
        }
        ListNode temp = head;
        while (temp.next != null && temp.val != node.val){
            temp = temp.next;
        }
        if (temp.next == null){
            System.out.println("无法找到待修改节点");
        }else {
            temp.val = value;
        }
    }

    public static void showList(ListNode head){
        if (head == null){
            return;
        }
        ListNode temp = head;
        while (temp.next != null){
            System.out.print(temp.val + " => ");
            temp = temp.next;
        }
    }

    public static void showListRecursion(ListNode head){
        if (head == null){
            return;
        }
        System.out.println(head.val + " => ");
        showListRecursion(head.next);
    }






}
