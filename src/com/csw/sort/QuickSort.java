package com.csw.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/20 15:50 星期一
 * @Description: com.csw.sort 快速排序
 * @version: 1.0
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70, 12, 32, -1};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("arr=" + Arrays.toString(arr));
        testTime();  //测试快速排序时间
    }

    /**
     * 快排
     * @param a
     * @param low
     * @param high
     */
    public static void quickSort(int a[],int low,int high) {
        if(low<high) {
            int i=partition(a,low,high);
            quickSort(a,low,i-1);
            quickSort(a,i+1,high);
        }
    }
    public static int partition(int array[],int low,int high) {
        //用子序列的第一个作为基准
        int temp=array[low];
        int i=low;
        int j=high;
        while(i!=j) {
            while(i<j&&array[j]>temp) {
                j--;
            }
            if(i<j) {
                array[i]=array[j];
                i++;
            }
            while(i<j&&array[i]<temp) {
                i++;
            }
            if(i<j) {
                array[j]=array[i];
                j--;
            }
        }
        array[i]=temp;
        return i;
    }

    /**
     * 快排2
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSort2(int[] arr, int left, int right) {
        int l = left; //左下标
        int r = right; //右下标
        //pivot 中轴值
        int pivot = arr[(left + right) / 2];
        int temp = 0; //临时变量
        //while循环的目的是让比pivot值放到左边
        //比pivot值放到右边
        while (l < r) {
            //在pivot的左边一直找,找到大于等于pivot值,才退出
            while (arr[l] < pivot) {
                l += 1;
            }
            //在pivot的右边一直找,找到大于等于pivot值,才退出
            while (arr[r] > pivot) {
                r -= 1;
            }
            //如果l>=r说明pivot的左右两边的值已经按照左边全部是
            //小于等于pivot的值,右边全是大于等于pivot的值
            if (l >= r) {
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //如果交换完后,发现这个arr[l]==pivot值相等--,前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完后,发现这个arr[r]==pivot值相等l++,后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        //如果l==r,必须l++,r--否则为栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向右递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr, l, right);
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
        long l1 = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);

        Date date2 = new Date();
        //   SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2Str = simpleDateFormatter.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
        long l2 = System.currentTimeMillis();
        System.out.println("耗时毫秒数为:" + (l2 - l1));
    }

}
