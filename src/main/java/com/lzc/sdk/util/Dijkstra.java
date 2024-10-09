package com.lzc.sdk.util;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA
 * User: heroC
 * Date: 2020/6/13
 * Time: 12:44
 * Description:迪杰斯特拉算法
 * 求某个顶点到达各个顶点的最短路径?
 * 1，创建一个图，构建顶点数据和邻接矩阵
 * 2，构建一个访问类，有3个主要的数组，分别是记录已被访问的数组、记录前驱节点的数组、已走的记录路径值
 * 3，创建一个更新路径的方法，即选取一个最小的当前点到下一个没有访问的点的距离
 * 4，创建一个获取没有被访问过的最小路径的新的节点为起点的方法
 * Version: V1.0
 */
public class Dijkstra {
    // NIF常量表示没有该条路径
    private static final int NIF = 65535;

    public static void main(String[] args) {
        char vertexData[] = {'A','B','C','D','E','F','G'};
        int weight[][] = {
                {NIF, 5, 7, NIF, NIF, NIF, 2},
                {5, NIF, NIF, 9, NIF, NIF, 3},
                {7, NIF, NIF, NIF, 8, NIF, NIF},
                {NIF, NIF, NIF, 4, 5, NIF, 6},
                {NIF, NIF, 8, NIF, NIF, 5, 4},
                {NIF, NIF, NIF, 4, 5, NIF, 6},
                {2, 3, NIF, NIF, 4, 6, NIF}
        };
        // 迪杰斯特拉算法
        dijkstraAlgorithm(vertexData,weight,6);
    }

    /**
     * 迪杰斯特拉算法
     * @param vertexData 顶点数据
     * @param weight 邻接矩阵，权的关系
     * @param start 起始顶点的索引
     */
    public static void dijkstraAlgorithm(char[] vertexData, int[][] weight, int start){
        // 构建一个图
        DijGraph dijGraph = new DijGraph(vertexData, weight);
        // 将图传递给Visited类，做处理
        Visited visited = new Visited(start, dijGraph);
        // 更新以索引为6(G)作为起点的与索引为6这个起点(G)连通的节点的路径距离
        visited.updateDis(start);
        // 由于起点已经排除，所以只需要判断剩下的节点即可
        for (int i = 1; i < dijGraph.vertexData.length; i++) {
            // 获取新的最短路径的节点为新的起点(因为一个节点走过了，那么下一个节点就成为新的起点)
            int index = visited.getNewMinRoutVertex();
            // 更新与新的起点连通的节点的距离，选择最小的作为连通点的距离
            visited.updateDis(index);
        }

        // 展示从起始点G到每个节点的最短路径
        visited.showVertexDis();
    }
}


class Visited{
    // 用于记录已访问的点
    private int[] isVisitedVertex;
    // 用于记录起始点到达该点的距离
    private int[] vertexDis;
    // 用于记录该点的前驱节点，记录前驱节点的作用就是可以遍历得到行走的轨迹
    private int[] preVertex;
    // NIF表示两点不可连通
    private static final int NIF = 65535;
    private DijGraph graph;
    // 记录起始顶点索引
    private int startVertexIndex;

    /**
     * 构造函数，对3个数组的初始化，以及接收图
     * @param index 起始点的索引
     * @param graph 图
     */
    public Visited(int index, DijGraph graph) {
        int len = graph.vertexData.length;

        this.isVisitedVertex = new int[len];
        isVisitedVertex[index] = 1; // 起始点设置为已访问，1表示已访问，0表示未被访问

        this.vertexDis = new int[len];
        Arrays.fill(vertexDis,NIF); // 初始距离都是NIF
        vertexDis[index] = 0; // 起始点的距离，也就是G到G的距离就是0

        this.preVertex = new int[len];
        preVertex[index] = index; // 设置起始点的前驱节点的索引，就是他自己

        this.startVertexIndex = index;

        this.graph = graph;
    }

    /**
     * 获得index索引节点的路径
     * @param index
     * @return
     */
    public int getDis(int index){
        return vertexDis[index];
    }

    /**
     * 判断该索引的节点是否已被访问
     * @param index
     * @return
     */
    public boolean getIsVisitedVertex(int index){
        return isVisitedVertex[index]==1;
    }

    /**
     * 更新每个节点到起始节点的路径长度
     * @param index 当前起点
     */
    public void updateDis(int index){
        for (int j = 0; j < graph.weight[index].length; j++) {
            // 类似广度优先搜索遍历，获取index这个节点与其连通的节点之间的距离加上index这个节点已经走过的距离
            int len = getDis(index) + graph.weight[index][j];
            // 如果len小于连通的这个节点已经存在的距离，那么说明走到j这个节点的距离最小的是len这个距离
            // 并且j这个节点没有被访问过的话，就应该将这个节点的距离赋值为最小的len
            // j这个节点的前驱节点就是index这个节点了
            if (!getIsVisitedVertex(j) && len < getDis(j)){
                vertexDis[j] = len;
                preVertex[j] = index;
            }
        }
    }

    /**
     * 获取没有访问过的节点中，已走的最短距离的那个节点的索引，
     * 将其设置为已访问，并返回这个节点，作为新的起点
     * @return
     */
    public int getNewMinRoutVertex(){
        int min = NIF;
        int index = 0;
        for (int i = 0; i < isVisitedVertex.length; i++) {
            if (isVisitedVertex[i]==0 &&  vertexDis[i] < min){
                min = vertexDis[i];
                index = i;
            }
        }
        isVisitedVertex[index] = 1;
        return index;
    }

    // 遍历记录到达每个节点最短路径数组，以及记录的前驱节点数组
    public void showVertexDis(){
        for (int i = 0; i < vertexDis.length; i++) {
            if (vertexDis[i]!=NIF){
                System.out.println(graph.vertexData[i] + " : " +vertexDis[i]);
            }else {
                System.out.println("NIF");
            }
        }

        System.out.println("具体行走路线：");
        Set<Character> set = new LinkedHashSet<>();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < preVertex.length; i++) {
            int temp = i;
            set.add(graph.vertexData[i]);
            do{
                temp = preVertex[temp];
                set.add(graph.vertexData[temp]);
            }while (preVertex[temp]!=startVertexIndex);
            set.add(graph.vertexData[preVertex[temp]]);
            for (Character character : set) {
                stack.add(character);
            }
            while (!stack.isEmpty()){
                System.out.print(stack.pop()+" ");
            }
            set.clear();
            System.out.println(": "+vertexDis[i]);
        }
    }
}

class DijGraph{
    char[] vertexData; // 存储图中顶点的数据
    int[][] weight; // 邻接矩阵，即权路径

    public DijGraph(char[] vertexData, int[][] weight) {
        this.vertexData = new char[vertexData.length];
        for (int i = 0; i < vertexData.length; i++) {
            this.vertexData[i] = vertexData[i];
        }
        this.weight = new int[vertexData.length][vertexData.length];
        for (int i = 0; i < weight.length; i++) {
            for (int j = 0; j < weight[i].length; j++) {
                this.weight[i][j] = weight[i][j];
            }
        }
    }
}





