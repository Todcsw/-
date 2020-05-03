package com.csw.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/19 22:22 星期日
 * @Description: com.csw.sort 希尔排序
 * @version: 1.0
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort(arr);
        testTime(); //测试交换法的时间
    }

    /**
     * 交换式的希尔排序(交换的时候有点冒泡的意思)
     * @param arr
     */
    public static void shellSort(int[] arr) {
        System.out.println("希尔排序插入采用交换法");
        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历个组中所有的元素(共gap组,每一组个元素),步长为gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素,说明交换
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }

           // System.out.println("希尔排序第" + (++count) + "轮:" + Arrays.toString(arr));
        }


    }

    /**
     * 移位式的希尔排序(交换的时候采用的是插入)
     * @param arr
     */
    public static void shellSort2(int[] arr) {
        System.out.println("希尔排序插入采用移位");

        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {

            //增量gap,并逐步缩小增量
            for (int i = gap; i < arr.length; i++) {
                //遍历个组中所有的元素(共gap组,每一组个元素),步长为gap
                   int j=i;
                   int temp=arr[j];
                   if (arr[j]<arr[j-gap]){
                       while (j-gap>=0&&temp<arr[j-gap]){
                           //移动
                           arr[j]=arr[j-gap];
                           j-=gap;
                       }
                       //当退出while后,就给temp找到插入位置
                       arr[j]=temp;
                }
            }

            // System.out.println("希尔排序第" + (++count) + "轮:" + Arrays.toString(arr));
        }


    }


    /**
     * 测试时间
     */
    public static void testTime() {
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormatter.format(date1);

        System.out.println("排序前的时间是=" + date1Str);
        long l = System.currentTimeMillis();
        shellSort2(arr);

        Date date2 = new Date();
        //   SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2Str = simpleDateFormatter.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
        long l1 = System.currentTimeMillis();
        System.out.println("耗时毫秒数为:"+(l1-l));
    }

    /**
     * 使用逐步推导得到方式
     *
     * @param arr
     */
    public static void shellSortTest(int[] arr) {
        int temp = 0;
        //希尔排序的第一轮排序
        //因为第一轮是将10个数据分成了5组
        for (int i = 5; i < arr.length; i++) {
            //遍历个组中所有的元素(共5组,每一组两个元素),步长为5
            for (int j = i - 5; j >= 0; j -= 5) {
                //如果当前元素大于加上步长后的那个元素,说明交换
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));

        //希尔排序的第一轮排序
        //因为第一轮是将10个数据分成了5组
        for (int i = 2; i < arr.length; i++) {
            //遍历个组中所有的元素(共5组,每一组两个元素),步长为5
            for (int j = i - 2; j >= 0; j -= 2) {
                //如果当前元素大于加上步长后的那个元素,说明交换
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));

        //希尔排序的第三轮排序
        //因为第一轮是将10个数据分成了5组
        for (int i = 1; i < arr.length; i++) {
            //遍历个组中所有的元素(共5组,每一组两个元素),步长为5
            for (int j = i - 1; j >= 0; j -= 1) {
                //如果当前元素大于加上步长后的那个元素,说明交换
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
