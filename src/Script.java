import java.util.Arrays;

/**
 * @title: Script
 * @Author Tan
 * @Date: 2021/12/13 13:14
 * @Version 1.0
 */
public class Script {
    public static void main(String[] args) {
        int[] arr =  getRandomArray(50);
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    // 直接插入排序
    public static void InsertSort(int[] arr){
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

    // 获取随机数组
    public static int[] getRandomArray(int maxSize){
        int[] array = getArray(maxSize);
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * array.length * 100 );
        }
        return array;
    }

    // 获取数组
    public static int[] getArray(int maxSize){
        return new int[maxSize];
    }

    //希尔排序（位移法）
    public static void shellSort(int[] arr){
        int insertIndex = 0;
        for (int gap = arr.length / 2; gap > 0 ; gap /= 2) {
            for (int i = gap; i < arr.length ; i++) {
                if (arr[i] < arr[i - gap]){         // 当前组内后面的元素比前面的小
                    int x = arr[i];                 // 复制当前需要排序的元素（较小元素）
                    // 借助插入排序的思想 => 此时为组内元素进行比较,间距为gap
                    for (insertIndex = i - gap; insertIndex >= 0 && x < arr[insertIndex] ; insertIndex-= gap) {
                        arr[insertIndex + gap] = arr[insertIndex];  // 当前位置比要插入的元素大(循环条件)，当前元素后移 （当前位置 j + 1 即为要插入的位置）
                    }
                    arr[insertIndex + gap] = x;
                }
            }
        }
    }

    //快速排序 （low high 为开始时的索引）
    public static void QuickSort(int[] arr,int low,int high){
        if (low < high){
            // 长度大于1 => 继续递归
            int pivotloc = Partition(arr,low,high);     // 将数组一分为二，pivotloc为枢轴元素排好序的位置
            QuickSort(arr,low,pivotloc - 1);
            QuickSort(arr,pivotloc + 1,high);
        }
    }

    // 快速排序确定中枢位置
    public static int Partition(int[] arr,int low,int high){
        int pivot = arr[low];   // 中间变量,初始值为传入数组第一个元素（中枢值）
        while (low < high){
            // 从后面找大于中枢的值
            while (low < high && arr[high] >= pivot ){
                high--;
            }
            arr[low] = arr[high];     // 将大于中枢的元素放置到 low 位置
            // 从前面找小于中枢的值
            while (low < high && arr[low] <= pivot){
                low++;
            }
            arr[high] = arr[low];
        }
        // 结束循环，此时 low 和 high 相等 => 该位置用于放置中枢位置
        arr[low] = pivot;
        return low ;
    }

    // 归并排序
    public static void MergeSort(int[] arr,int start,int end){
        if (start < end){
            int mid = (start + end) / 2;        // 划分子序列
            MergeSort(arr,start,mid);           // 左子序列递归
            MergeSort(arr,mid + 1,end);    // 右子序列递归
            merge(arr,start,mid,end);           // 排序好的数组合并
        }
    }

    // 两路归并排序 => 两个有序序列合并为一个子序列
    public static void merge(int[] arr,int left,int mid,int right){
        int[] temp = new int[arr.length];       // 辅助数组
        int p1 = left,p2 = mid + 1,k = left;    // p1、p1为检测指针，K为temp中的指针
        while (p1 <=  mid && p2 <= right){
            if (arr[p1] <= arr[p2]){
                temp[k] = arr[p1];
                p1++; k++;
            }else {
                temp[k] = arr[p2];
                p2++;k++;
            }
        }
        // 第一个序列检测完
        while (p1 <= mid){
            temp[k] = arr[p1];
            p1++; k++;
        }
        // 第二个序列检测完
        while (p2 <= right){
            temp[k] = arr[p2];
            p2++;k++;
        }
        // 复制回原来的数组
        for (int i = left; i <= right ; i++) {
            arr[i] = temp[i];
        }
    }

    //基数排序（桶排序）
    public static void radixSort(int[] arr){
        // 思想： 将数组统一为一样的位数长度，依次从最低位进行排序
        // 得到数组中最大的数的位数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max){
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();            // 最大数的位数
        int[][] bucket = new int[10][arr.length];       //0 - 9,长度为数组长度
        int[] ElementCounts = new int[10];              // 记录每个桶的元素个数（0-9）
        for (int i = 0,n = 1; i < maxLength; i++,n *= 10) {
            // 按照个、十、百的顺序放入桶中
            for (int j = 0; j < arr.length; j++) {
                // 取出个位数/十位数/百位数的值
                int finalNum = arr[j] / n % 10;
                // 放入桶中(ge表示该放哪个桶，)
                bucket[finalNum][ElementCounts[finalNum]] = arr[j];
                ElementCounts[finalNum]++;
            }
            // 遍历每一个桶，依次从桶中取出
            int index = 0;
            for (int k = 0; k < ElementCounts.length; k++) {
                // 如果有数据则取出
                if (ElementCounts[k] != 0){
                    for (int l = 0; l < ElementCounts[k]; l++) {
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                ElementCounts[k] = 0;           // 将桶清零
            }
            System.out.println("第" + (i + 1)+ "轮，排序后 arr= " + Arrays.toString(arr));
        }
    }


}

