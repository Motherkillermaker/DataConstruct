package GuiguDataConstruct.stack;

import java.util.Stack;

/**
 * @author CJYong
 * @create 2021-12-10 16:05
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        // 入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("jerry");
        stack.add("lucy");
        // 出栈
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }
}
