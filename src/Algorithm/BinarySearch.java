package Algorithm;

import java.util.ArrayList;

public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = new int[]{1,4,7,7,16,16,80,90,100,100,100,302,302,965};
        int i = BinarySearch(arr, 4);
        System.out.println(i);

    }

    //二分法查找(数组必须有序)
    public static int BinarySearch(int[] arr,int key){
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
    public static int BinarySearchRecursion(int[] arr,int key,int low,int high){
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
    public static ArrayList<Integer> BinarySearchRecursionPlus(int[] arr, int key, int low, int high){
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

}


