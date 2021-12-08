import org.junit.Test;

/**
 * @title: InsertSort
 * @Author Tan
 * @Date: 2021/12/8 11:36
 * @Version 1.0
 */
public class InsertSort {

    @Test
    public void test(){
        int[] arr = new int[]{1,95,21,2,8,7,26,17,999,123,500};
        SelectSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

    }


    // 直接插入排序
    public void InsertSort(int[] arr){
        int i,j;  // i为无序位置的第一个元素，j为插入过程中寻找的下标
        for (i = 1; i < arr.length; i++){   // 从第二个元素开始依次执行
            if (arr[i] < arr[i -1]){        // 当前位置元素小于前一个，需要进行排序
                int x = arr[i];             // 复制当前需要排序的元素
                for (j = i - 1; j >= 0 && x < arr[j]; j--){  // 顺序查找插入位置
                    arr[j + 1] = arr[j];     // 当前位置比要插入的元素大，当前元素后移
                }
                arr[j + 1] = x;               // 将 x 插入到正确位置
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

    // 冒泡排序
    public void BubbleSort(int[] arr){
        int m,j,temp;                                      // 交换临时存储 (m : 总共需要几趟     j: 每一趟需要的次数)
        for (m = 0; m < arr.length - 1; m++){             //  n个元素，总共需要比较 n - 1趟
            for (j = 0; j < arr.length - 1 -m; j++){      // 第 m 趟需要比较 n - m 次 (java下标从 0 开始，第一趟时 i = 0, 所以有 m + j = n - 1  =>  j = n - 1 -m )
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
        int flag =  1;                                      // flag 作为是否有交换的标记
        int m,j,temp;                                      // 交换临时存储 (m : 总共需要几趟     j: 每一趟需要的次数)
        for (m = 0; m < arr.length - 1 && flag ==1; m++){             //  n个元素，总共需要比较 n - 1趟
            flag = 0;
            for (j = 0; j < arr.length - 1 -m; j++){      // 第 m 趟需要比较 n - m 次 (java下标从 0 开始，第一趟时 i = 0, 所以有 m + j = n - 1  =>  j = n - 1 -m )
                if (arr[j] > arr[j + 1]){                 // 后面的元素小于前面的元素 -> 需要交换位置 (发生逆序)
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
        int i,j,k,temp;
        for (i = 0; i < arr.length; i++){     // 总共需要 n 趟
            k = i;                            // k 为当前最小值的下标
            for (j = i + 1; j < arr.length; j++){
                if (arr[j] < arr[k]){         // 当前位置为最小值
                    k = j;                    //  记录最小值位置
                }
            }
            if (k != i){                      // 如果开始位置不是最小值位置
                temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;                // 将最小值位置插入
            }
        }
    }


}

