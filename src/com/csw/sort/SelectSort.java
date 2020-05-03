package com.csw.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/19 14:46 星期日
 * @Description: com.csw.sort 选择排序
 * @version: 1.0
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1};
        selectSort(arr);
        testTime(); //80000个数据排序测试时间
    }

    /**
     * 使用逐步指导的方式来,讲解选择排序
     * 第一轮
     * 原始初始值 101,34,119,1
     * 第一轮排序 :1,34,119,101
     * 算法,先简单后复杂,就是可以把一个复杂的算法，拆分成简单的问题,逐步解决
     * <p>
     * 自己的理解：将每一次排序的第一个元素假定为最小值,定义俩变量一个记录值,一个记录下标用于交换
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {
        System.out.println("选择排序~~~~~");
        //在推导过程中
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[minIndex];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            //当for循环结束时,将最小值放在arr[0]，即交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
            // System.out.println("第"+(i+1)+"轮后~~");
            // System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 测试时间
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

        selectSort(arr);

        Date date2 = new Date();
        //   SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2Str = simpleDateFormatter.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
    }
}
