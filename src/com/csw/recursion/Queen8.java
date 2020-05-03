package com.csw.recursion;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/18 13:31 星期六
 * @Description: com.csw.recursion 八皇后问题
 * @version: 1.0
 */
public class Queen8 {

    //定义一个max表示有多少皇后
    int max=8;
    //定义数组array 保存皇后放置的位置的结果
    //比如arr={0,4,7,5,2,6,1,3}
    int[] array=new int[max];
    static int count=0;
    static int judgeCount=0;
    public static void main(String[] args) {
        //测试8皇后是否正确
        Queen8 queen8=new Queen8();
        queen8.check(0);
        System.out.println("一共有"+count+"种解法");
        System.out.println("共判断了多少次"+judgeCount);

    }

    //编写一个方法,放置第n个皇后
    //特别注意：check是每一次递归,进入到check中都有for(int i=0;i<max;i++)因此会有回溯
    private void check(int n){
        if(n==max){ //n=8 ,其实8个皇后已然放好了
            print();
            return;
        }
        //依次放入皇后,并判断是否冲突
        for (int i=0;i<max;i++){
            //先把当前这个皇后n,放到该行的1列
            array[n]=i;
            //判断当放置第n个皇后到i列时,是否冲突
            if(judge(n)){
                //不冲突
                //接着放n+1个皇后,即开始递归
                check(n+1); //
            }
            //如果冲突,就继续执行array[n]=i;即将第n个皇后放置在本行的后移一个位置
        }
    }

    /**
     * 查看当我们放置第n个皇后,就去检测该皇后是否和前面已经摆放的皇后冲突
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n){
        judgeCount++;
        for(int i=0;i<n;i++){
            //1.array[i]==array[n]表示判断第n个皇后是否和前面n-1个皇后在同一列
            //2.表示判断Math.abs(n-i)==Math.abs(array[n]-array[i])第n个皇后和第i个皇后是否在同一斜线
            //n=1 放在第二列 n=1 array[1]=1
            //Math.abs(1-0)==1 Math.abs(1)
            //判断是否在同一行,没有必要,n每次都在递增
            if(array[i]==array[n]|| Math.abs(n-i)==Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    /**
     *写一个方法,可以将皇后摆放的位置打印出来
     */
    private void print(){
        count++;
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+" ");
        }
        System.out.println("");
    }
}
