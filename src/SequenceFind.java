import jdk.nashorn.internal.ir.IfNode;
import org.junit.Test;

/**
 * @title: SequenceFind
 * @Author Tan
 * @Date: 2021/12/7 10:21
 * @Version 1.0
 */
public class SequenceFind {

    @Test
    public void test(){
        int[] arr = new int[]{1,2,3,4,8,9,12,17,19,30,32};
        System.out.println(TwoFind(arr, 32));
    }

    // 顺序查找
    public int SearchSeq(int[] arr,int key){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key){
                return i + 1;
            }
        }
        return -1;
    }

    //二分法查找
    public int TwoFind(int[] arr,int key){
        int low = 0;                            // 初始最小值索引
        int high = arr.length - 1;              // 初始最大值索引
        while (low <= high) {                   //最小值和最大值中间不为空
            int mid = (low + high) / 2;
            if (key == arr[mid]) {
                return mid + 1;                 // 查找的值等于中间位置，返回mid的索引
            } else if (key < arr[mid]) {
                high = mid - 1;                 // 查找的值比中间位置小，移动high
            } else if (key > arr[mid]) {
                low = mid + 1;                  // 查找的值比中间位置大，移动low
            }
        }
        return 0;                               // 没有找到目标元素，返回 0
    }

}

