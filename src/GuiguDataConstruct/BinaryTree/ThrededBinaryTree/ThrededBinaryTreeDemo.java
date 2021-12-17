package GuiguDataConstruct.BinaryTree.ThrededBinaryTree;

/**
 * @title: ThrededBinaryTreeDemo
 * @Author Tan
 * @Date: 2021/12/17 14:43
 * @Version 1.0
 */
public class ThrededBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode root =  new HeroNode(1,"Tom");
        HeroNode node2 =  new HeroNode(3,"Jack");
        HeroNode node3 =  new HeroNode(6,"Smith");
        HeroNode node4 =  new HeroNode(8,"Mary");
        HeroNode node5 =  new HeroNode(10,"King");
        HeroNode node6 =  new HeroNode(14,"Dim");
        // 创建二叉树
        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        // 测试线索化
        ThrededBinaryTree thrededBinaryTree = new ThrededBinaryTree();
        thrededBinaryTree.root = root;
        thrededBinaryTree.ThredNode();
        HeroNode leftNode =  node5.left;
        HeroNode rightNode =  node5.right;
        System.out.println("10号结点的前驱结点是：" + leftNode);
        System.out.println("10号结点的后继结点是：" + rightNode);
        // 线索化后的遍历
        thrededBinaryTree.ThredShow();
    }
}

