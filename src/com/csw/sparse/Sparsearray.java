package com.csw.sparse;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/2 16:47 星期四
 * @Description: com.csw  稀疏数组和普通数组的转换
 * @version: 1.0
 */
public class Sparsearray {
    public static void main(String[] args) {
        //创建一个原始的二维数组 11*11
        //0:表示没有棋子,1表示黑子,2表示蓝子
        int[][] chessArr=new int[11][11];

        chessArr[1][2]=1;
        chessArr[2][3]=2;
        chessArr[4][5]=2;
        //输出原始的二维数组
        System.out.println("原始的二维数组");
        for(int [] row:chessArr){
            for(int a:row){
                System.out.printf("%d\t",a);
            }
            System.out.println("");
        }

        //将二维数组转换为稀疏数组的思想
        //1.先遍历二维数组 得到非0数据的个数
        int sum=0;
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                if(chessArr[i][j]!=0){
                    sum++;
                }
            }
        }
        System.out.println("sum="+sum);

        //创建一个稀疏数组
        int[][] sparseArray=new int[sum+1][3];
        //给稀疏数组赋值
        sparseArray[0][0]=11;
        sparseArray[0][1]=11;
        sparseArray[0][2]=sum;

        //遍历二维数组 将非0数据存放到稀疏数组
        int count=0; //用于记录时第几个非0数据
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                if(chessArr[i][j]!=0){
                    count++;
                   sparseArray[count][0]=i;
                   sparseArray[count][1]=j;
                   sparseArray[count][2]=chessArr[i][j];
                }
            }
        }

        // 输出稀疏数组的形式
        System.out.println("稀疏数组----");
        for(int [] row:sparseArray){
            for(int a:row){
                System.out.printf("%d\t",a);
            }
            System.out.println("");
        }


        //将稀疏数组-》恢复为原来的数组
        /**
         * 1.先读取稀疏数组的第一行,根据第一行的数据,创建原始的二维数组
         * 2.在读取稀疏数组后几行数据,并赋给原始的二维数组即可
         *
         */

        //1.先读取稀疏数组的第一行,根据第一行的数据,创建原始的二维数组
        int[][] chessArr2=new int[sparseArray[0][0]][sparseArray[0][1]];
        //2.在读取稀疏数组后几行数据,并赋给原始的二维数组即可

        //从第二行开始
        for(int i=1;i<=sparseArray[0][2];i++){
                chessArr2[sparseArray[i][0]][sparseArray[i][1]]=sparseArray[i][2];
        }


        //输出恢复后的数组
        System.out.println("恢复后的数组----");
        for(int [] row:chessArr2){
            for(int a:row){
                System.out.printf("%d\t",a);
            }
            System.out.println("");
        }

    }
}
