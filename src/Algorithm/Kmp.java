package Algorithm;

import java.security.PublicKey;
import java.util.Arrays;

public class Kmp {
    public static void main(String[] args) {
        String str1 = "ABCDDAJKHDJKAHABCD";
        String str2 = "DDAJ";
        int[] next = getKmpNext("AAAB");
        System.out.println(Arrays.toString(next));
        int i = kmp(str1, str2);
        System.out.println(i);

    }

    // KMP搜索算法(S为主串 T为子串)
    public static int kmp(String S,String T){
        int[] Next = getKmpNext(T);
        // 遍历主串
        for (int i = 0,j = 0; i < S.length(); i++) {
            // 当前匹配不成功
            while (j > 0 && S.charAt(i) != T.charAt(j)){
                j = Next[j - 1];
            }
            // 当前匹配成功
            if (S.charAt(i) == T.charAt(j)){
                j++;
            }
            if (j == T.length()){
                return i - j + 1;
            }
        }
        return -1;
    }

    // 求取 Next 数组
    public static int[] getKmpNext(String str){
        // 创建Next数组
        int[] Next = new int[str.length()];
        // 字符串长度为1 => Next数组为0
        Next[0] = 0;
        for (int i = 1,j = 0; i < str.length(); i++) {
            while ( j > 0 && str.charAt(i) != str.charAt(j)){
                j = Next[j - 1];
            }
            if (str.charAt(i) == str.charAt(j)){
                j++;
            }
            Next[i] = j;
        }
        return Next;
    }

}



