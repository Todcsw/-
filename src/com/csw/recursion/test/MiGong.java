package com.csw.recursion.test;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/19 10:03 星期日
 * @Description: com.csw.recursion.test  自己写迷宫练习,加深影响
 * @version: 1.0
 */
public class MiGong {
    public static void main(String[] args) {
        int[][] arr = new int[8][7];
        for (int i = 0; i < 8; i++) {
            arr[i][0] = 1;
            arr[i][6] = 1;
        }
        for (int i = 0; i < 7; i++) {
            arr[0][i] = 1;
            arr[7][i] = 1;
        }
        arr[3][1] = 1;
        arr[3][2] = 1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf(arr[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("逃出迷宫");
        setWay(arr, 1, 1);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf(arr[i][j] + " ");
            }
            System.out.println("");
        }
    }

    /**
     * 定制策略 下->右->上->左, //0代表没有走过的路线,1代表迷宫的围墙,2代表路线，3代表尝试走过但没有走通
     *
     * @param map 地图
     * @param i   起始坐标
     * @param j
     * @return
     */
    public static boolean setWay(int map[][], int i, int j) {
        if (map[6][5] == 2) {
            //已经找到通道
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            } else {
                //map[i][j]= 1,2,3
                return false;
            }
        }

    }
}
