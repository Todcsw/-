package com.csw.algorithm.dac;

/**
 * @Auther: 行路
 * @Date: Created on 2020/5/2 13:53 星期六
 * @Description: com.csw.algorithm.dac 分治算法(汉诺塔)
 * @version: 1.0
 */
public class HanoiTower {
    public static void main(String[] args) {
        hanoiTower(64,'A','B','C');
    }

    //汉诺塔的移动方法
    //使用分治算法
    public static void hanoiTower(int num,char a,char b,char c){
        //如果只有一个盘
        if(num==1){
            System.out.println("第1个盘从"+a+"->"+c);
        }else{
             //如果我们只有n>=2 情况,我们总是可以看作是两个盘1，最下边的一个盘2,上面的所有盘
            //1.先把最上面的所有盘A-B,移动过程会使用到c
            hanoiTower(num-1,a,c,b);
            //2.把下面的盘a-c
            System.out.println("第"+num+"个盘从"+a+"->"+c);
            //3.把B塔所有盘从B-C,移动过程中使用a塔
            hanoiTower(num-1,b,a,c);
        }
    }
}
