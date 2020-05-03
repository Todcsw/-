package com.csw.search;

import java.util.ArrayList;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/21 16:09 星期二
 * @Description: com.csw.search 二分查找
 * @version: 1.0
 */
public class BinarySearch {
    //二分查找的前提是该数组必须是有序的
    public static void main(String[] args) {
        int[] arr = {1, 8,10, 89, 1000, 1000,1000, 1234};
        int resIndex = binarySearch(arr, 0, arr.length - 1, 1000);
        System.out.println("查找次数为:"+count+",查找的下标为:"+resIndex);
        ArrayList<Integer> list = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println(list);

    }
    //二分查找

    /**
     * @param arr     数组
     * @param left    左边的索引
     * @param right   右边的索引
     * @param findVal 要查找的值
     * @return 如果找到返回下标, 如果没有找到, 就返回-1
     */

    public static int count=0;
    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        //当left>right时,说明递归整个数组,但是没有找到
        ++count;
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            return binarySearch(arr, mid + 1, right, findVal); //向右递归
        } else if (findVal < midVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }


    /**
     * 课后思考题:当有多个相同的数值时,如何将所有的数组都查到,比如很多个1000
     * 思路分析
     * 1.找到mid值时,不马上返回
     * 2.向mid索引值左边扫描,将所有满足1000的元素的下标,加入到集合ArrayList
     * 3.向mid索引值的右边扫描,将所有满足1000,的元素下标,加入到ArrayList
     *
     * @param arr
     * @param left
     * @param right
     * @param findVal
     * @return
     */
    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal) {
        //当left>right时,说明递归整个数组,但是没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            return binarySearch2(arr, mid + 1, right, findVal); //向右递归
        } else if (findVal < midVal) {
            return binarySearch2(arr, left, mid - 1, findVal);
        } else {
            //
            ArrayList<Integer> resIndexList = new ArrayList<>();
            //左边
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal){
                    break;
                }
                resIndexList.add(temp);
                temp-=1;  //temp左移
            }
            resIndexList.add(mid); //中间
            //右边
            temp=mid+1;
            while (true) {
                if (temp >arr.length-1 || arr[temp] != findVal){
                    break;
                }
                resIndexList.add(temp);
                temp+=1;  //temp左移
            }
            return resIndexList;
        }
    }

}
