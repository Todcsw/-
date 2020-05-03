package com.csw.recursion;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/17 22:41 星期五
 * @Description: com.csw.recursion
 * @version: 1.0
 */
public class RecursionTest {
    public static void main(String[] args) {
        //通过打印问题,回顾递归调用机制
        test(4);


        int factorial = factorial(2);
        System.out.println(factorial);
    }

    //打印问题
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.println("n=" + n);
    }

    //阶乘问题
    public static int factorial(int n){
        if(n==1){
            return 1;
        }else{
            return factorial(n-1)*n; //factorial(2)*3
        }
    }
}
