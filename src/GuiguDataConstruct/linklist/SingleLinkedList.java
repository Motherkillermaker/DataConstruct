package GuiguDataConstruct.linklist;

import jdk.internal.org.objectweb.asm.tree.IincInsnNode;

import java.util.Stack;

/**
 * @title: SingleLinkedList
 * @Author Tan
 * @Date: 2021/12/9 20:06
 * @Version 1.0
 */
public class SingleLinkedList {

    public static void main(String[] args) {
        // 创建测试节点
        HeroNode node1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode node2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode node3 = new HeroNode(3, "吴用", "智多星");
        HeroNode node4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode node5 = new HeroNode(5, "jery", "杰瑞");
        HeroNode node6 = new HeroNode(6, "lucy", "露西");
        HeroNode node7 = new HeroNode(7, "mars", "马斯");
        HeroNode node8 = new HeroNode(8, "jack", "杰克");
        HeroNode node9 = new HeroNode(8, "susan", "苏珊");
        HeroNode node10 = new HeroNode(9, "rose", "罗斯");
        // 创建链表
        SingleLinkList linkList = new SingleLinkList();
        SingleLinkList linkList1 = new SingleLinkList();
        // 将节点加入链表
        linkList.add(node1);
        linkList.add(node3);
        linkList.add(node4);
        linkList.add(node7);
        linkList.add(node8);

        linkList1.add(node2);
        linkList1.add(node5);
        linkList1.add(node6);
        linkList1.add(node9);
        linkList1.add(node10);

        // 显示单链表
        linkList.showlist();
        linkList1.showlist();

        System.out.println("*****************测试修改节点*****************************************");

        //测试修改节点
//        HeroNode node = new HeroNode(3, "无用", "智多星！！！！！");
//        linkList.update(node);
//        linkList.showlist();


        System.out.println("******************测试删除节点****************************************");
        //删除一个结点
//        linkList.delete(1);
//        linkList.delete(4);
//        linkList.delete(3);
//        linkList.delete(2);

        // 显示单链表
//        linkList.showlist();

        System.out.println("******************获取节点的个数**************************************");

//        System.out.println("该链表有效节点个数为：" + getLength(linkList.getHead()));

        System.out.println("******************返回链表中的第k个节点*******************************");

//        HeroNode indexNode = findLastIndexNode(linkList.getHead(), 3);
//        System.out.println(indexNode);

        System.out.println("******************链表反转（原链表结构改变）***************************");

//        reverseLinkList(linkList.getHead());
//        linkList.showlist();

        System.out.println("******************反向打印链表（原链表结构未改变）**********************");
//        reversePrint(linkList.getHead());

        System.out.println("******************有序链表的合并**************************************");
        SingleLinkList list = mergeList(linkList.getHead(), linkList1.getHead());
        list.showlist();


    }

    // 合并两个有序的单链表（合并之后依然有序）
    public static SingleLinkList mergeList(HeroNode headone,HeroNode headtwo){
        if (headone.next == null && headtwo.next == null ){
            // 空链表
            return null;
        }
        SingleLinkList newlist = new SingleLinkList();  // 创建一个空链表
        HeroNode temp1 = headone.next;                  // 链表1  指针
        HeroNode temp2 = headtwo.next;                  // 链表2  指针
        HeroNode temp  = newlist.getHead();             // 新表指针
        while (temp1 != null && temp2 != null){
            if (temp1.no <= temp2.no){
                // 1 链表的值 小于 2 链表的值 => 将 1 加入新链表，新链表的指针后移，1 的指针后移
                temp.next = temp1;                      // 将较小值 temp1 加入新链表
                temp = temp.next;                       // temp    后移（指向新表的末尾）
                temp1 = temp1.next;                     // temp1   后移
            }else {
                temp.next = temp2;                      // 将较小值 temp2 加入新链表
                temp = temp.next;                       // temp    后移（指向新表的末尾）
                temp2 = temp2.next;                     // temp2   后移
            }
        }
        // 循环终止，有一个链表此时为空（temp1 or temp2 为空），将剩余的节点直接加入新链表
        if (temp1 != null){
            temp.next = temp1;                           // temp2 为空，将temp1接在新链表后面
        }else {
            // 此处有一个bug: 若最后 tem2 与 temp 为相同节点，就会出现递归，导致 temp 后面的结点无限 => 故若两个结点的 no 相同，则它们必须是不同的结点（后面的名字不同）

            temp.next = temp2;                           // temp1 为空，将temp2接在新链表后面
        }
        return newlist;

    }


