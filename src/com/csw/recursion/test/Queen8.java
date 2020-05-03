package com.csw.recursion.test;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/19 11:06 星期日
 * @Description: com.csw.recursion.test 自己写八皇后练习,加深影响
 * @version: 1.0
 */
public class Queen8 {
    int max = 8; //定义八个皇后
    int[] arr = new int[max];
    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {

    }




    /**
     * 查看当我们放置第n个皇后,就去检测该皇后是否和前面已经摆放的皇后冲突
     * @param n 表示第n个皇后
     * @return
     */
    private boolean judge(int n){
        judgeCount++;
        for(int i=0;i<n;i++){
            if(arr[i]==arr[n]|| Math.abs(n-i)==Math.abs(arr[n]-arr[i])){
                return false;
            }
        }
        return true;
    }

    /**
     * 用于打印路线
     * @param arr
     */
    private void print( int arr[]){
        count++;
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println("");
    }
}
