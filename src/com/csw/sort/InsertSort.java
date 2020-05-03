package com.csw.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/19 15:16 星期日
 * @Description: com.csw.sort 插入排序
 * @version: 1.0
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1, -1, 89};
        System.out.println("老师的方法");
        insertSort(arr);
        System.out.println("我的方法");
        int[] arr1 = {101, 34, 119, 1, -1, 89};
        insertSort1(arr1);

        testTime();//80000个数据排序测试时间
    }

    //插入排序
    public static void insertSort(int[] arr) {
        System.out.println("插入排序~~~~");
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //当退出while循环时,说明插入的位置找到,insertIndex-1
            arr[insertIndex + 1] = insertVal;
            // System.out.println("第"+i+"轮");
            // System.out.println(Arrays.toString(arr));
        }


    }

    /**
     * 自己写的双重循环
     *
     * @param arr
     */
    public static void insertSort1(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertValue = arr[i];
            int k = i - 1;
            int j = 0;
            for (j = k; j >= 0 && insertValue < arr[j]; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = insertValue;
           // System.out.println("第" + i + "轮");
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
        long l = System.currentTimeMillis();
        insertSort(arr);

        Date date2 = new Date();
        //   SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2Str = simpleDateFormatter.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
        long l1 = System.currentTimeMillis();
        System.out.println("耗时毫秒数为:"+(l1-l));
    }


    /**
     * 以下时分析的思路
     * 使用逐步推导的方式来讲解,边离理解
     *     第一轮int[] arr={101,34,119,1};
     *
     *     定义待插入的差个数
     *         int insertVal=arr[1];
     *         int insertIndex=1-1; //arr[1]前面的下标
     *
     *     给InsertVal,找到插入的位置
     *     insertIndex>=0这保证插入位置时,不越界
     *     insertVal<arr[insertIndex]待插入数没有找到插入位置
     *     需要将arr[insertIndex] 后移
     *         while (insertIndex>=0&&insertVal<arr[insertIndex]){
     *             arr[insertIndex+1]=arr[insertIndex];
     *             insertIndex--;
     *         }
     *     当退出while循环时,说明插入的位置找到,insertIndex+1
     *         arr[insertIndex+1]=insertVal;
     *         System.out.println("第一轮插入后");
     *         System.out.println(Arrays.toString(arr));
     */


}