    // 从尾到头（反向）打印单链表 => 使用栈来实现
    public static void reversePrint(HeroNode head){
        if (head.next == null){
            // 空链表
            return;
        }
        // 非空 => 创建一个栈，将遍历的节点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        HeroNode currentNode = head.next;
        while (currentNode != null){
            stack.push(currentNode);
            currentNode = currentNode.next;
        }
        // 入栈完毕 将栈中的节点打印输出
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }


    // 单链表的反转
    // 1.定义一个节点 reverseHead
    // 2.从头到尾遍历原来的链表，每遍历一个节点，就将其取出，并放在新链表的最前端（头插法）
    // 3. 原来链表的 head.next = reverseHead.next (将原始链表的头节点的next域指向新链表的第一个节点 => 丢弃所定义的中间节点 reverseHead)
    public static void reverseLinkList(HeroNode head){
        //如果当前链表为空，或只有一个节点 => 无需反转直接返回
        if (head.next == null || head.next.next == null){
            return;
        }
        HeroNode currentNode = head.next;                                        // 指针变量（初始指向链表的第一个结点）
        HeroNode next = null;                                                    // 指向当前节点的下一节点
        HeroNode reverseHead = new HeroNode(0, "", "");     // 中间节点（用于接收每一次遍历出的结点）
        while (currentNode != null){
            next = currentNode.next;                     // 先指针后移(保存当前节点的下一节点)

            //插入过程（重要）： 1） 新插入节点的next域指向原来头节点的next域（第二根线，让原来头结点后面的所有东西（reverseHead.next）放在新节点的后面（currentNode.next））  2） 新节点接在头节点的next域 （第一根线）
            currentNode.next = reverseHead.next;         // 第二根线 （核心代码）
            reverseHead.next = currentNode;              // 第一根线 （核心代码）
            currentNode = next;                          // 当前指针后移
        }
        // 丢弃中间节点 reverseHead
        head.next = reverseHead.next;

    }


    // 查找单链表中的倒数第 k 个节点
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        if (head.next == null){
            // 判断链表是否为空
            return null;
        }
        int length = getLength(head);
        if (length <= 0 || index > length){
            // 检查输入 index 的合法性
            return null;
        }
        HeroNode currentNode = head.next;                       // temp 指向第一个节点
        for (int i = 0; i < length -index; i++) {
            // 说明： 7个结点 查找倒数第3个(正数第5个节点) => length - index = 7 - 3 = 4, i 从 0 到 4 => 循环进行4次
            currentNode = currentNode.next;                     // 结束循环时 temp 指向正数第5个节点（倒数第三个节点）
        }
        return currentNode;
    }

    // 获取单链表节点的个数
    public static int getLength(HeroNode head){
        if (head.next == null){
            return 0;
        }
        int length = 0;                    // 定义一个 num 统计有效节点的个数
        HeroNode currentNode = head.next;
        while (currentNode != null){
            length ++;
            currentNode = currentNode.next;
        }
        return length;
    }
}



