package GuiguDataConstruct.AVL;

public class AVLTree {

    public Node root;

    // 添加节点
    public void add(Node node){
        if (root == null){
            root = node;
        }else {
            root.add(node);
        }
    }

    // 删除结点
    public void delete(int value){
        if (root == null){
            // 根节点为空直接结束
            return;
        }else {
            // 找到删除的节点
            Node node = findDeleteNode(value);
            if (node == null){
                return;
            }
            // 1. 删除节点为 root 节点 => 将根节点置空
            if (root.left == null && root.right == null){
                root = null;
                return;
            }
            // 2. 查找待删除结点的父结点
            Node parentNode = findDeleteParentNode(value);
            // 3. 删除节点为叶子节点
            if (node.left == null && node.right == null){
                // 判断当前节点是左子节点还是右子节点
                if (parentNode.left != null && parentNode.left.value == value){
                    // 左子节点
                    parentNode.left = null;
                }else if (parentNode.right != null && parentNode.right.value == value){
                    // 右子节点
                    parentNode.right = null;
                }
                // 4. 删除节点既有左子树又有右子树 (左子树中的最大节点 或者 右子树的最小节点替换)
            }else if (node.left != null && node.right != null){
                int minValue = findNodeMin(node.right);
                node.value = minValue;
                // 5. 删除节点只有左子节点 或者 右子节点
            }else {
                // 含有左子节点
                if (node.left != null){
                    //  待删除结点不是根结点
                    if (parentNode != null) {
                        // 待删除结点为父结点的左子结点
                        if (parentNode.left.value == value) {
                            // 父节点的左子节点 => 待删除结点的左子节点
                            parentNode.left = node.left;
                            // 待删除结点为父结点的右子结点
                        } else {
                            // 父节点的右子节点 => 待删除结点的左子节点 （待删除结点只有左子结点）
                            parentNode.right = node.left;
                        }
                    }else {
                        // 待删除结点为根结点
                        root = node.left;
                    }
                    // 含有右子结点
                }else {
                    if (parentNode != null){
                        // 待删除结点为父节点的左子结点
                        if (parentNode.left.value == value) {
                            // 父结点的左子结点 => 待删除结点的右子结点
                            parentNode.left = node.right;
                            // 待删除结点为父结点的右子结点
                    }else {
                            // 待删除结点为根结点
                            root = node.right;
                        }
                    }else {
                        // 父节点的右子节点 => 待删除结点的左子节点 （待删除结点只有右子结点）
                        parentNode.right = node.right;
                    }
                }
            }
        }
    }

    // 查找要删除的节点
    public Node findDeleteNode(int value){
        if (root == null){
            return null;
        }else {
            return root.findDeleteNode(value);
        }
    }

    // 查找要删除节点的父节点
    public Node findDeleteParentNode(int value){
        if (root == null){
            return null;
        }else {
            return root.findDeleteParentNode(value);
        }
    }

    // 查找以Node为根节点的二叉排序树的最小值,并删除最小节点
    public int findNodeMin(Node node){
        Node temp = node;
        // 循环查找左节点 => 最小值
        while (temp.left != null){
            temp = temp.left;
        }
        //循环结束 temp = 最小节点
        // 删除最小节点
        delete(temp.value);
        // 返回以Node为根节点的二叉排序树的最小值
        return temp.value;
    }

    // 中序遍历
    public void infixOrder(){
        if (root != null){
            root.infixOrder();
        }else {
            System.out.println("二叉排序树为空，无法遍历");
        }
    }

}
