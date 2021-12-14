package GuiguDataConstruct.hashtable;

/**
 * @title: HashTable
 * @Author Tan
 * @Date: 2021/12/14 15:14
 * @Version 1.0
 */
public class HashTable {
    // 创建一个数组，数组的元素为 员工链表
    private EmpLinkedList[] empLinkedListArray;
    // 数组的长度（共有几条链表）
    private int size;

    //构造函数
    public HashTable(int size) {
        this.size = size;
        // 初始化哈希表（创建数组）
        empLinkedListArray = new EmpLinkedList[size];
        // 初始化每个链表(创建每一个链表) => 重要
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    // 散列函数（取模法）
    public int hashFunction(int id){
        return id % size;
    }

    // 添加雇员
    public void add(Emp emp){
        // 根据员工 ID 决定员工应该添加到哪个链表 （散列）
        int empLinkedListNO = hashFunction(emp.id);
        // 将员工加入到对应的列表
        empLinkedListArray[empLinkedListNO].add(emp);
    }

    // 遍历雇员
    public void show(){
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].showList(i);
        }
    }

    //查找雇员
    public void getEmpById(int id){
        // 使用散列函数决定到哪个链表去查找
        int empLinkedListNO = hashFunction(id);
        Emp emp = empLinkedListArray[empLinkedListNO].getEmpById(id);
        if (emp != null){
            System.out.println("在第" + (empLinkedListNO + 1) + "条链表中找到雇员：" + emp);
        }else {
            System.out.println("在Hash表中未找到该员工！");
        }
    }

    //删除员工
    public void deleteEmpById(int id){
        // 使用散列函数决定到哪个链表去查找
        int empLinkedListNO = hashFunction(id);
        int res =  empLinkedListArray[empLinkedListNO].deleteEmpById(id);
        if (res > 0){
            System.out.println("雇员ID为 " + id + " 的员工已被删除");
        }else if (res < 0){
            System.out.println("没有找到雇员id = " + id + " 的员工");
        }
    }


}

