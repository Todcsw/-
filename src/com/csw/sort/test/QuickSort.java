package com.csw.sort.test;

import java.util.Arrays;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/20 16:30 星期一
 * @Description: com.csw.sort.test
 * @version: 1.0
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr={12,32,12,43,23,1,124,34,-1,-3,30,32,-943};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr,int low,int high){
        if(low<high){
            int i = partition(arr, low, high);
            quickSort(arr,low,i-1);
            quickSort(arr,i+1,high);
        }
    }

    public static int partition(int[] arr,int left,int right){
        int temp=arr[left];
        int i=left;
        int j=right;
        while (i!=j){
            while (i<j&&temp<arr[j]){
                j--;
            }
            if(i<j){
                arr[i]=arr[j];
                i++;
            }
            while (i<j&&temp>arr[i]){
                i++;
            }
            if(i<j){
                arr[j]=arr[i];
                j--;
            }
        }
        arr[i]=temp;
        return i;
    }

}
