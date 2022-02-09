package LeetCode.Array;

import WestLake.ListNode;

/** LeetCode 2
 * @author CJYong
 * @create 2021-12-09 22:41
 */
public class TwoNumPlus {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(1);
        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(8);

        ListNode node = addTwoNumbers(l1, l2);
        System.out.println(node);


    }
    public static ListNode addTwoNumbers(ListNode l1,ListNode l2){
        // l1,l2 是逆序存储数组的链表
        ListNode head = new ListNode(0);
        ListNode temp1 = l1, temp2 = l2;
        ListNode currentNode = head;
        int carry = 0;                              // 用于携带进位
        while (temp1 != null || temp2!= null){
            int x = (temp1 != null) ? l1.val:0;     // temp1 != null 为条件
            int y = (temp2 != null) ? l2.val:0;     // temp1 != null 为条件
            int sum = x + y + carry;                // carry 初始为 0，后续为之前携带的值
            carry = sum / 10;                       // 更新 carry 的值，将进位带入下一次运算
            currentNode.next = new ListNode(sum % 10);  // 两结点之和 与 10 的模 为新链表的节点值
            currentNode = currentNode.next;         // 新链表指针移动
            if (temp1 != null){
                temp1 = temp1.next;
            }
            if (temp2 != null){
                temp2 = temp2.next;
            }
        }
        // 链表结束时carry = 1 ,则还需新增一个结点
        if (carry > 0){
            currentNode.next = new ListNode(carry);
        }
        return head;
    }
}



