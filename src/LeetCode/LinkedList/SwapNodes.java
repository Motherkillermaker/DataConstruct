package LeetCode.LinkedList;

/** LeetCode 24
 * @author CJYong
 * @create 2021-12-25 13:09
 */
public class SwapNodes {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        swapNodes(node1);
        System.out.println(node1);

    }

    public static ListNode swapNodes(ListNode node){
        if (node == null){
            return null;
        }
        ListNode slow = node;
        ListNode fast = node.next;
        ListNode head = null;
        ListNode temp = head;
        while (fast.next != null){
            if (head == null){
                head = fast;
                head.next = slow;
                fast = fast.next.next;
                slow = slow.next.next;
                temp = head.next;
            }else {
                temp.next = fast;
                temp.next.next = slow;
                temp = temp.next.next;
                fast = fast.next.next;
                slow = slow.next.next;
            }
        }

        return head;
    }
}
