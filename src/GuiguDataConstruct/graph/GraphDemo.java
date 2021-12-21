package GuiguDataConstruct.graph;

public class GraphDemo {
    public static void main(String[] args) {
        int n = 8;      // 节点个数
//        String[] vertexs = {"A","B","C","D","E"};   // 顶点
        String[] vertexs = {"1","2","3","4","5","6","7","8"};   // 顶点
        // 创建图
        Grpph grpph = new Grpph(n);
        // 循环添加顶点
        for (String vertex : vertexs){
            grpph.insertVertex(vertex);
        }
        // 添加边(AB AC BC BD BE)
//        grpph.insertEdge(0,1,1);
//        grpph.insertEdge(0,2,1);
//        grpph.insertEdge(1,2,1);
//        grpph.insertEdge(1,3,1);
//        grpph.insertEdge(1,4,1);

        grpph.insertEdge(0,1,1);
        grpph.insertEdge(0,2,1);
        grpph.insertEdge(1,3,1);
        grpph.insertEdge(1,4,1);
        grpph.insertEdge(3,7,1);
        grpph.insertEdge(4,7,1);
        grpph.insertEdge(2,5,1);
        grpph.insertEdge(2,6,1);
        grpph.insertEdge(5,6,1);
        // 显示邻接矩阵
        grpph.showGraph();
        // 测试 DFS
        System.out.println("深度优先遍历结果：");
        grpph.dFS();
        System.out.println();
        // 测试 BFS
        System.out.println("广度优先遍历结果：");
        grpph.bFS();

    }
}
