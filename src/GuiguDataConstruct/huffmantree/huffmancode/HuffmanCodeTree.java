package GuiguDataConstruct.huffmantree.huffmancode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/** 构建哈夫曼编码树
 * @title: HuffmanCodeTree
 * @Author Tan
 * @Date: 2021/12/18 13:29
 * @Version 1.0
 */
public class HuffmanCodeTree {
    public Node root;

    // 哈夫曼编码表（字节数，哈夫曼编码）
    public Map<Byte,String> huffmanCode = new HashMap<Byte,String>();

    public HuffmanCodeTree() {
    }

    public HuffmanCodeTree(Node root) {
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
     * 传入Byte数组，创建对应的哈夫曼编码树
     * @param bytes： 字符串对应的byte数组 => ASCII码表对应
     */
    public void createHuffmanCodeTree(byte[] bytes){
        // 1. 创建存放结点的集合
        ArrayList<Node> nodes = new ArrayList<Node>();
        // 2. 遍历bytes,统计每一个byte出现的次数 (类比： 统计字符串中字符的个数)=> Map 实现
        Map<Byte,Integer> counts = new HashMap<>();
        for (byte b : bytes){
            //获取当前byte的次数
            Integer count = counts.get(b);
            if (count == null){
                // Map 中没有该数据 => 第一次出现
                counts.put(b,1);
            }else {
                // 已经存在，将次数加1
                counts.put(b,count+1);
            }
        }
        // 3. 把每一个键值对转换成 Node 对象，并加入集合 nodes 中 (此时便完成了哈夫曼树的输入 => 一个集合)
        for (Map.Entry<Byte,Integer> entry : counts.entrySet()){
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }

        // 4. 利用步骤三得到的集合构建哈夫曼树
        // 当结点数大于 2 时重复操作
        while (nodes.size() > 1){
            // 从小到大排序
            Collections.sort(nodes);
            // 取出权值最小和次小的结点，构建新的二叉树(新的二叉树中只有权值weight，没有data => data均在叶子节点上)
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null,leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            // 在ArrayList 中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 将新生成的 parent 结点加入 nodes 集合中
            nodes.add(parent);
        }
        // 6. 更换哈夫曼树的根结点(循环结束时Node集合中只有根节点)
        this.root = nodes.get(0);
    }

    /**
     * 生成哈夫曼编码
     * 1. 将哈夫曼编码表存放在 Map<Byte,String> 形式中
     * 2. 定义一个StringBuilder,存储某个叶子结点的路径
     * @param Node: 根结点
     * @param Code： 路径编码 => 初始为空（左子节点为0，右子节点为1）
     */
    public void getHuffmanCode(Node Node,String Code,StringBuilder PathCode){
        // 叶子节点的拼接路径
        StringBuilder pathcode = new StringBuilder(PathCode);
        // 将路径编码写入路径中
        pathcode.append(Code);
        if (Node != null){
            // 判断当前节点是叶子节点还是非叶子结点
            if (Node.data == null){
                // 非叶子结点 => 进行递归
                // 左递归
                getHuffmanCode(Node.left,"0",pathcode);
                // 右递归
                getHuffmanCode(Node.right,"1",pathcode);
            }else {
                // 叶子结点 => 将结点的拼接路径加入 huffmanCode 中
                huffmanCode.put(Node.data,pathcode.toString());
            }
        }
    }


}

