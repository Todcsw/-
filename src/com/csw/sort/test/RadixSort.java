package com.csw.sort.test;

import java.util.Arrays;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/21 13:13 星期二
 * @Description: com.csw.sort.test 基数排序
 * @version: 1.0
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
    }

    public static void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();
        System.out.println(maxLength);
        //定义10个桶
        int[][] bucket = new int[10][arr.length];
        //记录每个桶中的个数
        int[] bucketElementCounts = new int[10];
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {

            for (int j = 0; j < arr.length; j++) {
                int digitOfElement = arr[j] / n % 10;
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            int index = 0;

            for (int k = 0; k < bucketElementCounts.length; k++) {
                if (bucketElementCounts[k] != 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
                bucketElementCounts[k] = 0;
            }
        System.out.println("第"+(i+1)+"轮"+ Arrays.toString(arr));
        }
    }


}
