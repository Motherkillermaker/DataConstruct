package WestLake.LinkedList;

import WestLake.TreeNode;

import javax.management.QueryEval;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author CJYong
 * @create 2022-02-09 21:21
 */
public class Tree {

    public static void main(String[] args) {

    }


    /**
     * 非递归先序遍历
     * @param head
     */
    public static void preOrderUnRecur(TreeNode head){
        //先序遍历： 打印顺序为头左右，压栈顺序为头右左 （相反）
        System.out.println("pre-order");
        if (head == null){
            return;
        }
        //1.自己创建一个栈
        Stack<TreeNode> stack = new Stack<>();
        //2.头结点入栈
        stack.add(head);
        while (!stack.isEmpty()){
            //3.从栈中弹出一个元素并打印
            head = stack.pop();
            System.out.println(head.value + " ");
            //4.右孩子不为空则入栈
            if (head.right != null){
                stack.push(head.right);
            }
            //5.左孩子不为空则入栈
            if (head.left != null){
                stack.push(head.left);
            }
        }
        System.out.println();
    }

    /**
     * 非递归后序遍历
     * @param head
     */
    public static void postOrderUnRecur(TreeNode head){
        //后序遍历：打印顺序为左右头
        //压栈顺序为头左右（头右左出栈），临时压栈存储（头右左压栈），打印结果为左右头
        System.out.println("post-order");
        if (head == null){
            return;
        }
        //1.自己创建两个栈（s1为压入栈，s2为临时存储栈）
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        //2.头结点入栈
        s1.push(head);
        while (!s1.isEmpty()){
            //3.从栈中弹出一个元素并压入s2栈中（临时存储）
            head = s1.pop();
            s2.push(head);
            //4.左孩子不为空则入栈
            if (head.left != null){
                s1.push(head.left);
            }
            //5.右孩子不为空则入栈
            if (head.right != null){
                s1.push(head.right);
            }
        }
        //6.s2元素依次出栈即为后序遍历
        while (!s2.isEmpty()){
            System.out.println(s2.pop().value + " ");
        }
        System.out.println();
    }

    /**
     * 非递归中序遍历
     * @param head
     */
    public static void inOrderUnRecur(TreeNode head){
        //中序遍历：打印顺序左头右，压栈顺序头左（出栈左头）
        //左头右（右为右树，右数上继续左头右...）
        if (head == null){
            return;
        }
        //1.自己创建一个栈
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || head != null){
            //2.左边界全部入栈
            if (head != null){
                stack.push(head);
                head = head.left;
            }else {
                //3.弹出节点并打印
                head = stack.pop();
                System.out.println(head.value + " ");
                //4.将head设置为右孩子 => 继续走逻辑分支1
                head = head.right;
            }
        }
        System.out.println();
    }

    /**
     * 宽度优先遍历（层次遍历）
     * @param head
     */
    public static void wideOrder(TreeNode head){
        if (head == null){
            return;
        }
        //1.自己创建一个队列
        Queue<TreeNode> queue = new LinkedList<>();
        //2.头结点入队列
        queue.add(head);
        while (!queue.isEmpty()){
            //3.从队列中弹出一个元素并打印
            TreeNode cur = queue.poll();
            System.out.println(cur.value + " ");
            //4.左孩子不为空则入队列
            if (cur.left != null){
                queue.add(cur.left);
            }
            //5.右孩子不为空则入队列
            if (cur.right != null){
                queue.add(cur.right);
            }
        }
        System.out.println();
    }

    /**
     * 二叉树的最大宽度
     * @param head
     */
    public static int maxWide(TreeNode head){
        if (head == null){
            return 0;
        }
        //1.自己创建一个队列
        Queue<TreeNode> queue = new LinkedList<>();
        //2.头结点入队列
        queue.add(head);
        HashMap<TreeNode, Integer> leveMap = new HashMap<>();
        leveMap.put(head,1);
        //当前层数
        int curLevel = 1;
        //当前层节点数
        int curLevelNodes = 0;
        //最大宽度
        int maxWidth = Integer.MIN_VALUE;
        while (!queue.isEmpty()){
            //3.从队列中弹出一个元素并打印
            TreeNode cur = queue.poll();
            int level = leveMap.get(cur);
            if (level == curLevel){
                //还在本层
                curLevelNodes++;
            }else {
                //已经在下一层，更新宽度
                maxWidth = Math.max(maxWidth,curLevelNodes);
                //数据重置
                curLevel++;
                curLevelNodes = 1;
            }

            System.out.println(cur.value + " ");
            //4.左孩子不为空则入队列
            if (cur.left != null){
                //进队列时记录信息
                leveMap.put(cur.left,curLevel + 1);
                queue.add(cur.left);
            }
            //5.右孩子不为空则入队列
            if (cur.right != null){
                //进队列时记录信息
                leveMap.put(cur.right,curLevel + 1);
                queue.add(cur.right);
            }
        }
        //遍历完所有层后比较最后一层的宽度
        maxWidth = Math.max(maxWidth,curLevelNodes);
        return maxWidth;

    }

}
