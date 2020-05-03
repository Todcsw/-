package com.csw.algorithm.prem;

import com.csw.graph.Graph;

import java.util.Arrays;

/**
 * @Auther: 行路
 * @Date: Created on 2020/5/2 22:54 星期六
 * @Description: com.csw.algorithm.prem
 * @version: 1.0 普里姆算法
 */
public class PrimAlgorithm {
    public static void main(String[] args) {

        //测试图是否创建成功
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertex = data.length;
        //将领接矩阵的关系,使用二维数组来表示
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},};
        //创建MGraph对象
        MGraph graph = new MGraph(vertex);
        //创建一个MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(graph,vertex,data,weight);
        //输出
        minTree.showGraph(graph);
        //测试普里姆算法
        minTree.prim(graph,0);
    }
}

//创建最小生成树->村庄的图

class MinTree {
    //创建图的领接矩阵

    /**
     * @param graph  图对象
     * @param vertex 图对应的顶点个数
     * @param data   图的各个顶点的值
     * @param weight 图的领接矩阵
     */
    public void createGraph(MGraph graph, int vertex, char[] data, int[][] weight) {
        int i, j;
        for (i = 0; i < vertex; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < vertex; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }


    /**
     * 显示图的领接矩阵
     *
     * @param graph
     */
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }



    /**
     * 编写prim算法,得到最小生成树
     * @param graph 图
     * @param v 表示从图的第几个顶点开始生成
     */
    public void prim(MGraph graph,int v){
        //visited[] 标记节点是否被访问过
        int visited[]=new int[graph.vertex];
        //visited[] 默认元素的值都是0,表示没有访问过
//        for(int i=0;i<graph.vertex;i++){
//            visited[i]=0;
//        } 默认都是0
        //把当前这个节点标记未以访问
        visited[v]=1;
        //h1,h2记录两个顶点的下标
        int h1=-1;
        int h2=-1;
        int minWeight=10000; //将minWeight初始化成一个大数,后面在遍历过程中,会被替换
        for(int k=1;k<graph.vertex;k++){
            //因为有graph.vertex顶点,普里姆算法结束后,有graph.vertex-1边

            //这个是确定每一次生成的子图,和那个节点的距离最近
            for(int i=0;i<graph.vertex;i++){ //i节点被访问过的节点
                for(int j=0;j<graph.vertex;j++){ //j的节点表示还没有访问过的节点
                    if(visited[i]==1&&visited[j]==0&&graph.weight[i][j]<minWeight){
                        //替换minWeight(寻找已经访问过的节点和未访问过的节点间的权值最小的边)
                        minWeight=graph.weight[i][j];
                        h1=i;
                        h2=j;
                    }
                }
            }
            //找到一条边是最小的
            System.out.println("边<"+graph.data[h1]+","+graph.data[h2]+">权值:"+minWeight);
            //将当前找到的节点标记为已经访问
            visited[h2]=1;
            //minWeight重新设置为最大值10000
            minWeight=10000;
        }
    }
}

class MGraph {
    int vertex; //表示图的节点个数
    char[] data;//存放节点数据
    int[][] weight;//存放边,就是领接矩阵

    public MGraph(int vertex) {
        this.vertex = vertex;
        data = new char[vertex];
        weight = new int[vertex][vertex];
    }
}
