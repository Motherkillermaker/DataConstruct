import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

        int maxSize = 1000000;
        System.out.println("*****************测试冒泡排序*****************************");
//        testSolution(maxSize,"BubbleSort");

        System.out.println("*****************测试改进的冒泡排序************************");
//        testSolution(maxSize,"BubbleSortPlus");

        System.out.println("*****************测试选择排序*****************************");
//        testSolution(maxSize,"SelectSort");

        System.out.println("*****************测试直接插入排序************************");
//        testSolution(maxSize,"InsertSort");

        System.out.println("*****************测试折半插入排序************************");
//        testSolution(maxSize,"BInsertSort");

        System.out.println("*****************测试希尔排序（交换法）排序************************");
//        testSolution(maxSize,"shellSortExchange");

        System.out.println("*****************测试希尔排序（移位法）排序************************");
        testSolution(maxSize,"shellSort");

        System.out.println("*****************测试快速排序排序************************");
        int[] arr = getRandomArray(maxSize);
        long begin = System.currentTimeMillis();
        QuickSort(arr,0,arr.length - 1);
        long end = System.currentTimeMillis();
        System.out.println("所花费时间为: " + (end - begin));

    }

    /** 测试方法
     * 通过传入方法名来调用方法
     * @param maxSize
     * @param solution
     */
    public void testSolution(int maxSize,String solution){
        int[] array = getArray(maxSize);
        try {
            Class<?> clazz = Class.forName("Sort");                             // 获取类
            Method method = clazz.getMethod(solution, int[].class);             // 获取方法
            for (int i = 0; i < array.length; i++) {
                array[i] = (int) (Math.random() * array.length * 100 );
            }
            long begin = System.currentTimeMillis();
            try {
                try {
                    method.invoke(clazz.newInstance(),array);                   // 方法的执行
                } catch (InvocationTargetException | InstantiationException e) {
                    e.printStackTrace();
                }
            } catch (IllegalAccessException e) {
                System.out.println("输入的参数类型异常，请重新输入！");
            }
            long end = System.currentTimeMillis();
            System.out.println("所花费时间为: " + (end - begin));
        } catch (ClassNotFoundException e) {
            System.out.println("没有该类！");
        } catch (NoSuchMethodException e) {
            System.out.println("没有该方法");
        }


    }

    // 获取随机数组
    public int[] getRandomArray(int maxSize){
        int[] array = getArray(maxSize);
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * array.length * 100 );
        }
        return array;
    }

    // 获取数组
    public int[] getArray(int maxSize){
        return new int[maxSize];
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
        //思想： 前i个有序，后j个无序，依次从无序序列中选取元素插入到有序序列中 （查找到指定位置为重点 =>  依次查找/二分查找）
        int i,insertIndex;                  // i为无序位置的第一个元素，insertIndex 为插入过程中寻找的下标
        for (i = 1; i < arr.length; i++){   // 从第二个元素开始依次执行 n - 1 次
            if (arr[i] < arr[i -1]){        // 当前位置元素小于前一个，需要进行排序
                int x = arr[i];             // 复制当前需要排序的元素
                for (insertIndex = i - 1; insertIndex >= 0 && x < arr[insertIndex]; insertIndex--){     // 顺序查找插入位置 （从后往前）=> 若 x < arr[insertIndex] 则循环继续
                    arr[insertIndex + 1] = arr[insertIndex];                                            // 当前位置比要插入的元素大(循环条件)，当前元素后移 （当前位置 j + 1 即为要插入的位置）
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
                }                            // 循环结束， high + 1 为插入位置
            }
            for (j = i - 1; j >= high + 1; j--){
                arr[j + 1] = arr[j];         //  移动元素，空出 high + 1 的位置
            }
            arr[high + 1] = x;               //  将元素插入到正确位置
        }
    }

    //希尔排序(交换法 => 速度较慢)
    public void shellSortExchange(int[] arr){
        //思想：先根据步长将数组分组，组内先排序（交换），最后在进行一次冒泡排序 => 减少插入排序比较和交换的次数
        int temp = 0;
        // 定义一共循环的轮数
        for (int gap = arr.length / 2; gap > 0 ; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                // 遍历各组中的元素（共 gap 个组），步长为 gap => 组内冒泡排序
                for (int j = i - gap; j >= 0 ; j -= gap) {
                    // 每组中如果当前元素大于后面元素则交换
                    if (arr[j] > arr[j + gap]){
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }

    }

    //希尔排序（位移法 => 速度较快）
    public static void shellSort(int[] arr){
        int insertIndex = 0;
        for (int gap = arr.length / 2; gap > 0 ; gap /= 2) {
            for (int i = gap; i < arr.length ; i++) {
                if (arr[i] < arr[i - gap]){         // 当前组内后面的元素比前面的小
                    int x = arr[i];                 // 复制当前需要排序的元素（较小元素）
                    // 借助插入排序的思想 => 此时为组内元素进行比较,间距为gap
                    // 从当前元素的前一个开始，满足条件则交换，最后一次进入循环时 insertIndex 已经移动
                    for (insertIndex = i - gap; insertIndex >= 0 && x < arr[insertIndex] ; insertIndex-= gap) {
                        arr[insertIndex + gap] = arr[insertIndex];  // 当前位置比要插入的元素大(循环条件)，当前元素后移 （当前位置 insertIndex + gap 即为要插入的位置）
                    }
                    arr[insertIndex + gap] = x;
                }
            }
        }
    }

    //希尔排序(移位法韩顺平版本)
    public void shellSortMove(int[] arr){
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;                      // 保存待插入位置的下标 (第一轮为中间位置的下一个元素)
                int x = arr[j];                 // 保存待插入元素
                if (arr[j] < arr[j - gap]){     // 组内需要排序
                    while (j - gap >= 0 && x < arr[j-gap]){
                        arr[j] = arr[j - gap];  // 前面的元素（较大）移到了后面
                        j -= gap;
                    }
                    // 找到了插入位置
                    arr[j] = x;
                }
            }
        }

    }

    //快速排序 （low high 为开始时的数组索引 => 递归）
    public void QuickSort(int[] arr,int low,int high){
        if (low < high){
            // 长度大于1 => 继续递归
            int pivotloc = Partition(arr,low,high);     // 将数组一分为二，pivotloc为枢轴元素排好序的位置
            QuickSort(arr,low,pivotloc - 1);       // 小于中枢的元素组成的数组
            QuickSort(arr,pivotloc + 1,high);       // 大于中枢的元素组成的数组
        }
    }

    // 快速排序确定中枢位置
    public int Partition(int[] arr,int low,int high){
        int pivot = arr[low];   // 中间变量,初始值为传入数组第一个元素（中枢值）
        while (low < high){
            // 从后面找大于中枢的值
            // 拓展：若从大到小排序，只需把大于中枢的往前搬，小于中枢的往后搬即可
            while (low < high && arr[high] >= pivot ){
                high--;
            }
            arr[low] = arr[high];     //  小于中枢的从后往前搬
            // 从前面找小于中枢的值
            while (low < high && arr[low] <= pivot){
                low++;
            }
            arr[high] = arr[low];    //  大于中枢的从前往后搬
        }
        // 结束循环，此时 low 和 high 相等 => 该位置用于放置中枢位置
        arr[low] = pivot;
        return low ;
    }

    // 归并排序
    public void MergeSort(int[] arr){

    }


}

