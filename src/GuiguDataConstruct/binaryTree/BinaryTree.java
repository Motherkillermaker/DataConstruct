package GuiguDataConstruct.binaryTree;

/** 构建二叉树
 * @title: BinaryTree
 * @Author Tan
 * @Date: 2021/12/17 10:43
 * @Version 1.0
 */
public class BinaryTree {
    public HeroNode root;

    public BinaryTree() {
    }

    public BinaryTree(HeroNode root) {
        this.root = root;
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

