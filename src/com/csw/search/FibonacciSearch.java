package com.csw.search;

import java.util.Arrays;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/21 23:19 星期二
 * @Description: com.csw.search 斐波那契查找算法
 * @version: 1.0
 */
public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println(fibSearch(arr,1234));
    }

    //mid=low+F(k-1)+1,后面需要使用斐波那契数列,因此我们需要先获取到一个斐波那契数列
    //非递归的方式得到斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 使用非递归
     * 编写斐波那契查找算法
     *
     * @param a   数组
     * @param key 我们需要查找的关键码
     * @return 返回对应的小标, 如果没有-1
     */
    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;  //表示斐波那契分割数值对应下标
        int mid = 0;  //存放mid值
        int[] f = fib();  //获取斐波那契数列
        //获取到斐波那契分割值的小标
        while (high > f[k] - 1) {
            k++;
        }
        //因为f[k]值大于a的长度,因此许Arrays类,构造一个新的数组,并指向a[]
        int[] temp = Arrays.copyOf(a, f[k]);
        //实际上需要使用a数组最后的数填充temp
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }
        //使用while循环来循环处理,找到我们的数key
        while (low <= high) {
            //只要这个添加满足,就可以一直找
            mid = low + f[k - 1] - 1;
            if(key<temp[mid]){
                //说明我们应该向数组的前面查找
                high=mid-1;
                //1.全部元素=前面的元素+后边的元素
                //2.f[k]=f[k-1]+h[k-2]
                //因为前面有f[k-1]个元素,所以可以继续拆分f[k-1]=f[k-2]+f[k-3]
                //即f[k-1]的前面继续查找k--;
                //即下次循环mid=f[k-1-1]-1;
                k--;
            }else if(key>temp[mid]){ //说明向数组 的后面查找
                low=mid+1;
                //为什么是k-=2,1.全部元素=前面的元素+后边的元素
                //因为后面我们有f[k-2]个元素所以我们可以继续拆分
                //mid=f[k-1-2]-1
                k-=2;
            }else{
                //需要确定返回的是那个下标
                if(mid<=high){
                    return mid;
                }else{
                    return high;
                }
            }
        }
        return -1;
    }
}
