package com.csw.algorithm.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Auther: 行路
 * @Date: Created on 2020/5/2 21:23 星期六
 * @Description: com.csw.algorithm.greedy
 * @version: 1.0 贪心算法
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台,放入到Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        //将各个电台放入到broadcasts
        HashSet<String> hashSet1=new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2=new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3=new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4=new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5=new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        broadcasts.put("k1",hashSet1);
        broadcasts.put("k2",hashSet2);
        broadcasts.put("k3",hashSet3);
        broadcasts.put("k4",hashSet4);
        broadcasts.put("k5",hashSet5);

        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //创建一个ArrayList,存放电台集合
        ArrayList<String> selects = new ArrayList<>();

        //定义一个临时集合,在遍历过程中,存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<String>();

        //定义一个maxKey,保存在一次遍历过程中,能够覆盖最大未覆盖的地区对应的电台的Key
        //如果maxKey 不为null,则会加入到selects
        String maxKey=null;
        while (allAreas.size()!=0){
            //没进行一次while,需要
            maxKey=null;

            //如果allAreas不为0说明还没有覆盖所有地区
            //遍历 broadcasts,取出对应的key
            for(String key:broadcasts.keySet()){
                //没进行一次for
                tempSet.clear();
                //当前这个Key能覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                //求出tempSet和allAreas集合的交集,交集会赋给tempSet
                tempSet.retainAll(allAreas);
                //如果当前集合包含未覆盖地区的数量比maxKey指向的集合未覆盖的地区还多
                if(tempSet.size()>0&&(maxKey==null||tempSet.size()>broadcasts.get(maxKey).size())){
                    maxKey=key;
                }
            }
            //maxKey!=null,就应该将maxKey加入selects中
            if(maxKey!=null){
                selects.add(maxKey);
                //将maxKey指向的广播电台覆盖的地区,从allAreas去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("得到的选择结果集合"+selects);
    }
}
