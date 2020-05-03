package com.csw.queue.test;

import java.util.Scanner;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/10 13:37 星期五
 * @Description: com.csw.queue.Test
 * @version: 1.0
 */
public class 队列练习2 {
    public static void main(String[] args) {
        //测试数组模拟环形队列
        CircleQueue queue= new CircleQueue(4);
        char key=' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop=true;
        //输出一个菜单
        while(loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key=scanner.next().charAt(0); //接收一个字符
            switch (key){
                case 's':
                    queue.show();
                    break;
                case 'a':
                    System.out.println("输入一个数字");
                    int value=scanner.nextInt();
                    queue.add(value);
                    break;
                case 'g': //出去数据
                    try{
                        int res=queue.get();
                        System.out.printf("取出数据是%d",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //出去数据
                    try{
                        int res=queue.head();
                        System.out.printf("队列的头数据是%d",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop=false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }


}

class CircleQueue{

    public  int maxSize;
    public int rear; //队尾
    public int front; //队头
    public int arr[];

    public CircleQueue(int maxSize) {
        this.maxSize = maxSize;
        arr=new int[maxSize];
    }


    public boolean isEmpty(){
        return rear==front;
    }
    public  boolean isFull(){
        return (rear+1)%maxSize==front;
    }

    public  int Size(){
        return (rear-front+maxSize)%maxSize;
    }

    public void add( int i){
        if(isFull()){
            System.out.println("队列以满");
            return;
        }

        arr[rear]=i;
        rear=(rear+1)%maxSize;
    }

    public int get(){
        if(isEmpty()){
            throw  new RuntimeException("队列没有数据");
        }
        int value=arr[front];
        front=(front+1)%maxSize;
        return value;
    }

    public void show(){
        if(isEmpty()){
            System.out.println("队列为空");
        }
        for(int i=front;i<front+Size();i++)
            System.out.printf("arr[%d]=%d\n",(i)%maxSize,arr[(i)%maxSize]);

    }

    public  int head(){
        if(isEmpty()){
            throw  new RuntimeException("队列没有数据");
        }
        return arr[front];
    }
}
