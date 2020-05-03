package com.csw.algorithm.dynamic;

import java.util.Arrays;

/**
 * @Auther: 行路
 * @Date: Created on 2020/5/2 15:35 星期六
 * @Description: com.csw.algorithm.dynamic
 * @version: 1.0
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3}; //物品的重量
        int[] val = {1500, 3000, 2000}; //物品的价值
        int m = 4; //背包的容量
        int n = val.length; //物品的个数
        //创建二位数组
        //v[i][j]表示在前i个物品中能够装入容量为j的背包最大价值
        int[][] v = new int[n + 1][m + 1];

        //为了记录放入商品的情况,我们定义一个二维数组
        int[][] path = new int[n + 1][m + 1];

        //初始化第一行和第一列,在本程序中默认就是0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0; //将第一列设置为0
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0; //将第一行设置为0
        }

        //根据公式进行动态规划
        for (int i = 1; i < v.length; i++) {
            //不处理第一行
            for (int j = 1; j < v[0].length; j++) {
                //不处理第一列
                if (w[i - 1] > j) { //因为程序从1开始
                    v[i][j] = v[i - 1][j];
                } else {
                    //因为i是从1开始的,所以公式需要调整
                    //v[i][j]=Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
                    //为了记录商品存放到背包的情况,我们不能直接使用上面的公式,需要使用if-else来体现公式
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        //把当前的情况记录到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        //输出一下v
//        for (int i = 0; i < v.length; i++) {
//            for (int j = 0; j < v[i].length; j++) {
//                System.out.print(v[i][j] + " ");
//            }
//            System.out.println("");
//        }

        //输出一下v
        //这样输出不对
//        for (int i = 0; i < v.length; i++) {
//            for (int j = 0; j < v[i].length; j++) {
//                // System.out.print(path[i][j]+" ");
//                if (path[i][j] == 1) {
//                    System.out.printf("第%d个商品放入到背包\n", i);
//                }
//            }
//        }

        for(int[] a:v){
            System.out.println(Arrays.toString(a));
        }

        for(int[] a:path){
            System.out.println(Arrays.toString(a));
        }
        int i=path.length-1;
        int j=path[0].length-1;
        while (i>0&&j>0){
            if(path[i][j]==1){
                System.out.printf("第%d个商品放入到背包\n",i);
                j-=w[i-1];
            }
            i--;
        }

    }
}
