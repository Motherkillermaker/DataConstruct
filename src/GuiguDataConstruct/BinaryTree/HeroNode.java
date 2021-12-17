package GuiguDataConstruct.BinaryTree;

/** 二叉树结点
 * @title: HeroNode
 * @Author Tan
 * @Date: 2021/12/17 9:55
 * @Version 1.0
 */
public class HeroNode {
    public int no;
    public String name;
    public HeroNode left;    // 左右子结点默认为空
    public HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    // 前序遍历(根左右)
    public void preOrder(){
        System.out.println(this);   // 输出父节点
        // 递归向左子树前序遍历
        if (this.left != null){
            this.left.preOrder();
        }
        // 递归向右子树前序遍历
        if (this.right != null){
            this.right.preOrder();
        }
    }

    // 中序遍历（左根右）
    public void infixOrder(){
        // 递归向左子树中序遍历
        if (this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);   // 输出父结点
        // 递归向右子树中序遍历
        if (this.right != null){
            this.right.infixOrder();
        }
    }

    // 后序遍历（左右根）
    public void postOrder(){
        // 递归向左子树后序遍历
        if (this.left != null){
            this.left.postOrder();
        }
        // 递归向右子树后序遍历
        if (this.right != null){
            this.right.postOrder();
        }
        System.out.println(this);   // 输出父结点
    }

    // 前序遍历查找(根左右)
    public HeroNode preOrderSearch(int no){
        HeroNode resNode = null;
        // 判断当前节点是否符合
        if (this.no == no){
            return this;
        }
        // 左子节点不为空 => 向左递归查找
        if (this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null){
            // 左递归找到对应节点
            return resNode;
        }
        // 右子节点不为空 => 向右递归查找
        if (this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        // 右递归找到 => 返回对应节点   未找到 => 返回null
        return resNode;
    }

    // 中序遍历查找（左根右）
    public HeroNode infixOrderSearch(int no){
        HeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.no == no){
            return this;
        }
        if (this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    // 后序遍历查找（左右根）
    public HeroNode postOrderSearch(int no){
        HeroNode resNode = null;
        if (this.left != null){
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if (this.no == no){
            return this;
        }
        return resNode;
    }

    // 删除结点（叶子结点直接删除  非叶子结点删除该树 => 判断当前结点的子结点是否需要删除 => 链表的删除（让指针指向待删除的前一个结点））
    public void deleteNode(int no){
        // 判断是否为空已经在树的类中 => 调用方法时树不为空
        // 左子树不为空且左子节点为待删除的结点
        if (this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        // 右子树不为空且右子节点为待删除的结点
        if (this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        // 左子树递归删除
        if (this.left != null){
            this.left.deleteNode(no);
        }
        // 右子树递归删除
        if (this.right != null){
            this.right.deleteNode(no);
        }


    }

}

