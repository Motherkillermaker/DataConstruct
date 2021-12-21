package Algorithm;

public class DivideAndConquer {
    public static void main(String[] args) {
        hanoitower(8,'A','B','C');
    }

    /**
     * 分治算法求解基本步骤：
     * 1. 分解： 将原问题分解为若干个规模较小、相互独立，与原问题形式相同的子问题
     * 2. 解决： 若子问题规模较小容易解决则直接解决，否则递归解决各个子问题
     * 3. 合并： 将各个子问题的解合并为原问题的解
     * @param num
     * @param a
     * @param b
     * @param c
     */
    public static void hanoitower(int num,char a,char b,char c){
        // 只有一个盘
        if (num == 1){
            System.out.println("第1个盘从 " + a + " => " + c);
        }else {
            // n > 2; 总是可以看作两个盘（最下面的盘 / 上面的所有盘）
            // 1. 把上面的盘 A => B (移动过程使用 c )
            hanoitower(num - 1,a,c,b);
            // 把最下面的盘 A => C
            System.out.println("第" + num + "个盘从 " + a + " => " + c);
            // 把 B 塔所有的盘 B => C (移动过程使用 a)
            hanoitower(num-1,b,a,c);
        }
    }


}


