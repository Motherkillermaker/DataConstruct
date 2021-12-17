package GuiguDataConstruct.BinaryTree;

/**
 * @title: BinaryTreeDemo
 * @Author Tan
 * @Date: 2021/12/17 10:54
 * @Version 1.0
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        // 创建二叉树
        BinaryTree binaryTree =  new BinaryTree();
        // 创建需要的结点
        HeroNode root =  new HeroNode(1,"宋江");
        HeroNode node2 =  new HeroNode(2,"吴用");
        HeroNode node3 =  new HeroNode(3,"卢俊义");
        HeroNode node4 =  new HeroNode(4,"林冲");
        HeroNode node5 =  new HeroNode(5,"关胜");
        // 手动创建二叉树 => 后续以递归方式创建二叉树
        root.left = node2;
        root.right = node3;
        node3.left = node5;
        node3.right = node4;
        binaryTree.root = root;
        // 测试二叉树的遍历
        System.out.println("前序遍历测试");
        binaryTree.preOrder();
        System.out.println("中序遍历测试");
        binaryTree.infixOrder();
        System.out.println("后序遍历测试");
        binaryTree.postOrder();
        System.out.println();
        // 测试二叉树的查找
        System.out.println("前序遍历查找");
//        HeroNode resNode = binaryTree.preOrderSearch(5);
        System.out.println("中序遍历查找");
//        HeroNode resNode = binaryTree.infixOrderSearch(5);
        System.out.println("后序遍历查找");
        HeroNode resNode = binaryTree.postOrderSearch(5);
        if (resNode != null){
            System.out.println(resNode.no + "号节点的信息为：" + resNode);
        }else {
            System.out.println("没有找到该节点");
        }
        // 测试二叉树的删除
        binaryTree.deleteNode(3);
        binaryTree.preOrder();

    }
}

