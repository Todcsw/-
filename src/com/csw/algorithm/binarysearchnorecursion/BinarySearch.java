package com.csw.algorithm.binarysearchnorecursion;

/**
 * @Auther: 行路
 * @Date: Created on 2020/5/2 13:36 星期六
 * @Description: com.csw.algorithm.binarysearchnorecursion
 * @version: 1.0 二分查找非递归算法
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr={1,3,8,10,11,67,100};
        int i = binarySearch(arr,11);
        System.out.println("下标为:"+i);

    }


    /**
     * 二分查找的非递归实现
     * @param arr 待查找的数组 升序排列
     * @param target 需要查找的数
     * @return 返回对应的下标，-1表示没有找到
     */
    public static int binarySearch(int[] arr,int target){
        int left=0;
        int right=arr.length-1;
        while (left<=right){
            //说明继续查找
            int mid=(left+right)/2;
            if(arr[mid]==target){
                return mid;
            }else if(arr[mid]>target){
                right=mid-1;
            }else{
                left=mid+1;
            }
        }
        return -1;
    }
}
