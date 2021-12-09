package GuiguDataConstruct.queen;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.Scanner;

/**
 * @title: ArrayQueue
 * @Author Tan
 * @Date: 2021/12/9 13:49
 * @Version 1.0
 */
public class ArrayQueue {

        public static void main(String[] args) {
            Queue arrayqueue = new Queue(3);
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

    // 使用数组模拟队列-编写一个ArrayQueen 类
class Queue{
        private int maxSize; // 表示数组最大容量
        private int front;   //  队列头
        private int rear;    //  队列尾
        private int[] arr;   //  该数组用于存放数据，模拟队列

        // 创建队列的构造器
        public Queue(int arrMaxSize){
            maxSize = arrMaxSize;
            arr = new int[maxSize];
            front = -1;     // 指向队头的前一个位置
            rear = -1;      // 指向队尾数据
        }

        //判断是否队满
        public boolean isFull(){
            return rear == maxSize - 1;   // java数值下标从 0 开始
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
            rear++;              // 移动队尾指针
            arr[rear] = n;       // 添加数据
        }

        // 出队（队头出）
        public int getQueue(){
            // 判断是否 队满
            if (isEmpty()){
                // 通过抛出异常来处理
                throw new RuntimeException("队列空，不能取数据");
            }
            front++;            // 队头指针后移
            return arr[front];  // 返回队头元素
        }

        // 显示队列的所有数据
        public void showQueue(){
            if (isEmpty()){
                System.out.println("队列为空，无法遍历");
            }
            // 数组的遍历 （此处为数组的下标，与队列的头尾指针无关 / front = -1 没有影响）
            for (int i = 0; i < arr.length; i++) {
                System.out.printf("arr[%d]=%d\n",i,arr[i]);
            }
        }

        // 显示队头元素 （非取元素，队指针不用移动）
        public int showFront(){
            if (isEmpty()){
                throw new RuntimeException("队列为空");
            }
            return arr[front + 1];      // 返回队头元素
        }
    }



