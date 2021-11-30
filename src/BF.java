import org.junit.Test;

/**
 * @title: BF
 * @Author Tan
 * @Date: 2021/11/30 16:34
 * @Version 1.0
 */
public class BF {

    @Test
    public void test(){
        String S = "EDCABCFDAJKJKABC";                  // 下标依次从 0 开始  （第 N 个元素的下标为 N  - 1 ）
        String T = "JKJ";
        int index = indexBF(S, T);                      // 返回子串在主串中的下标（索引）
        int location = index + 1;                       // 返回字串在主串中的位置 （index + 1）
        System.out.println("子串在主串中的索引为：" + index);
        System.out.println("子串在主串中的位置为：" + location);
    }

    public  int indexBF(String S,String T){           // S为主串，T为字串
        char[] s = S.toCharArray();                  // 将字符串转换为字符数组
        char[] t = T.toCharArray();
        int i = 0;                                   //  Java 中 charAt（）该方法下标从 0 开始 切记 ！！！
        int j = 0;
        while (i < s.length && j < t.length){
            if (s[i] == t[j]){
                i++; j++;                           // 主串和子串依次匹配下一个字符
            }else {
                i = i - j + 1;                      // 主串回溯到下一个位置   理解： 减去 j 走过的步数  （ i - j (回到开始) + 1 （向前一格） ）
                j = 0 ;                             // 子串从头开始
            }
        }
        if (j >= T.length()){                      // 子串走完所有长度，匹配成功
            return i - t.length;                   // 返回匹配的第一个字符下标
        }else {
            return -1;                             // 模式匹配不成功
        }
    }

}