// 定义一个单链表
class SingleLinkList{
    // 初始化头节点 （不存放具体数据）
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }

    // 添加结点到单向列表 (不考虑顺序)
    public void add(HeroNode heroNode){
        // 当不考虑编号的顺序时
        //1. 找到当前链表最后的结点   2. 将最后结点的 next域指向 新加结点  => 尾插法 （通过遍历寻找到尾指针）
        HeroNode temp = head;            // 因为head 结点不能动， 因此引入辅助变量 temp (比作指针)
        while (true){                   // 遍历链表，找到最后的元素
            if (temp.next == null){
                // 找到链表最后
                break;
            }
            // 没有找到最后则将 temp 后移 （可以发现 temp 的作用就是一个移动的指针）
            // 理解： 第一次时 temp = 头节点（next域为空，循环结束），头节点的 next域 指向新添加的元素（第一个元素）
            //       第二次时 头节点的 next 域（第一个元素）不为空,此时使 temp 为第一个元素，temp 的 next 域为空（循环结束）， 将 temp(第一个元素)的 next 域设置为新添加的元素（第二个元素）
            //       第三次时 头节点的 next 域（第一个元素）不为空,此时使 temp 为第一个元素，temp 的 next 域不为空（第二个元素），此时使 temp 为第二个元素，temp 的 next 域为空（循环结束），将 temp(第二个元素)的 next 域设置为新添加的元素（第三个元素）
            //       。。。。。。
            temp = temp.next;
        }
        //结束循环时， temp 就指向链表最后元素
        temp.next = heroNode;
    }

    // 添加结点到单向列表 （考虑排名,有该排名则添加失败，并给出提示）
    public void addByOrder(HeroNode heroNode){
        // 头结点不能动，引入辅助结点
        HeroNode temp = head;
        boolean flag = false;                       // flag 表示添加的编号是否存在，默认为false
        while (true){
            // 循环结束的情况
            if (temp.next == null){
                // 遍历到表尾
                break;
            }
            if (temp.next.no > heroNode.no){
                // 当前指针所指元素的下一个元素的 no 大于 传入节点的 no, 插入位置就在当前 temp 之后
                break;
            }else if (temp.next.no ==  heroNode.no){
                // 当前编号已经存在，无法添加
                flag = true;
                break;
            }
            // 循环继续的情况 继续移动指针遍历链表
            temp = temp.next;
        }
        // 循环结束，判断 flag的值
        if (flag == true){
            // 编号存在，不能添加
            System.out.printf("当前编号%d已经存在，无法添加\n",heroNode.no);
        }else {
            // 可以添加（位置为当前 temp 之后）
            // 关键插入步骤： 1) 新节点.next = temp.next  2) temp.next = 新的节点  且步骤不能交换
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    // 删除一个节点 （思路：让 temp 指向被删除的前一个结点， temp.next = temp.next.next,被删除的结点会被垃圾回收机制回收）
    public void delete(int no){
        HeroNode temp = head;
        boolean flag = false;                    // 标识是否找到被删除的结点
        while (true){
            if (temp.next == null){
                // 已经遍历到表尾
                break;
            }
            if (temp.next.no == no){
                // 找到了待删除结点 （此时 temp 指向待删除结点的前一个结点）
                flag = true;
                break;
            }
            temp = temp.next;                   // temp 后移，遍历链表
        }
        if (flag){
            // 找到待删除结点
            temp.next = temp.next.next;
        }else {
            System.out.printf("待删除的结点%d 不存在，无法删除\n",no);
        }

    }

    // 修改节点信息 （根据 no 来修改）
    public void update(HeroNode heroNode){
        // node 根据传入的 结no 来修点的 改即可
        // 判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        // 根据 no 编号来找到需要修改的结点
        HeroNode temp = head.next;                   // 引入辅助结点 （指针）
        boolean flag = false;                        // 表示是否找到该节点
        while (true){
            // 循环结束时
            if (temp == null){
                // 到达表尾 (指针指向表尾后的元素 => 为空)
                break;
            }
            if (temp.no == heroNode.no){
                // 找到所要修改的结点, 位置为当前 temp 的位置
                flag = true;
                break;
            }
            // 循环继续，使指针向后移动
            temp = temp.next;
        }
        if (flag){
            // 找到当前节点
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        }else {
            // 没找到
            System.out.printf("没有找到编号为%d的节点，无法修改\n",heroNode.no);
        }



    }

    // 显示链表 => 遍历
    public void showlist(){
        // 判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        // 头节点不能动，引入辅助变量 temp
        HeroNode temp = head.next;          // 此时temp（指针） 为第一个节点
        while (true){
            // 判断是否到链表表尾 （指针 temp == null）
            if (temp == null){
                break;
            }
            // 没有到达表尾则输出该节点的信息
            System.out.println(temp);
            // 输出一个节点后将指针 temp 后移
            temp = temp.next;              // 该语句功能简记为 指针后移
        }
    }

}

// 定义一个结点 （以 英雄为例）
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;       //  指向下一个结点（结点类型为 HeroNode）

    // 构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}