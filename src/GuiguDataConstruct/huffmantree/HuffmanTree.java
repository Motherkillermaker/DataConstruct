package GuiguDataConstruct.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**  创建哈夫曼树（wpl最小的即为哈夫曼树）
 * 结点的带权的路径长度（）：从根节点到该结点直接的路径长度与该结点的权的乘积
 * 树的带权路径长度（wpl）: 所有叶子结点的带权路径长度之和
 * @title: HuffmanTree
 * @Author Tan
 * @Date: 2021/12/18 10:49
 * @Version 1.0
 */
public class HuffmanTree {

    public Node root;

    public HuffmanTree() {
    }

    public HuffmanTree(Node root) {
        this.root = root;
    }

    // 前序遍历 (根左右)
    public void preOrder(){
        if (this.root != null){
            this.root.preOrder();
        }else {
            System.out.println("HuffmanTree is Empty !");
        }
    }


    /**
     * 创建HuffmanTree 的方法
     * @param arr: 创建成哈夫曼树的数组
     * @return： 哈夫曼树的根结点
     */
    public void CreatHuffmanTree(int[] arr){
        // 1. 遍历arr,将 arr 的每一个元素构成一个Node,并放入ArrayList中
        List<Node> nodes = new ArrayList<Node>();
        for (int value: arr) {
            nodes.add(new Node(value));
        }
        // 当结点数大于2时重复操作
        while (nodes.size() > 1){
            // 2. 从小到大排序
            Collections.sort(nodes);
            // 3. 取出权值最小的结点和次小的结点，构建新的二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(leftNode.value + rightNode.value);       // 构建新的二叉树
            parent.left = leftNode;
            parent.right = rightNode;                                             // 将左右结点挂在新的父结点上
            // 4. 在ArrayList 中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 5. 将新生成的 parent 结点加入 nodes 集合中
            nodes.add(parent);
        }
        // 6. 更换哈夫曼树的根结点(此时Node集合中只有根节点)
         this.root = nodes.get(0);
    }
}

