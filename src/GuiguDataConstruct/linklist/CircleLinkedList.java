package GuiguDataConstruct.linklist;

/**
 * @title: CircleLinkedList
 * @Author Tan
 * @Date: 2021/12/11 13:22
 * @Version 1.0
 */
public class CircleLinkedList {
    public static void main(String[] args) {
        CircleLinkList circleLinkList = new CircleLinkList();
        circleLinkList.addBoy(5);
        circleLinkList.showList();

        System.out.println("************** 约瑟夫问题测试 ***********************");
        /**
         约瑟夫问题 （小孩出圈书顺序）代码实现
         @param start: 从第几个小孩开始数
         @param countNum：数几下
         @param nums：小孩的总数量
         */
        // 正确顺序为 2 -> 4 -> 1 -> 5 -> 3
        circleLinkList.JosepfSolution(1,2,5);
//        circleLinkList.JosepfSolution(10,3,100);
    }

}

// 创建循环单向链表
class CircleLinkList{
    // 创建头节点 (默认为空)
    private Boy  head = null;

    // 添加方法
    public void  addBoy(int num){
        if (num < 1){
            System.out.println("num 的值不正确，请重新输入");
            return;
        }
        Boy currentBoy = null;
        for (int i = 1; i <= num ; i++) {
            // 根据编号创建节点
            Boy boy = new Boy(i);
            // 如果编号为 1
            if (i == 1){
                head = boy;
                head.next = head;              // 第一个节点构成环状
                currentBoy = head;             // 让指针指向头结点
            }else {
                currentBoy.next = boy;          // 当前结点加入链表
                boy.next = head;                // 指向头部 => 形成循环
                currentBoy = currentBoy.next;   // 指针后移
            }
        }
    }

    // 遍历方法
    public void showList(){
        if (head == null){
            System.out.println("没有结点，无法遍历");
            return;
        }
        Boy temp = head;
        while (true){
            System.out.printf("当前节点的编号%d \n",temp.no);
            if (temp.next == head){
                break;
            }
            temp = temp.next;
        }
    }

    /**
     * 约瑟夫问题 （小孩出圈书顺序）代码实现
     * @param start: 从第几个小孩开始数
     * @param countNum：数几下
     * @param nums：小孩的总数量
     */
    public void JosepfSolution(int start,int countNum,int nums){
        // 数据校验
        if (head == null || start < 1 || start > nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        // 创建辅助指针位于 head 之前
        Boy helper = head;
        Boy temp = head;
        // 1. 让help 指向最后一个结点 => 循环结束
        while (true){
            if (helper.next == head){
                break;
            }
            helper = helper.next;
        }
        // 2. 报数前让 temp 和 helper 移动到指定位置 (移动 k - 1 次) => temp 到为开始报数的小孩，help 在 temp 之前
        for (int i = 0; i < start - 1; i++) {
            temp = temp.next;
            helper = helper.next;
        }
        // 3. 开始报数和出圈
        while (true){
            if (helper == temp){
                // 圈中只有一个人
                break;
            }
            for (int i = 0; i < countNum - 1; i++) {
                // 报数 ( 两个指针移动 countNum - 1 次 )
                temp = temp.next;
                helper = helper.next;
            }
            System.out.printf("小孩%d出圈 \n",temp.no);
            // 出圈 (将 temp 指向的结点出圈 => 从链表移除该节点)
            temp = temp.next;
            helper.next = temp;
            }
        // 循环结束 => temp 和 helper 指向圈中最后一个小孩
        System.out.printf("最后留在圈中的小孩编号%d \n",temp.no);
        }


    }


// 创建 boy 结点
class Boy{
    public int no;
    public Boy next;

    public Boy(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                ", next=" + next +
                '}';
    }
}

