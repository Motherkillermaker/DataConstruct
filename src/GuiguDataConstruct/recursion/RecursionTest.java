package GuiguDataConstruct.recursion;

/**
 * @title: RecursionTest
 * @Author Tan
 * @Date: 2021/12/12 12:43
 * @Version 1.0
 */
public class RecursionTest {
    public static void main(String[] args) {
        test(4);
        System.out.println("**************");
        int factorial = factorial(30);
        System.out.println(factorial);

    }

    /**
     * 分治法求解递归问题算法的一般形式
     * @param n
     * void p (参数列表){
     *     if (递归结束条件){
     *         可直接求解的步骤  => 基本项
     *     }else p (较小的参数)
     * }
     *
     */

    // 递归方法调用： 执行到一个方法后就会开辟一个独立的空间（栈） => 递归调用结束后依次出栈 => 后进先出
    public static void test(int n){
        if (n > 2){
            test(n- 1);
        }
        System.out.println(" n = " + n);
    }

    // 阶乘问题
    public static int factorial(int n){
        if (n == 1){
            return 1;
        }else {
            return factorial(n - 1) * n;
        }
    }

}

