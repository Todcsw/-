package com.csw.search;

import java.util.Arrays;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/21 16:47 星期二
 * @Description: com.csw.search 插值查找
 * @version: 1.0
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr=new int[100];
        for(int i=0;i<100;i++){
            arr[i]=i+1;
        }
        int i = insertValueSearch(arr, 0, arr.length - 1, 33);
        System.out.println("查找次数:"+count+"，查找到的下标为:"+i);
    }

    /**
     * 插值查找算法
     * 要求数组是有序的
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return //如果找到,就返回对应的值,如果没有找到,返回-1
     */
    public static int count=0;
    public static int insertValueSearch(int[] arr,int left,int right,int findVal){
        ++count;
        //注意这两个条件必须有,否则会得到mid可能越界
        if(left>right||findVal<arr[0]||findVal>arr[arr.length-1]){
            return -1;
        }
        //求出mid 自适应的值
        int mid=left+(right-left)*(findVal-arr[left])/(arr[right]-arr[left]);
        int midVal=arr[mid];
        if(findVal>midVal){
            return insertValueSearch(arr,mid+1,right,findVal);                    //说明向右递归查找
        }else if(findVal<midVal){
            return insertValueSearch(arr,left,mid-1,findVal);
        }else{
            return mid;
        }
    }
}
