package GuiguDataConstruct.queen;

import java.util.Scanner;

/**
 * @title: CircleArrayQueue
 * @Author Tan
 * @Date: 2021/12/9 19:10
 * @Version 1.0
 */
public class CircleArrayQueue {
    public static void main(String[] args) {
        //创建一个循环队列 (数组为4，最大存储 3 个元素)
        CircleQueue arrayqueue = new CircleQueue(4);
        char key = ' ';            // 接受用户的输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        // 输出一个菜单
        while (loop){
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队头元素");
            key = scanner.next().charAt(0);     // 接收一个字符
            switch (key){
                case 's':
                    arrayqueue.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    System.out.printf("程序退出，再见");
                    break;
                case 'a':
                    System.out.println("请输入一个数字：");
                    int value = scanner.nextInt();
                    arrayqueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayqueue.getQueue();
                        System.out.println("取出的数据是" +  res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayqueue.showFront();
                        System.out.println("队列头的数据是：" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
    }

}



// 使用数组模拟队列-编写一个CircleArrayQueen 类
class CircleQueue{
    private int maxSize; // 表示数组最大容量
    private int front;   //  队列头
    private int rear;    //  队列尾
    private int[] arr;   //  该数组用于存放数据，模拟队列

    // 创建队列的构造器
    public CircleQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0;      // 指向队头
        rear =  0;      // 指向队尾数据的后一个位置
    }

    //判断是否队满
    public boolean isFull(){
        return (rear + 1) % maxSize == front;   // 循环队列判断是否队满    java数值下标从 0 开始
    }

    //判断是否队空
    public boolean isEmpty(){
        return rear == front;
    }

    // 入队（队尾入）
    public void addQueue(int n){
        // 判断是否 队满
        if (isFull()){
            System.out.println("队满，不能加入数据");
            return;
        }
        arr[rear] = n;       // 添加数据
        rear = (rear + 1) % maxSize;              // 移动队尾指针 （取模使得指针移动到空元素的位置）
    }

    // 出队（队头出）
    public int getQueue(){
        // 判断是否 队满
        if (isEmpty()){
            // 通过抛出异常来处理
            throw new RuntimeException("队列空，不能取数据");
        }
        // 1. 先把 front 对应的值保留到一个临时变量 （不保存直接移动的话就无法找到 front 指针）
        int value = arr[front];
        // 2. 将 front 后移 （考虑取模 => 让指针往空元素的位置移动 => 循环队列）
        front = (front + 1) % maxSize;            // 队头指针后移
        // 3. 返回对头元素
        return value;  // 返回队头元素
    }

    // 求出当前队列中有效数据的个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    // 显示队列的所有数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空，无法遍历");
        }
        // 数组的遍历 （从 front 开始， 到 rear 结束， 取模是为了保持和队列的头尾指针一致 （防止数组角标越界））
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
        }
    }

    // 显示队头元素 （非取元素，队指针不用移动）
    public int showFront(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[front];      // 返回队头元素 （ front 指向队列的第一个元素 ）
    }
}