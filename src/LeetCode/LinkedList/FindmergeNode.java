package LeetCode.LinkedList;

/**
 * @author CJYong
 * @create 2021-12-27 21:50
 */
public class FindmergeNode {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0;
        int lenB = 0;
        while (headA != null){
            lenA++;
            headA = headA.next;
        }
        while (headB != null){
            lenB++;
            headB = headB.next;
        }
        int step = Math.abs(lenA - lenB);
        ListNode tempA = headA;
        ListNode tempB = headB;
        if (lenA > lenB){
            while (step > 0 ){
                tempA = tempA.next;
                step --;
            }
        }
        return null;

    }

}
