package com.csw.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/20 21:27 星期一
 * @Description: com.csw.sort 基数排序(目前只能排序正数)
 * @version: 1.0
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
        testTime(); //测试时间
    }

    /**
     * 基数排序
     */
    public static void radixSort(int[] arr) {
        System.out.println("基数排序~~~~");
        //1.得到数组中最大的数的位数
        int max = arr[0];//假设第一数就是最大值
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength = (max + "").length();

        int[][] bucket = new int[10][arr.length];
        int[] bucketElementCounts = new int[10];

        //使用循环将代码处理
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //争对每个元素对应位进行判断,第一次是个位,第二次是十位 ,第三次是百位
            for (int j = 0; j < arr.length; j++) {
                //取出每个元素的对应的值
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;

            }
            //按照这个桶的顺序(一维数组的下标依次取出数据,放入原来的数组)
            int index = 0;
            //遍历每一个桶,并将桶中是数据,放入到原数组中
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据,才放入到原数组中
                if (bucketElementCounts[k] != 0) {
                    //循环该桶即第k个桶,放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr
                        arr[index++] = bucket[k][l];
                    }
                }
                //第i+1论处理后,需要将每个bucketElementCounts[k]=0!!!!1
                bucketElementCounts[k] = 0;
            }
           // System.out.println("第" + (i + 1) + "轮,对个位的排序处理arr=" + Arrays.toString(arr));
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
        radixSort(arr);

        Date date2 = new Date();
        //   SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2Str = simpleDateFormatter.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
        long l2 = System.currentTimeMillis();
        System.out.println("耗时毫秒数为:" + (l2 - l1));
    }


    /**
     * 基数排序方法 思路分析得到推论
     * @param arr
     */
    public static void radixSortTest(int[] arr) {
        //第一轮排序(针对每个元素的各位进行排序处理)

        //定义一个二维数组,表示10个桶,每个桶就是一个二维数组
        //1.二维数组包含10个一维数组
        //2.为了防止在放数的时候数据溢出,则每个一位数组(桶),大小位arr.length
        //3.名明确,基数排序是使用空间换时间
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中,实际存放了多少数据,我们定义一个一维数组记录各个桶的每次放入的数据个数
        //bucketElementCounts[0],记录的就是bucket[0]桶的放入数据个数
        int[] bucketElementCounts = new int[10];

        //第一轮
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位的值
            int digitOfElement = arr[j] % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;

        }
        //按照这个桶的顺序(一维数组的下标依次取出数据,放入原来的数组)
        int index = 0;
        //遍历每一个桶,并将桶中是数据,放入到原数组中
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据,才放入到原数组中
            if (bucketElementCounts[k] != 0) {
                //循环该桶即第k个桶,放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
            }
            //第1论处理后,需要将每个bucketElementCounts[k]=0!!!!1
            bucketElementCounts[k] = 0;
        }
        System.out.println("第一轮,对个位的排序处理arr=" + Arrays.toString(arr));

        //============================================
        //第二轮 对10位
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位的值
            int digitOfElement = arr[j] / 10 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;

        }
        //按照这个桶的顺序(一维数组的下标依次取出数据,放入原来的数组)
        index = 0;
        //遍历每一个桶,并将桶中是数据,放入到原数组中
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据,才放入到原数组中
            if (bucketElementCounts[k] != 0) {
                //循环该桶即第k个桶,放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
            }
            //第1论处理后,需要将每个bucketElementCounts[k]=0!!!!1
            bucketElementCounts[k] = 0;
        }
        System.out.println("第二轮,对个位的排序处理arr=" + Arrays.toString(arr));


        //============================================
        //第三轮
        for (int j = 0; j < arr.length; j++) {
            //取出每个元素的个位的值
            int digitOfElement = arr[j] / 100 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;

        }
        //按照这个桶的顺序(一维数组的下标依次取出数据,放入原来的数组)
        index = 0;
        //遍历每一个桶,并将桶中是数据,放入到原数组中
        for (int k = 0; k < bucketElementCounts.length; k++) {
            //如果桶中有数据,才放入到原数组中
            if (bucketElementCounts[k] != 0) {
                //循环该桶即第k个桶,放入
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    //取出元素放入到arr
                    arr[index++] = bucket[k][l];
                }
            }
            //第1论处理后,需要将每个bucketElementCounts[k]=0!!!!1
            bucketElementCounts[k] = 0;
        }
        System.out.println("第三轮,对个位的排序处理arr=" + Arrays.toString(arr));
    }
}
