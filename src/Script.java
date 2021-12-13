import java.util.Arrays;

/**
 * @title: Script
 * @Author Tan
 * @Date: 2021/12/13 13:14
 * @Version 1.0
 */
public class Script {
    public static void main(String[] args) {
        int[] arr1 = new int[]{9,5,1,3,10,7,4,6,2,8};
        shellSort(arr1);
        System.out.println(Arrays.toString(arr1));
        int[] arr2 = new int[]{5,2,8,1,7,4,6,3,9,10};
        InsertSort(arr2);
        System.out.println(Arrays.toString(arr2));

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

}

