package GuiguDataConstruct.AVL;

public class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    // 左旋转方法
    public void leftRotate(){
        // 创建新的节点 => 以当前根节点
        Node newRoot = new Node(value);
        // 把新节点的左子树 设置 为当前节点的左子树
        newRoot.left = left;
        // 把新节点的右子树 设置 为当前节点右子树的左子树
        newRoot.right = right.left;
        // 把当前节点的值替换成右子节点的值
        value = right.value;
        // 把当前节点的右子树设置成当前节点的右子树的右子树
        right = right.right;
        // 把当前的左子节点设置成新的节点
        left = newRoot;
    }

    // 右旋转方法
    public void rightRotate(){
        Node newRoot = new Node(value);
        newRoot.right = right;
        newRoot.left = left.right;
        value = left.value;
        left = left.left;
        right = newRoot;
    }

    // 返回以当前节点为根节点的树的高度 => 递归求解
    public int getHeight(){
        return Math.max(left == null ? 0 : left.getHeight(),right == null ? 0 : right.getHeight()) + 1;
    }

    // 返回左子树的高度
    public int leftHeight(){
        if (left == null){
            return 0;
        }
        return left.getHeight();
    }

    // 返回右子树的高度
    public int rightHeight(){
        if (right == null){
            return 0;
        }
        return right.getHeight();
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
        // 添加节点后树失衡
        if (rightHeight() - leftHeight() > 1){
            // 右子树的左子树高度 > 右子树的右子树高度
            if (right != null && right.leftHeight() > right.rightHeight()){
                // 对右子节点进行右旋转
                right.rightRotate();
                // 当前节点左旋转
                leftRotate();
            }else {
                leftRotate();
            }
            // 添加完后直接结束方法
            return;
        }
        if (leftHeight() - rightHeight() >1){
            // 左子树的右子树高度 > 左子树的左子树高度
            if (left != null && left.rightHeight() > left.leftHeight()){
                // 先对当前节点左节点（左子树） => 左旋转
                left.leftRotate();
                // 再对当前节点进行右旋转
                rightRotate();
            }else {
                // 直接进行右旋转
                rightRotate();
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

