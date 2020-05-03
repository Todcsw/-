package com.csw.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/26 14:18 星期日
 * @Description: com.csw.sort 堆排序
 * @version: 1.0
 */
public class Heapsort {
    public static void main(String[] args) {
        //要求将数组升序排列
        int arr[]={4,6,8,5,9};
        heapSort(arr);
        testTime();//测试时间

        //System.out.println('0'*'0'/('0'+'0')==24);
    }

    //编写一个堆排序的方法
    public static void heapSort(int arr[]){
        int temp=0;
        System.out.println("堆排序");
        //从堆底开始调整
        for(int i=arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);
        }

        for(int j=arr.length-1;j>0;j--){
            //交换
            temp=arr[j];
            arr[j]=arr[0];
            arr[0]=temp;
            //因为交换后只有栈顶不符合,所以直接调整栈顶
            adjustHeap(arr,0,j);
        }
        System.out.println("数组="+ Arrays.toString(arr));
    }

    //将一个数组(二叉树),调整成一个大顶推

    /** 功能：完成将以i对应的非叶子结点的树,调整成大顶堆
     *  int[] arr={4,6,8,5,9} 调整后i=1 adjustHeap->调整成49856
     *  再次调用adjustHeap传入的是0 调整成96854
     *  调整需要从下至上
     * @param arr   表示待调整的数组
     * @param i     表示非叶子结点在数组中的索引
     * @param length 表示对多少个元素继续调整,length是在主键减少的
     */
    public static void adjustHeap(int[] arr,int i,int length){
        int temp=arr[i]; //取出当前元素的值,保存在临时变量中
        //开始调整 k=i*2+1 k是i结点的左子结点
        for(int k=i*2+1;k<length;k=k*2+1){
            if(k+1<length&&arr[k]<arr[k+1]){
                //说明左子结点小于右子结点的值
                k++;
            }
            if(arr[k]>temp){
                //如果子结点大于父结点
                arr[i]=arr[k]; //把较大的值赋给当前结点
                i=k; //i指向k,继续循环比较
            }else{
                break; //
            }
        }
        //当for循环后,已经将以i为父结点的树的最大值,放在了最顶(局部)
        arr[i]=temp; //将temp值放到调整后的位置
    }
    /**
     * 测试时间
     */
    public static void testTime() {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
        }
        int[] temp=new int[arr.length];
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormatter.format(date1);

        System.out.println("排序前的时间是=" + date1Str);
        long l1 = System.currentTimeMillis();
        heapSort(arr);

        Date date2 = new Date();
        //   SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2Str = simpleDateFormatter.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
        long l2 = System.currentTimeMillis();
        System.out.println("耗时毫秒数为:" + (l2 - l1));
    }
}
