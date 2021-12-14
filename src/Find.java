import jdk.nashorn.internal.ir.IfNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @title: SequenceFind
 * @Author Tan
 * @Date: 2021/12/7 10:21
 * @Version 1.0
 */
public class Find {

    @Test
    public void test(){
        int[] arr = new int[]{1,4,7,7,16,16,80,90,100,100,100,302,302,965};
        System.out.println("********************测试顺序查找**************************");
        System.out.println( "查找元素的索引值为: " + SearchSeq(arr,302));

        System.out.println("********************测试二分法查找**************************");
        System.out.println( "查找元素的索引值为: " + BinarySearchRecursion(arr,80,0,arr.length - 1));

        System.out.println("********************测试二分法查找（返回集合）**************************");
        ArrayList<Integer> indexList  = BinarySearchRecursionPlus(arr,302,0,arr.length - 1);
        System.out.println(indexList);

        System.out.println("********************测试插值查找算法查找（返回集合）**************************");
        System.out.println( "查找元素的索引值为: " + InsertValueSearch(arr,965,0,arr.length - 1));

        System.out.println("********************测试斐波那契查找算法查找（返回集合）**************************");
        System.out.println( "查找元素的索引值为: " + FibonacciSearch(arr,90));

    }


    // 顺序查找
    public int SearchSeq(int[] arr,int key){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key){
                return i ;
            }
        }
        return -1;
    }

    //二分法查找(数组必须有序)
    public int BinarySearch(int[] arr,int key){
        int low = 0;                            // 初始最小值索引
        int high = arr.length - 1;              // 初始最大值索引
        while (low <= high) {                   //最小值和最大值中间不为空
            int mid = (low + high) / 2;
            if (key == arr[mid]) {
                return mid ;                    // 查找的值等于中间位置，返回mid的索引
            } else if (key < arr[mid]) {
                high = mid - 1;                 // 查找的值比中间位置小，移动high
            } else if (key > arr[mid]) {
                low = mid + 1;                  // 查找的值比中间位置大，移动low
            }
        }
        return 0;                               // 没有找到目标元素，返回 0
    }

    //二分法查找(递归)
    public int BinarySearchRecursion(int[] arr,int key,int low,int high){
        if (low > high){
            // 数组递归完毕但未找到，结束递归
            return -1;
        }
        int mid = (low + high) / 2;
        int midValue = arr[mid];
        if (key > midValue){
            // 向右递归
            return BinarySearchRecursion(arr,key,mid + 1,high);
        }else if (key < midValue){
            // 向左递归
            return BinarySearchRecursion(arr,key,low,mid - 1);
        }else {
            return mid;     // 找到了
        }
    }

    //二分查找（返回所有符合条件的元素索引）
    public ArrayList<Integer> BinarySearchRecursionPlus(int[] arr, int key, int low, int high){
        if (low > high){
            // 数组递归完毕但未找到，结束递归
            return new ArrayList<Integer>();
        }
        int mid = (low + high) / 2;
        int midValue = arr[mid];
        if (key > midValue){
            // 向右递归
            return BinarySearchRecursionPlus(arr,key,mid + 1,high);
        }else if (key < midValue){
            // 向左递归
            return BinarySearchRecursionPlus(arr,key,low,mid - 1);
        }else {
            // 找到索引值后不要马上返回，分别向左右继续扫描，将符合条件的元素下标加入集合中
            ArrayList<Integer> indexList =  new ArrayList<Integer>();
            int temp = mid - 1;
            while (true){
                // 没找到（下标为0 或者没有元素符合条件）
                if (temp < 0 || arr[temp] != key){
                    break;
                }
                indexList.add(temp);
                temp -= 1;              // temp 左移
            }
            indexList.add(mid);         // 将中间的放入集合
            temp = mid + 1;
            while (true){
                if (temp > arr.length - 1 || arr[temp] != key){
                    break;
                }
                indexList.add(temp);
                temp += 1;
            }
            return indexList;
        }
    }

    //插值查找（需要有序）
    public int InsertValueSearch(int[] arr,int key,int low,int high){
        // key < arr[0] || key > arr[arr.length - 1]  防止角标越界
        if (low > high || key < arr[0] || key > arr[arr.length - 1]){
            // 数组递归完毕但未找到，结束递归
            return -1;
        }
        // 插值查找算法 mid 的确定(自适应查找) => 重要
        int mid = low + (high - low) * (key - arr[low]) / (arr[high] - arr[low]);
        int midValue = arr[mid];
        if (key > midValue){
            // 向右递归
            return BinarySearchRecursion(arr,key,mid + 1,high);
        }else if (key < midValue){
            // 向左递归
            return BinarySearchRecursion(arr,key,low,mid - 1);
        }else {
            return mid;     // 找到了
        }
    }

    //斐波那契查找（需要有序）
    public int FibonacciSearch(int[] arr,int key){
        // 思路： 将数组按斐波那契数列分割, 使用递归来查找
        int low = 0;  int high = arr.length - 1;
        int k = 0;                                // 斐波那契数列的下标
        int mid = 0;
        int[] fib = Fibonacci(100);
        // 获取斐波那契分割数值的下标（如何将数值分割）
        while (high > fib[k] - 1){
            k++;
        }
        // 将arr构造成与 fib[k] 等长的数列（不足的部分用 high 填充）
        int[] temp = Arrays.copyOf(arr,fib[k]);
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }
        // 查找所需要的值 f[k] = f[k-1] + f[k-2]
        while (low <= high){
            mid = low + fib[k - 1] - 1;
            if (key < temp[mid]){
                high = mid - 1;
                k--;                        // 下一次按照新的斐波那契分割查找
            }else if (key > temp[mid]){
                low = mid + 1;
                k -= 2;                     // 下一次按照新的斐波那契分割查找
            }else {
                if (mid <= high){
                    return mid;
                }else {
                    return high;
                }
            }
        }
        return -1;
    }

    //构建斐波那契数列
    public int[] Fibonacci(int maxSize){
        int[] fib = new int[maxSize];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i < fib.length; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

}

