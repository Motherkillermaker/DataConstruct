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
        QuickSort(arr,0,arr.length - 1);
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


}

