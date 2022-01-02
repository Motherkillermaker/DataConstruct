package ZuoGod;

import java.util.Arrays;

/**
 * 排序总结
 *
 * 1)不基于比较的排序，对样本数据有严格要求，不易改写（桶排序）
 *
 * 2)基于比较的排序，只要规定好两个样本怎么比大小就可以直接复用
 *
 * 3)基于比较的排序，时间复杂度的极限是O(NlogN) / 归并（稳定） / 快排 / 堆
 *
 * 4)时间复杂度O(N*logN)、 额外空间复杂度低于O(N)、 且稳定的基于比较的排序是不存在的。
 *
 * 5)为了绝对的速度选快排、为了省空间选堆排、为了稳定性选归并
 *
 * 6)不具备稳定性的排序 ：选择排序、快速排序、堆排序
 *   具备稳定性的排序 ： 冒泡排序、插入排序、归并排序、一切桶排序思想下的排序
 *
 * 比较器的使用
 *
 * Compare 接口的实现
 * 从小到大  o1.id - o2.id < 0  =>  o1 < o2  =>  参数和返回 表达式 顺序一致
 * 从大到小  o2.id - o1.id > 0  =>  o2 > o1  =>  参数和返回 表达式 顺序相反
 *
 *
 * @author CJYong
 * @create 2021-12-26 22:34
 */
public class two {
    public static void main(String[] args) {
        int[] array = getRandomArray(10);
        System.out.println(Arrays.toString(array));
        mergeSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void mergeSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }
        process(arr,0,arr.length - 1);
    }

    /**
     * 递归过程
     * @param arr 待排序数组
     * @param L 需要排序的起始位置
     * @param R 需要排序的结尾位置
     */
    public static void process(int[] arr,int L,int R){
        if (L == R){
            // 一个数 => 直接返回
            return;
        }
        // 求取中点位置 L + (R - L) / 2
        int mid = L + ((R - L) >> 1);
        process(arr,L,mid);
        process(arr,mid + 1,R);
        merge(arr,L,mid,R);
    }

    /**
     * 合并两个有序数组（递归后的合并过程）
     * @param arr 数组
     * @param L 左边位置
     * @param M 第一个数组的最后一个位置
     * @param R 第二个数组的最后一个位置
     */
    public static void merge(int[] arr,int L,int M,int R){
        int[] help = new int[R - L + 1];
        // help 数组指针
        int i = 0;
        // 左侧数组指针
        int p1 = L;
        // 右侧数组指针
        int p2 = M + 1;
        // p1 和 p2 都没越界时
        while (p1 <= M && p2 <= R){
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++]:arr[p2++];
        }
        // p2 越界 => 将 p1 剩余部分拷贝进数组
        while (p1 <= M){
            help[i++] = arr[p1++];
        }
        // p1 越界 => 将 p2 剩余部分拷贝进数组
        while (p2 <= R){
            help[i++] = arr[p2++];;
        }
        // 将新数组替换原来数组
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
    }

    /**
     * 堆排序
     * @param arr
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            //构建大顶堆
            heapInsert(arr, i);
        }
        int size = arr.length;
        swap(arr, 0, --size);
        while (size > 0) {
            //后边的n-1次构建大根堆
            heapify(arr, 0, size);
            //每次都要交换最大、最小值
            swap(arr, 0, --size);
        }
    }

    /**
     * 构造大根堆（ Heapinsert  => 向上和父节点比较  =>  构建成大根堆）
     * @param arr
     */
    public static void heapInsert(int[] arr,int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            // 不断地循环回去，直到顶的结点的时候，index为0，但是(index - 1) / 2也为0
            // 所以循环到顶了就终止，构建堆或者完全二叉树这种结构的时候根本就不用考虑兄弟结点的，所以只考虑左结点就是对的
            index = (index - 1) / 2;
        }
    }

    /**
     * 将剩余的数构造成大根堆（Heapify => 向下和子节点比较）
     * @param arr
     * @param index
     * @param size
     */
    public static void heapify(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {
            //找出大值的索引，比较的是兄弟节点
            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            //找出大值的索引，与父节点比较
            largest = arr[largest] > arr[index] ? largest : index;
            //如果最大值的索引已经等于父索引，就不再继续循环了
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    //交换数组中两个元素的值
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 获取随机数组
     * @param maxSize
     * @return
     */
    public static int[] getRandomArray(int maxSize){
        int[] array = getArray(maxSize);
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * array.length * 10 );
        }
        return array;
    }

    /**
     * 获取数组
     * @param maxSize
     * @return
     */
    public static int[] getArray(int maxSize){
        return new int[maxSize];
    }

}
