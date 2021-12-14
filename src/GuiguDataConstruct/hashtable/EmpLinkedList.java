package GuiguDataConstruct.hashtable;

/**
 * @title: EmpLinkedList
 * @Author Tan
 * @Date: 2021/12/14 15:01
 * @Version 1.0
 */
// 构造员工链表
public class EmpLinkedList {
    // 头结点
    private Emp head;

    // 添加员工到链表 (默认添加到最后)
    public void add(Emp emp){
        //添加第一个
        if (head == null){
            head = emp;
            return;
        }
        Emp temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = emp;
    }

    // 遍历员工链表
    public void showList(int no){
        if (head == null){
            System.out.println("当前" + (no + 1) +"链表为空");
            return;
        }
        System.out.print("当前" + (no + 1) + "链表的信息为：");
        Emp temp = head;
        while (true){
            System.out.print(temp + " ");
            if (temp.next == null){
                break;
            }
            temp = temp.next;
        }
        System.out.println();
    }

    //查找员工
    public Emp getEmpById(int id){
        if (head == null){
            return null;
        }
        Emp temp = head;
        while (true){
            if (temp.id == id){
                break;
            }
            if (temp.next == null){
                // 遍历完链表却没找到 => 返回空
                temp = null;
                break;
            }
            temp = temp.next;
        }
        return temp;
    }

    //删除员工
    public int deleteEmpById(int id){
        if (head == null){
            return -1;
        }
        Emp temp = head;
        boolean flag = false;
        // 头节点为待删除雇员 => 将头节点下一个元素设置为头结点
        if (temp.id == id){
            head = head.next;
            return 1;
        }
        //从第二个结点开始遍历到表尾
        while (true){
            if (temp.next == null){
                break;
            }
            if (temp.next.id == id){
                // 此时 temp 结点为待删除结点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            // 找到了待删除结点
            temp.next = temp.next.next;
            return 1;
        }else {
            // 删除的结点不存在
            return -1;
        }
    }

}

