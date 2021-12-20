package GuiguDataConstruct.graph;

import java.util.ArrayList;
import java.util.Arrays;

public class Grpph {
    // 储存顶点的集合
    public ArrayList<String> vertexList;
    // 储存图对应的邻接矩阵
    public int[][] edge;
    // 表示边的数目
    public int numofEdges;
    // 定义数组boolean[], 记录某个节点是否被访问过
    public boolean[] isVisited;

    public Grpph() {
    }

    public Grpph(int n){
        // 初始化邻接矩阵
        edge = new int[n][n];
        // 初始化顶点集合
        vertexList = new ArrayList<String>(n);
        // 初始化边的数目
        numofEdges = 0;
        // 初始化标记数组（判断节点是否被访问过）
        isVisited = new boolean[n];
    }

    // 深度优先遍历 （DFS）
    private void dFS(boolean[] isVisited,int i){
        // 访问该节点并输出
        System.out.print(getVertexByIndex(i) + " => ");
        // 将该节点设置为已经访问过
        isVisited[i] = true;
        // 获取第一个邻接结点
        int w = getNearVertex(i);
        // 该结点的邻接结点存在
        while (w != -1){
            // w没有被访问过
            if (!isVisited[w]){
                //  => 递归查找下一个
                dFS(isVisited,w);
            }
            // w已经被访问过 => 查找该结点的第二个邻接结点
            w = getNextNeartVertex(i,w);
        }
    }

    // 重载DFS,遍历所有的节点进行DFS
    public void dFS(){
        // 遍历所有的结点，进行DFS => 回溯 (该结点的邻接结点不存在)
        for (int i = 0; i < getNumofVertexs(); i++) {
            // 该结点没有被访问过
            if (!isVisited[i]){
                dFS(isVisited,i);
            }
        }
    }

    // 得到第一个邻接节点的下标w(没有的话返回 -1)
    public int getNearVertex(int index){
        for (int i = 0; i < vertexList.size(); i++) {
            if (edge[index][i] > 0){
                // 下一个结点存在且相互连接
                return i;
            }
        }
        return -1;
    }

    /**
     * 得到第二个邻接结点的下标w(没有的话返回 -1)
     * @param v1: 初始访问节点
     * @param v2：初始访问节点的第一个邻接节点
     * @return： 初始访问节点的第二个邻接节点
     */
    public int getNextNeartVertex(int v1,int v2){
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edge[v1][i] > 0){
                return i;
            }
        }
        return -1;
    }

    // 插入节点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 添加边
     * @param v1: 对应的顶点在集合中的下标
     * @param v2：对应的顶点在集合中的下标
     * @param weight: 表示两个点是否有连接（0表示没有连接 / 1表示有连接）
     */
    public void insertEdge(int v1, int v2, int weight){
        edge[v1][v2] = weight;
        edge[v2][v1] = weight;
        numofEdges++;
    }

    // 得到顶点的数目
    public int getNumofVertexs(){
        return vertexList.size();
    }

    // 得到边的数目
    public int getNumofEdges(){
        return numofEdges;
    }

    // 返回下标所对应的节点（输入下标 => 返回节点）
    public String getVertexByIndex(int i){
        return vertexList.get(i);
    }

    // 返回v1 和 v2 （下标所对应的节点）之间的权值
    public int getWeight(int v1, int v2){
        return edge[v1][v2];
    }

    // 显示邻接矩阵
    public void showGraph(){
        for (int[] link : edge){
            System.out.println(Arrays.toString(link));
        }
    }
}
