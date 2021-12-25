package GuiguDataConstruct.binarySortTree;/**
 *
 * @title: Node
 * @Author Tan
 * @Date: 2021/12/19 10:37
 * @Version 1.0
 */


public class Node {
    int value;
    Node left;
    Node right;


    public Node(int value) {
        this.value = value;
    }

    // 添加节点
    public void add(Node node){
        if (node == null){
            return;
        }
        // 判断结点的值并进行递归查找插入位置
        // 添加节点值小于当前节点值 => 左子树添加
        if (node.value < this.value){
            if (this.left == null){
                // 左子节点为空，直接添加
                this.left = node;
            }else {
                // 递归向左边添加
                this.left.add(node);
            }
            // 添加节点值大于当前节点值 => 右子树添加
        }else {
            if (this.right == null){
                this.right = node;
            }else {
                // 递归向右边添加
                this.right.add(node);
            }
        }
    }

    // 查找要删除的结点
    public Node findDeleteNode(int value){
        if (value == this.value){
            return this;
        }else if (value < this.value){
            // 查找的值小于当前节点 => 左子树递归查找
            if (this.left == null){
                // 左子树为空 => 直接返回
                return null;
            }
            // 左子树不为空 => 递归查找
            return this.left.findDeleteNode(value);
        }else {
            // 查找的值大于或等于当前节点 => 右子树递归查找
            if (this.right == null){
                return null;
            }
            return this.right.findDeleteNode(value);
        }
    }

    // 查找要删除节点的父节点
    public Node findDeleteParentNode(int value){
        // 判断当前节点是否为要删除节点的父节点(递归结束条件)
        if (this.left != null && this.left.value == value ||
                (this.right != null && this.right.value == value)){
            return this;
        }else {
            // 查找的值小于当前节点的值，且当前节点的左子节点不为空
            if (value < this.value && this.left != null){
                // 左子树递归查找
                return this.left.findDeleteParentNode(value);
            }else if (value >= this.value && this.right != null){
                // 右子树递归查找
                return this.right.findDeleteParentNode(value);
            }else {
                // 没有父节点
                return null;
            }
        }
    }


    // 中序遍历
    public void infixOrder(){
        if (this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

}

