package GuiguDataConstruct.AVL;

public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};          // 左旋转示例数组
//        int[] arr = {10,12,8,9,7,6};        // 右旋转示例数组
        int[] arr = {10,11,7,6,8,9};
        // 创建 AVL 树
        AVLTree avlTree = new AVLTree();
        // 添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        // 中序遍历
        avlTree.infixOrder();
        // 查看树的高度
        System.out.println("平衡");
        System.out.println("树高度：" + avlTree.root.getHeight());
        System.out.println("左子树高度：" + avlTree.root.leftHeight());
        System.out.println("右子树高度：" + avlTree.root.rightHeight());
        System.out.println(avlTree.root);

    }
}
