package LeetCode;

/**
 * @author CJYong
 * @create 2021-12-09 22:41
 */
public class TwoNumPlus {
    public static void main(String[] args) {

    }
}


// 定义单链表
class LinkList{
    private ListNode head = new ListNode(0);

    // 逆序添加结点到链表 （逆序插入）
    public void addReverse(ListNode node1,ListNode node2){
        ListNode temp = head;
        while (true){
            if (temp.next == null){
                // 遍历到表尾的后一个元素
                break;
            }
            // 没有到达表尾则继续遍历链表
            temp = temp.next;
        }
        //结束循环时， temp 就指向链表最后元素


    }
}

// 定义节点
class ListNode{
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
