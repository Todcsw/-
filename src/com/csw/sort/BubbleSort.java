package com.csw.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/19 13:55 星期日
 * @Description: com.csw.sort 冒泡排序 时间复杂度(n^2)
 * @version: 1.0
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, 20};
        sort1(arr);
        sort2(arr);
        testTime();//80000个数据排序测试时间

    }

    /**
     * 冒泡排序
     *
     * @param arr
     */
    public static void sort1(int arr[]) {
        System.out.println("初代冒泡排序--------");
        int temp = 0; //临时变量
        //将最大的数排到最后
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
            // System.out.println("第"+(i+1)+"趟排序");
            // System.out.println(Arrays.toString(arr));
        }
    }


    /**
     * 优化冒泡排序
     *
     * @param arr
     */
    public static void sort2(int arr[]) {
        System.out.println("优化冒泡排序--------");
        int temp = 0; //临时变量
        boolean flag = false;  //表示变量,表示是否进行交换
        //将最大的数排到最后
        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
            //   System.out.println("第"+(i+1)+"趟排序");
            //  System.out.println(Arrays.toString(arr));
            if (!flag) { //在一趟排序中,一次交换也没有发生过
                break;
            } else {
                flag = false; //重置false ,进行下次判断
            }
        }
    }

    /**
     * 80000个数据排序测试时间
     */
    public static void testTime() {

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormatter.format(date1);
        System.out.println("排序前的时间是=" + date1Str);

        sort1(arr);

        Date date2 = new Date();
        //   SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2Str = simpleDateFormatter.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
    }
}
