package GuiguDataConstruct.linklist;

/**
 * @title: DoubleLinkedList
 * @Author Tan
 * @Date: 2021/12/11 12:04
 * @Version 1.0
 */
public class DoubleLinkedList {
    public static void main(String[] args) {
        // 创建测试节点
        HeroNodeDouble node1 = new HeroNodeDouble(1, "宋江", "及时雨");
        HeroNodeDouble node2 = new HeroNodeDouble(2, "卢俊义", "玉麒麟");
        HeroNodeDouble node3 = new HeroNodeDouble(3, "吴用", "智多星");
        HeroNodeDouble node4 = new HeroNodeDouble(4, "林冲", "豹子头");
        HeroNodeDouble node5 = new HeroNodeDouble(5, "jery", "杰瑞");
        HeroNodeDouble node6 = new HeroNodeDouble(6, "lucy", "露西");
        HeroNodeDouble node7 = new HeroNodeDouble(7, "mars", "马斯");
        HeroNodeDouble node8 = new HeroNodeDouble(8, "jack", "杰克");
        HeroNodeDouble node9 = new HeroNodeDouble(8, "susan", "苏珊");
        HeroNodeDouble node10 = new HeroNodeDouble(9, "rose", "罗斯");
        // 创建链表
        DoubleLinkList doubleLinkList = new DoubleLinkList();
        doubleLinkList.addByOrder(node1);
        doubleLinkList.addByOrder(node4);
        doubleLinkList.addByOrder(node3);
        doubleLinkList.addByOrder(node2);

        System.out.println("**********************************结点按序号添加*******************");
        doubleLinkList.showlist();

        System.out.println("**********************************结点的修改*******************");
        HeroNodeDouble newnode = new HeroNodeDouble(3, "没用", "智少星");
        doubleLinkList.update(newnode);
        doubleLinkList.showlist();

        System.out.println("**********************************结点的删除*******************");
        doubleLinkList.delete(3);
        doubleLinkList.showlist();





    }



}

class DoubleLinkList{
    // 初始化头节点
    private HeroNodeDouble head = new HeroNodeDouble(0,"","");

    public HeroNodeDouble getHead() {
        return head;
    }

    public void setHead(HeroNodeDouble head) {
        this.head = head;
    }

    // 遍历
    public void showlist(){
        // 判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        // 头节点不能动，引入辅助变量 temp
        HeroNodeDouble temp = head.next;          // 此时temp（指针） 为第一个节点
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

    // 添加结点到双向列表 (不考虑顺序)
    public void add(HeroNodeDouble heroNode){
        // 当不考虑编号的顺序时
        //1. 找到当前链表最后的结点   2. 将最后结点的 next域指向 新加结点  => 尾插法 （通过遍历寻找到尾指针）
        HeroNodeDouble temp = head;            // 因为head 结点不能动， 因此引入辅助变量 temp (比作指针)
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
        temp.next = heroNode;       // 前指后
        heroNode.pre = temp;        // 后指前
    }

    // 添加结点到单向列表 （考虑排名,有该排名则添加失败，并给出提示）
    public void addByOrder(HeroNodeDouble heroNode){
        // 头结点不能动，引入辅助结点
        HeroNodeDouble temp = head;
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
            if (temp.next != null){
                heroNode.next = temp.next;
                temp.next.pre = heroNode;    // 后面两条线 (避免添加位置为最后一个元素 => 空指针异常)
            }
            temp.next = heroNode;
            heroNode.pre = temp;            // 前面两条线
        }
    }

    // 修改节点信息 （根据 no 来修改）
    public void update(HeroNodeDouble heroNode){
        // node 根据传入的 结no 来修点的 改即可
        // 判断链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        // 根据 no 编号来找到需要修改的结点
        HeroNodeDouble temp = head.next;                   // 引入辅助结点 （指针）
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

    // 删除一个节点 （思路：让 temp 指向被删除的前一个结点， temp.next = temp.next.next,被删除的结点会被垃圾回收机制回收）
    public void delete(int no){
        if (head.next == null){
            System.out.println("链表为空，无法删除 ！");
            return;
        }
        HeroNodeDouble temp = head.next;
        boolean flag = false;                    // 标识是否找到被删除的结点
        while (true){
            if (temp == null){
                // 已经遍历到表尾
                break;
            }
            if (temp.no == no){
                // 找到了待删除结点 （此时 temp 指向待删除结点的前一个结点）
                flag = true;
                break;
            }
            temp = temp.next;                   // temp 后移，遍历链表
        }
        if (flag){
            // 找到待删除结点
            temp.pre.next = temp.next;          // 前指后
            if (temp.next != null){
                temp.next.pre = temp.pre;       // 后指前 (最后一个结点无需执行 => 空指针)
            }
        }else {
            System.out.printf("待删除的结点%d 不存在，无法删除\n",no);
        }

    }
}

// 定义一个结点 （以 英雄为例）
class HeroNodeDouble{
    public int no;
    public String name;
    public String nickname;
    public HeroNodeDouble next;       //  指向下一个结点（结点类型为 HeroNodeDouble）
    public HeroNodeDouble pre;        //  指向前一个结点（结点类型为 HeroNodeDouble）

    // 构造器
    public HeroNodeDouble(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNodeDouble{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

