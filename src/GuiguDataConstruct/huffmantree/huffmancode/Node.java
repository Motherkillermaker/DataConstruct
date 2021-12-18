package GuiguDataConstruct.huffmantree.huffmancode;

/** 哈夫曼编码中的结点
 * @title: Node
 * @Author Tan
 * @Date: 2021/12/18 13:15
 * @Version 1.0
 */
public class Node implements Comparable<Node> {
    public Byte data;           // 存放数据本身，比如‘a’ => 97
    public int weight;           //  结点权值（频数）
    public Node left;
    public Node right;

    public Node(Byte data, int value) {
        this.data = data;
        this.weight = value;
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

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    // 实现比较接口，结点从小到大排序
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}
