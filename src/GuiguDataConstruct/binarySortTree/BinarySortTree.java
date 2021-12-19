package GuiguDataConstruct.binarySortTree;

/** 二叉排序树
 * @title: BinarySortTree
 * @Author Tan
 * @Date: 2021/12/19 10:36
 * @Version 1.0
 */
public class BinarySortTree {

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
            return;
        }else {
            // 找到删除的节点
            findDeleteNode(value);
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


    // 中序遍历
    public void infixOrder(){
        if (root != null){
            root.infixOrder();
        }else {
            System.out.println("二叉排序树为空，无法遍历");
        }
    }

}

