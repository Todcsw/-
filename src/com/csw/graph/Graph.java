package com.csw.graph;

import java.awt.font.NumericShaper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * a
 *
 * @Auther: 行路
 * @Date: Created on 2020/5/1 21:40 星期五
 * @Description: com.csw.graph 无向图(图的深度优先遍历)
 * @version: 1.0
 */
public class Graph {

    /*存储顶点集合
     */
    private ArrayList<String> vertexList;
    //存储图对应的领接矩阵
    private int[][] edges;
    //表示边的数目
    private int numOfEdges;

    //定义一个数组boolean[],记录某个结点是否被访问过
    private boolean[] isVisited;


    public static void main(String[] args) {

        //测试一把
        //结点的个数
        int n = 5;
        String[] Vertex = {"A", "B", "C", "D", "E"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环添加结点
        for (String vertex : Vertex) {
            graph.insertVertex(vertex);
        }
        //添加边
        //A-B,A-C,b-C, b-D,b-e
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        //显示
        graph.showGraph();

        //测试dfs
        System.out.println("深度遍历");
        graph.dfs();
        System.out.println("");
        System.out.println("广度优先遍历");
        graph.bfs();
    }

    //构造器
    public Graph(int n) {
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
      //  isVisited = new boolean[n];
    }


    /**
     * 得到第一个领接结点的下标 w
     *
     * @param index
     * @return 如果存在就返回对应的下标, 否则返回对应-1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }


    //返回前一个领接结点的下标来获取下一个领接结点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }


    /**
     * 深度优先遍历算法
     *
     * @param isVisited
     * @param i         第一次就是0
     */
    public void dfs(boolean[] isVisited, int i) {
        //首先访问该结点输出
        System.out.print(getValueByIndex(i) + "->");
        //将结点设置为已经访问过
        isVisited[i] = true;

        //查找该结点v的第一个领接结点
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如果w结点已经被访问过
            w = getNextNeighbor(i, w);
        }
    }

    //对dfs进行一个重载,遍历我们所有的结点,并进行dfs

    public void dfs() {
        isVisited=new boolean[vertexList.size()];
        //遍历所有的结点进行dfs[回溯]
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /**
     * 对一个结点广度优先遍历的方法
     */
    private void bfs(boolean[] isVisited, int i) {
        int u;//表示队列的头结点
        int w; //领接结点w
        //队列,记录结点访问的顺序
        LinkedList queue = new LinkedList();
        //访问结点,输出结点信息
        System.out.print(getValueByIndex(i) + "=>");
        //标记以被访问
        isVisited[i] = true;
        //将结点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()) {
            //取出队列的头结点下标
            u = (Integer) queue.removeFirst();
            //得到第一个领接点的小标w
            w = getFirstNeighbor(u);
            while (w != -1) {
                //是否访问过
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "=>");
                    //标记已经访问
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                //以u为前驱点,找w后面的下一个领结点
                w = getNextNeighbor(u, w); //体现出广度优先
            }
        }
    }

    /**
     * 遍历所有的节点,都进行广度优先搜索
     */
    public void bfs() {
        isVisited=new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    //图中常用方法

    /**
     * @return 返回结点个数
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 得到边的数目
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回结点i(下标)对应的数据0->"A" 1 "B" 2->"C"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

    //插入结点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }


    /**
     * 添加边
     *
     * @param v1     表示点的下标即使第几个顶点 "A"-"B"-"C"-"D"-"E"
     * @param v2     表示第二个顶点对应的下标
     * @param weight 表示
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }
}
