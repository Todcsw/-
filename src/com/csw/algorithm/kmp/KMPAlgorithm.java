package com.csw.algorithm.kmp;

import java.util.Arrays;

/**
 * @Auther: 行路
 * @Date: Created on 2020/5/2 16:51 星期六
 * @Description: com.csw.algorithm.kmp
 * @version: 1.0  kmp算法
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next=KmpNext("ABCDABD");
        System.out.println(Arrays.toString(next));
        int index=kmpSearch(str1,str2,next);
        System.out.println("index="+index);
    }



    /**
     * kmp搜索算法
     * @param str1 源字符串
     * @param str2 字串
     * @param next 部分匹配表,是字串对应的部分匹配表
     * @return -1是没有匹配到,否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1,String str2,int[] next){
        //遍历
        for(int i=0,j=0;i<str1.length();i++){

            //需要处理str1.charAt(i)!=str2.charAt(j),去调整j的大小
            //Kmp算法核心

            while (j>0&&str1.charAt(i)!=str2.charAt(j)){
                j=next[j-1];
            }

            if(str1.charAt(i)==str2.charAt(j)){
                j++;
            }
            if(j==str2.length()){
                return i-j+1;
            }
        }
        return -1;
    }

    //获取到一个字符串(字串)的部分匹配值
    public static int[] KmpNext(String dest) {
        //创建一个next保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0; //如果字符串长度为1部分匹配值就是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //当dest.charAt(i)!=dest.charAt(j)时,我们需要next[j-1]获取新的j
            //知道我们发现有这个dest.charAt(i)==dest.charAt(j)满足时,才退出
            while (j>0&&dest.charAt(i)!=dest.charAt(j)){
                j=next[j-1];
            }
            //当dest.charAt(i)==dest.charAt(j)满足时,部分匹配值就+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;

    }
}
