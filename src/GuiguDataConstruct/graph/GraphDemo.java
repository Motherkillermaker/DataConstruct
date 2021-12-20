package GuiguDataConstruct.graph;

public class GraphDemo {
    public static void main(String[] args) {
        int n = 5;      // 节点个数
        String[] vertexs = {"A","B","C","D","E"};   // 顶点
        // 创建图
        Grpph grpph = new Grpph(n);
        // 循环添加顶点
        for (String vertex : vertexs){
            grpph.insertVertex(vertex);
        }
        // 添加边(AB AC BC BD BE)
        grpph.insertEdge(0,1,1);
        grpph.insertEdge(0,2,1);
        grpph.insertEdge(1,2,1);
        grpph.insertEdge(1,3,1);
        grpph.insertEdge(1,4,1);
        // 显示邻接矩阵
        grpph.showGraph();
        // 测试DFS
        System.out.println("深度优先遍历结果：");
        grpph.dFS();

    }
}
