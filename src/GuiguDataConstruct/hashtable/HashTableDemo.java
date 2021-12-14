package GuiguDataConstruct.hashtable;

import java.util.Scanner;

/**
 * @title: HashTableDemo
 * @Author Tan
 * @Date: 2021/12/14 15:27
 * @Version 1.0
 */
public class HashTableDemo {
    public static void main(String[] args) {
        // 创建哈希表
        HashTable hashTable = new HashTable(7);

        // 编写简单菜单
        String key ="";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("dele: 删除雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("请输入id：");
                    int id = scanner.nextInt();
                    System.out.println("请输入名字：");
                    String name = scanner.next();
                    System.out.println("请输入地址：");
                    String address = scanner.next();
                    Emp emp =  new Emp(id,name,address);
                    hashTable.add(emp);
                    break;
                case "list":
                    hashTable.show();
                    break;
                case "find":
                    System.out.println("请输入查找员工的id：");
                    int no = scanner.nextInt();
                    hashTable.getEmpById(no);
                    break;
                case "dele":
                    System.out.println("请输入需要删除的员工id：");
                    int num = scanner.nextInt();
                    hashTable.deleteEmpById(num);
                    break;
                case "exit":
                    scanner.close();
                    System.out.println("再见！");
                default:
                    break;
            }
        }
    }
}

