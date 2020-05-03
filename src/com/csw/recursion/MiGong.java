package com.csw.recursion;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/17 23:11 星期五
 * @Description: com.csw.recursion 迷宫回溯问题
 * @version: 1.0
 */
public class MiGong {
    public static void main(String[] args) {
        //先创建一个二维数组,模拟迷宫
        //地图
        int[][] map=new int[8][7];
        //使用1表示强
        //上下全部置为1
        for(int i=0;i<7;i++){
            map[0][i]=1;
            map[7][i]=1;
        }
        //左右全部置为1
        for(int i=0;i<8;i++){
            map[i][0]=1;
            map[i][6]=1;
        }
        //设置挡板
        map[3][1]=1;
        map[3][2]=1;
        //map[1][2]=1;
        map[2][2]=1;
        //输出地图
        System.out.println("地图");
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println("");
        }
        //使用递归回溯来给小球找路
        //使用递归回溯
        //setWay(map,1,1);
        setWay2(map,1,1);
        //输出新的地图
        System.out.println("新的地图");
        for(int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println("");
        }
    }

    /**
     * 说明 map表示地图,i,j白澳式地图从那个位置开始触发(1,1)
     * 如果小球能到map[6][5] 位置,则说明通路找到
     * 约定:当map[i][j]=0 表示没走过,当为1表示强 2为通路可以走,3表示已经走过
     * 在走迷宫需要确定一个策略(方法)先走下->右->上->左,如果走不通就回溯
     * @param map 表示地图
     * @param i 从按个位置开始找
     * @param j
     * @return  如果找到通路,就返回true 否则返回false
     */
    public static boolean setWay(int[][] map,int i,int j){
        if(map[6][5]==2){
            //通路已经找到
            return true;
        }else{
            if(map[i][j]==0){ //如果当前这个点还没有走过
                //下->右->上->左 按照策略走
                map[i][j]=2; //假定该点是可以走通的.
                if(setWay(map,i+1,j)){
                    //向下走,
                    return true;
                }else if(setWay(map,i,j+1)){
                    //向右走
                    return true;
                }else if(setWay(map,i-1,j)){
                    //向上走
                    return true;
                }else if(setWay(map,i,j-1)) {
                    //向左走
                    return true;
                }else{
                    //说明该点是走不通的
                    map[i][j]=3;
                    return false;
                }

            }else{
                //如果map[i][j]!=0 可能是1，2，3
                return false;
            }
        }
    }

    /**
     * 修改策略 改成上->右->下->左
     * @param map
     * @param i
     * @param j
     * @return
     */
    public static boolean setWay2(int[][] map,int i,int j){
        if(map[6][5]==2){
            //通路已经找到
            return true;
        }else{
            if(map[i][j]==0){ //如果当前这个点还没有走过
                //上->右->下->左按照策略走
                map[i][j]=2; //假定该点是可以走通的.
                if(setWay2(map,i-1,j)){
                    //向上走,
                    return true;
                }else if(setWay2(map,i,j+1)){
                    //向右走
                    return true;
                }else if(setWay2(map,i+1,j)){
                    //向下走
                    return true;
                }else if(setWay2(map,i,j-1)) {
                    //向左走
                    return true;
                }else{
                    //说明该点是走不通的
                    map[i][j]=3;
                    return false;
                }

            }else{
                //如果map[i][j]!=0 可能是1，2，3
                return false;
            }
        }
    }
}


