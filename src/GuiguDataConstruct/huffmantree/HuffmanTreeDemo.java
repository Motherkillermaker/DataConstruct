package GuiguDataConstruct.huffmantree;

/**
 * @title: HuffmanTreeDemo
 * @Author Tan
 * @Date: 2021/12/18 12:29
 * @Version 1.0
 */
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] arr = new int[]{13,7,8,3,29,6,1};
        HuffmanTree huffmanTree =  new HuffmanTree();
        // 构建哈夫曼树
        huffmanTree.CreatHuffmanTree(arr);
        // 对哈夫曼树进行前序遍历
        huffmanTree.preOrder();
    }
}

