package GuiguDataConstruct.BinaryTree;

/**
 * @title: ArrayBinaryTree
 * @Author Tan
 * @Date: 2021/12/17 14:22
 * @Version 1.0
 */
public class ArrayBinaryTree {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
    }

}

// 编写ArrayBinaryTree
class ArrBinaryTree{
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    // 重载preOrder
    public void preOrder(){
        this.preOrder(0);
    }

    // 前序遍历
    public void preOrder(int index){
        // 如果为空或者长度为0
        if (arr == null || arr.length == 0){
            System.out.println("数组为空，无法前序遍历");
        }
        System.out.println(arr[index]);
        // 向左递归遍历
        if (index * 2 + 1 < arr.length){
            preOrder(2 * index + 1);
        }
        // 向右递归遍历
        if (index * 2 + 2 < arr.length){
            preOrder(2 * index + 2);
        }

    }
}

