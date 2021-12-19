package GuiguDataConstruct.binarySortTree;

/**
 * @title: BinarySortTreeDemo
 * @Author Tan
 * @Date: 2021/12/19 10:37
 * @Version 1.0
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9};
        // 建立二叉排序树
        BinarySortTree binarySortTree = new BinarySortTree();
        // 添加结点
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        // 中序遍历
        binarySortTree.infixOrder();
    }
}

