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

    //二分法查找(数组必须有序) => 寻找满足条件的值
    public int BinarySearch(int[] arr,int target){
        // 初始最小值索引
        int left = 0;
        // 初始最大值索引
        int right = arr.length - 1;
        //最小值和最大值中间不为空 => 解空间存在 => 当解空间变成 [right,left] 时（此时开闭已经无所谓了），不存在一个数 > right 且 < right，如区间[3,2]中不可能存在数一样
        while (left <= right) {
            // 防止溢出 等同于(left + right)/2
            int mid = left + ((right - left) / 2);
            // 查找的值等于中间位置，返回mid的索引
            if (target == arr[mid]) {
                return mid ;
            } else if (target < arr[mid]) {
                // 查找的值比中间位置小，移动right => 解空间变为 [left, mid - 1]
                // 排除全部大于mid位置的数 => mid 以及 mid 右侧的数字被我们排除在外 => mid 排除是因为 if 条件判断 / mid右侧的数排除是因为数组有序（它们都大于mid处的值）
                right = mid - 1;
            } else if (target > arr[mid]) {
                // 查找的值比中间位置大，移动left => 解空间变为 [mid+1, right]
                // 排除全部小于mid位置的数 => mid 以及 mid 左侧的数字被我们排除在外 => mid 排除是因为 if 条件判断 / mid左侧的数排除是因为数组有序（它们都小于mid处的值）
                left = mid + 1;
            }
        }
        // 循环结束都没有找到，则说明找不到，返回 -1 表示未找到。
        return -1;
    }

    //二分法查找(数组必须有序) => 寻找最左插入位置(目标第一次出现的位置) => 最左二分不断收缩右边界，最终返回左边界
    public int BinarySearchLeft(int[] arr,int target){
        // 初始最小值索引
        int left = 0;
        // 初始最大值索引
        int right = arr.length - 1;
        //最小值和最大值中间不为空 => 解空间存在 => 当解空间变成 [right,left] 时（此时开闭已经无所谓了），不存在一个数 > right 且 < right，如区间[3,2]中不可能存在数一样
        while (left <= right) {
            // 防止溢出 等同于(left + right)/2
            int mid = left + ((right - left) / 2);
            if (target <= arr[mid]) {
                // 查找的值小于或等于中间位置，移动right => 解空间变为 [left, mid - 1] => 向左二分
                // 排除全部大于mid位置的数 => mid 以及 mid 右侧的数字被我们排除在外 => mid 排除是因为 if 条件判断 / mid右侧的数排除是因为数组有序（它们都大于mid处的值）
                right = mid - 1;
            }else {
                // 查找的值比中间位置大，移动left => 解空间变为 [mid+1, right]
                // 排除全部小于mid位置的数（包括mid,因为此时 target > mid） => mid 以及 mid 左侧的数字被我们排除在外 => mid 排除是因为 if 条件判断 / mid左侧的数排除是因为数组有序（它们都小于mid处的值）
                left = mid + 1;
            }
        }
        //最后解空间的 l 就是最好的备胎，备胎转正。
        return left;
    }

    //二分法查找(数组必须有序) => 寻找最右插入位置(目标最后出现的位置) => 最右二分不断收缩左边界，最终返回右边界
    public int BinarySearchRight(int[] arr,int target){
        // 初始最小值索引
        int left = 0;
        // 初始最大值索引
        int right = arr.length - 1;
        //最小值和最大值中间不为空 => 解空间存在 => 当解空间变成 [right,left] 时（此时开闭已经无所谓了），不存在一个数 > right 且 < right，如区间[3,2]中不可能存在数一样
        while (left <= right) {
            // 防止溢出 等同于(left + right)/2
            int mid = left + ((right - left) / 2);
            if (target >= arr[mid]) {
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        //最后解空间的 l 就是最好的备胎，备胎转正。
        return right;

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

