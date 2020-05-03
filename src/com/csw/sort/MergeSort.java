package com.csw.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/20 17:01 星期一
 * @Description: com.csw.sort 归并排序
 * @version: 1.0
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};  //merge的次数 arr.length-1次
        int[] temp=new int[arr.length]; //归并排序,需要一个额外的空间
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println("归并排序后"+ Arrays.toString(arr));

        testTime(); //测试归并排序时间
    }

    //分+和
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if(left<right){
            int mid=(left+right)/2; //中间索引
            //向左递归进行分解
            mergeSort(arr,left,mid,temp);
            //向右递归进行分解
            mergeSort(arr,mid+1,right,temp);
            //到合并
            merge(arr,left,mid,right,temp);
        }
    }

    /**
     * 合并的方法
     *
     * @param arr   待排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  做中转数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left; //初始化i,左边有序序列的初始索引
        int j = mid + 1;//初始化j,右边有序序列的初始索引
        int t = 0; //指向temp数组的当前索引

        //(一)先把左右两边(有序)的数据按照规则填充到temp数组
        //直到左右两边的有序序列,有一边处理完毕为止
        while (i <= mid && j <= right) {  //继续
            //如果发现左边的有序序列的当前元素,小于等于右边有序序列的当前元素
            //即将左边的当前元素拷贝到temp数组中
            //然后t往后移
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else { //反之,右边的有序序列当前元素小于左边的
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        //(二)把剩余数据的一边的数据依次全部填充到temp中
        while (i <= mid) { //左边的有序序列还有剩余元素,就全部填充到temp
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        //(三)将temp数组的元素拷贝到arr
        //注意并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        while (tempLeft<=right){
            //第一次合并tempLeft=0,right=1 //第二次tempLeft=2,right=3 //第三次tempLeft=0,right=3
            //最后一次tempLeft=0,right=7
            //依次拷贝
            arr[tempLeft]=temp[t];
            t+=1;
            tempLeft+=1;
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
        int[] temp=new int[arr.length];
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormatter.format(date1);

        System.out.println("排序前的时间是=" + date1Str);
        long l1 = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length - 1,temp);

        Date date2 = new Date();
        //   SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2Str = simpleDateFormatter.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
        long l2 = System.currentTimeMillis();
        System.out.println("耗时毫秒数为:" + (l2 - l1));
    }

}
