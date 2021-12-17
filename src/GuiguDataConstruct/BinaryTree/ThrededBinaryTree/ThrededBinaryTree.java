package GuiguDataConstruct.BinaryTree.ThrededBinaryTree;


/** 构建 线索二叉树
 * @title: BinaryTree
 * @Author Tan
 * @Date: 2021/12/17 10:43
 * @Version 1.0
 */
public class ThrededBinaryTree {
    public HeroNode root;
    // 创建指向前驱节点的指针
    public HeroNode preNode = null;

    public ThrededBinaryTree() {
    }

    public ThrededBinaryTree(HeroNode root) {
        this.root = root;
    }

    // 重载线索化方法
    public void ThredNode(){
        this.ThredNode(root);
    }

    // 中序线索化（改变指针为空的结点 => 使其空指针指向前驱或者后继结点）
    public void ThredNode(HeroNode heroNode){
        if (heroNode == null){
            return;
        }
        // 线索化左子树
        ThredNode(heroNode.left);
        // 线索化当前结点
        // 左节点指向前驱
        if (heroNode.left == null){
            heroNode.left = preNode;
            heroNode.leftType = 1;
        }
        // 右节点指向后继 （此时处理的是上一个满足条件的节点的线索化 => preNode 为上一个线索化的结点）
        if (preNode != null && preNode.right == null){
            // 上一结点右指针的线索化
            preNode.right = heroNode;
            preNode.rightType = 1;
        }
        // 每处理一个节点，让preNode 跟随 当前节点移动
        preNode = heroNode;
        // 线索化右子树
        ThredNode(heroNode.right);
    }

    // 线索化二叉树后的遍历
    public void ThredShow(){
        HeroNode node = root;
        while (node != null){
            while (node.leftType == 0){
                node = node.left;           // 向左子树遍历
            }
            System.out.println(node);       // 打印线索化结点
            while (node.rightType == 1){
                // 右指针指向后继结点 （实际为当前子树的根节点）
                node = node.right;
                System.out.println(node);
            }
            // 循环结束，rightType 不为 1
            node = node.right;              // 继续遍历（中序遍历 => 左根右）
        }
    }

    // 前序遍历
    public void preOrder(){
        if (this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("当前二叉树为空，无法遍历！");
        }
    }

    // 中序遍历
    public void infixOrder(){
        if (this.root != null){
            this.root.infixOrder();
        }else {
            System.out.println("当前二叉树为空，无法遍历！");
        }
    }

    //后续遍历
    public void postOrder(){
        if (this.root != null){
            this.root.postOrder();
        }else {
            System.out.println("当前二叉树为空，无法遍历！");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int no){
        if (root != null){
            return root.preOrderSearch(no);
        }else {
            return null;
        }
    }

    // 中序查找
    public HeroNode infixOrderSearch(int no){
        if (root != null){
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }

    // 后序查找
    public HeroNode postOrderSearch(int no){
        if (root != null){
            return root.postOrderSearch(no);
        }else {
            return null;
        }
    }

    // 删除结点
    public void deleteNode(int no){
        if (root != null){
            // 该树只有一个结点
            if (root.no == no){
                root = null;
            }else {
                // 开始递归删除
                root.deleteNode(no);
            }
        }else {
            System.out.println("该树为空，无法删除！");
        }
    }

}