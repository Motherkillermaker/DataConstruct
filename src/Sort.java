import org.junit.Test;

import java.util.Arrays;

/**
 * @title: InsertSort
 * @Author Tan
 * @Date: 2021/12/8 11:36
 * @Version 1.0
 */
public class Sort {

    @Test
    public void test(){
        int maxSize = 80000;
        System.out.println("*****************测试冒泡排序*****************************");
        int[] arr1 = new int[maxSize];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = (int) (Math.random() * arr1.length * 100 );
        }
        long begin1 = System.currentTimeMillis();
        BubbleSort(arr1);
        long end1 = System.currentTimeMillis();
        System.out.println("所花费时间为: " + (end1 - begin1));

        System.out.println("*****************测试改进的冒泡排序************************");
        int[] arr2 = new int[maxSize];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = (int) (Math.random() * arr2.length * 100 );
        }
        long begin2 = System.currentTimeMillis();
        BubbleSortPlus(arr2);
        long end2 = System.currentTimeMillis();
        System.out.println("所花费时间为: " + (end2 - begin2));

        System.out.println("*****************测试选择排序*****************************");
        int[] arr3 = new int[maxSize];
        for (int i = 0; i < arr3.length; i++) {
            arr3[i] = (int) (Math.random() * arr3.length * 100 );
        }
        long begin3 = System.currentTimeMillis();
        SelectSort(arr3);
        long end3 = System.currentTimeMillis();
        System.out.println("所花费时间为: " + (end3 - begin3));

        System.out.println("*****************测试直接插入排序************************");
        int[] arr4 = new int[maxSize];
        for (int i = 0; i < arr4.length; i++) {
            arr4[i] = (int) (Math.random() * arr4.length * 100 );
        }
        long begin4 = System.currentTimeMillis();
        SelectSort(arr4);
        long end4 = System.currentTimeMillis();
        System.out.println("所花费时间为: " + (end4 - begin4));

    }

    // 冒泡排序
    public void BubbleSort(int[] arr){
        // 思想：依次比较前后两个元素，将较大位置元素放在后面
        // 第 i 趟 需要 比较 j 次 => i +j = n (n 为元素总数)
        int i,j,temp;                                     // 交换临时存储 (i : 总共需要几趟     j: 每一趟需要的次数)
        for (i = 0; i < arr.length - 1; i++){             //  n个元素，总共需要比较 n - 1趟
            for (j = 0; j < arr.length - (1 + i); j++){   // 第 i 趟需要比较 n - i 次  => 第1趟时 i = 0, 第2趟时 i = 1; 第3趟时 i = 2 ......
                if (arr[j] > arr[j + 1]){                 // 后面的元素小于前面的元素 -> 需要交换位置
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    //改进的冒泡排序
    public void BubbleSortPlus(int[] arr){
        int flag =  1;                                     // flag 作为是否有交换的标记
        int m,j,temp;                                      // 交换临时存储 (m : 总共需要几趟     j: 每一趟需要的次数)
        for (m = 0; m < arr.length - 1 && flag ==1; m++){  //  n个元素，总共需要比较 n - 1趟
            flag = 0;
            for (j = 0; j < arr.length - 1 -m; j++){       // 第 m 趟需要比较 n - m 次 (java下标从 0 开始，第一趟时 i = 0, 所以有 m + j = n - 1  =>  j = n - 1 -m )
                if (arr[j] > arr[j + 1]){                  // 后面的元素小于前面的元素 -> 需要交换位置 (发生逆序)
                    flag = 1;                              // 发生交换，flag设置为1。若本趟没有发生交换则 flag 保持为 0
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    //简单选择排序
    public void SelectSort(int[] arr){
        // 思想：遍历未排序的所有元素后将最小值按顺序放到指定位置
        int i,j,k,temp;
        for (i = 0; i < arr.length - 1; i++){           // 总共需要 n - 1 趟
            k = i;                                      // k 为当前最小值的下标（假定当前下标为 i 的元素最小）
            for (j = i + 1; j < arr.length; j++){       // 从当前已经排好序的下一个元素开始遍历
                if (arr[j] < arr[k]){                   // 当前位置为最小值
                    k = j;                              //  记录最小值位置
                }
            }
            if (k != i){                                // 如果开始位置不是最小值位置
                temp = arr[i];                          // 交换下标为 i（初始值）和下标为 k(最小值) 的元素
                arr[i] = arr[k];
                arr[k] = temp;                          // 将最小值位置插入
            }
        }
    }

    // 直接插入排序
    public void InsertSort(int[] arr){
        //思想： 前i个有序，后j个无序，依次从无序序列中选取元素插入到有序序列中 （查找到指定位置为重点 => 二分查找）
        int i,insertIndex;                  // i为无序位置的第一个元素，insertIndex 为插入过程中寻找的下标
        for (i = 1; i < arr.length; i++){   // 从第二个元素开始依次执行 n - 1 次
            if (arr[i] < arr[i -1]){        // 当前位置元素小于前一个，需要进行排序
                int x = arr[i];             // 复制当前需要排序的元素
                for (insertIndex = i - 1; insertIndex >= 0 && x < arr[insertIndex]; insertIndex--){     // 顺序查找插入位置 （从后往前）=> 若 x < arr[insertIndex] 则循环继续
                    arr[insertIndex + 1] = arr[insertIndex];                                            // 当前位置比要插入的元素大，当前元素后移 （当前位置 j 即为要插入的位置）
                }
                arr[insertIndex + 1] = x;             // 重要： 将 x 插入到正确位置 (上一次进入循环时 insertIndex 已经减 1)
            }
        }
    }

    // 折半插入排序
    public void BInsertSort(int[] arr){
        int i,j;  // i为无序位置的第一个元素，j为插入过程中寻找的下标
        for (i = 1; i < arr.length; i++) {   // 从第二个元素开始依次执行
            int x = arr[i];                  // 复制当前需要排序的元素
            int low = 0; int high = i - 1;   // 采用二分法查找插入位置
            while ( low <= high){
                int mid = (low + high) / 2;
                if ( x < arr[mid]){          // 当前元素小于中间位置元素
                    high = mid - 1;          // 在左半区查找
                }else {
                    low = mid + 1;           // 在右半区查找
                } // 循环结束， high + 1 为插入位置
            }
            for (j = i - 1; j >= high + 1; j--){
                arr[j + 1] = arr[j];        //  移动元素，空出 high + 1 的位置
            }
            arr[high + 1] = x;              //  将元素插入到正确位置
        }
    }


}
