package GuiguDataConstruct.huffmantree;

/** 哈夫曼树结点 （实现 Comparable d接口，让结点可以比较大小）
 * @title: Node
 * @Author Tan
 * @Date: 2021/12/18 10:49
 * @Version 1.0
 */
public class Node implements Comparable<Node>{
    int value;      // 结点权值
    Node left;
    Node right;

    public Node() {
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    // 接口的实现 (从小到大排序)
    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    // 前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }
}

