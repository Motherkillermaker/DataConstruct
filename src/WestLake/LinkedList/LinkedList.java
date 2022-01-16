package WestLake.LinkedList;

import LeetCode.LinkedList.ListNode;

import java.lang.annotation.Target;
import java.util.*;

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
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        HashMap<ListNode, ListNode> map = new HashMap<>();

        ArrayList<ListNode> reverse = reverse(node3, node5, node6);
        ListNode header = reverse.get(0);
        System.out.println(header);
        System.out.println(node1);

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

    /**
     * 反转指定位置链表
     * @param head: 链表头结点
     * @param left：反转链表头结点
     * @param right：反转链表尾结点
     * @return 链表头
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
            // 只有一个节点
            if (head.next == null || left == right){
                return head;
            }
            // 创建虚拟节点
            ListNode newHead = new ListNode(-1);
            newHead.next = head;
            // 寻找到left之前的节点
            ListNode preLeft = newHead;
            for (int i = 0; i < left - 1; i++) {
                preLeft = preLeft.next;
            }
            ListNode leftTemp = preLeft.next;
            // 寻找到right之后的节点
            ListNode rightTemp = newHead;
            for (int i = 0; i < right; i++) {
                rightTemp = rightTemp.next;
            }
            ListNode aftRight = rightTemp.next;
            // 反转指定区域链表
            ArrayList<ListNode> nodes = reverse(leftTemp, rightTemp, aftRight);
            ListNode header = nodes.get(0);
            ListNode tail = nodes.get(1);
            // 穿针引线
            preLeft.next = header;
            tail.next = aftRight;
            // 返回结果
            newHead = newHead.next;
            return newHead;
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

    public static void showListRecursion(ListNode head){
        if (head == null){
            return;
        }
        System.out.println(head.val + " => ");
        showListRecursion(head.next);
    }

    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null){
            return null;
        }
        //至少有两个结点 => 双指针
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != fast){
            //走到空 => 无环
            if (fast.next == null || fast.next.next == null){
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        //循环结束若方法没结束肯定有环 => 快指针从头开始走
        fast = head;
        while (slow != fast){
            slow = slow.next;
            fast = fast.next.next;
        }
        //快慢指针在入环结点相遇
        return slow;
    }

    public void reorderList(ListNode head) {
        //找到中点(slow为第一段链表结尾)
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode cur = slow.next;
        //断开
        slow.next = null;
        //反转(cur为第二段链表开头)
        ListNode pre = null;
        ListNode curNext = null;
        while (cur != null){
            //记录下一结点
            curNext = cur.next;
            //反转指针
            cur.next = pre;
            //往前走
            pre = cur;
            cur = curNext;
        }
        //插入(pre为反转链表的表头)
        ListNode temp = head;
        while (pre != null){
            //记录下一结点
            ListNode n1 = temp.next;
            ListNode n2 = pre.next;
            //连接
            temp.next = pre;
            pre.next = n1;
            //指针后移
            pre = n2;
            temp = n1;
        }

    }

    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        List<Integer> list = new ArrayList<>();
        //遍历链表并将值加入List中
        ListNode temp = head;
        while(temp != null){
            list.add(temp.val);
            temp = temp.next;
        }
        //List排序
        Collections.sort(list);
        //再次遍历值并依次修改值
        temp = head;
        for(Integer value : list){
            temp.val = value;
            temp = temp.next;
        }
        return head;
    }

    public ListNode mergeSortList(ListNode head){
        //链表归并排序
        if (head == null || head.next == null){
            return head;
        }
        //快慢指针找终点
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //链表分成两半
        ListNode cur = slow.next;
        slow.next = null;
        //右半部分归并排序
        ListNode r = mergeSortList(cur);
        //左半部分归并排序
        ListNode l = mergeSortList(head);
        //合并链表
        return mergeList(l,r);
    }

    public ListNode mergeList(ListNode l,ListNode r){
        //虚拟结点
        ListNode newHead = new ListNode(-1);
        ListNode newTemp = newHead;
        //两个链表排序
        while (l != null && r != null){
            if (l.val <= r.val){
                newTemp.next = l;
                l = l.next;
            }else {
                newTemp.next = r;
                r = r.next;
            }
            newTemp = newTemp.next;
        }
        //剩余链表直接添加
        newTemp.next = (l == null) ? r : l;
        newHead = newHead.next;
        return newHead;
    }

    public boolean isPalindrome(ListNode head) {
        if (head.next == null){
            return true;
        }
        //找到中点
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //分成两半
        ListNode cur = slow.next;
        slow.next = null;
        //反转后半部分链表
        ListNode pre = null;
        while (cur != null){
            ListNode curNext = cur.next;
            cur.next = pre;
            pre = cur;
            cur = curNext;
        }
        //依次比对
        while (head != null && pre != null){
            if (head.val != pre.val){
                return false;
            }
            head = head.next;
            pre = pre.next;
        }
        return true;
    }










}
