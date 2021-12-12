package GuiguDataConstruct.stack;

import java.util.Scanner;

/**
 * @title: ArrayStack
 * @Author Tan
 * @Date: 2021/12/11 14:52
 * @Version 1.0
 */
public class ArrayStackTest {
    public static void main(String[] args) {
        // 栈的测试
        Arraystack stack = new Arraystack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        // 输出一个菜单
        while (loop){
            System.out.println("show: 显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 添加数据到栈顶");
            System.out.println("pop:  从栈顶取出数据");
            key = scanner.next();     // 接收一个字符
            switch (key){
                case "show":
                    stack.showStack();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    System.out.printf("程序退出，再见");
                    break;
                case "push":
                    System.out.println("请输入一个数字：");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.println("取出的数据是" +  res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
    }

}

class Arraystack{
    public int maxSize;     // 栈的大小
    public int[] stack;     // 数组模拟栈
    public int top = -1;    // 栈顶

    public Arraystack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    // 栈满 (java数组下标从 0 开始，下标最大为 maxSize - 1 )
    public boolean isFull(){
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty(){
        return top == -1;
    }

    // 入栈 (top++ ; stack[top] = data )
    public void push(int value){
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    // 出栈 (int value = stack[top]; top--; return value)
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    // 遍历栈
    public void showStack(){
        if (isEmpty()){
            System.out.println("没有数据，无法遍历");
            return;
        }
        for (int i = top; i >=0 ; i--) {
            System.out.printf("stack[%d] = %d\n",i,stack[i]);
        }
    }

}

