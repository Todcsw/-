package com.csw.queue;

import java.util.Scanner;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/4 20:07 星期六
 * @Description: com.csw.queue
 * @version: 1.0
 */
public class CircleArrayQueueDemo {
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
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数字");
                    int value=scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': //出去数据
                    try{
                        int res=queue.getQueue();
                        System.out.printf("取出数据是%d",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //出去数据
                    try{
                        int res=queue.headQueue();
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
    private int maxSize;  //表示数组的最大容量

    //front 变量的含义做一个调整:front就指向队列的第一个元素,也就是arr[front]
    //front 的初始值为=0
    private int front; //执行队列头

    //rear变量的含义做一个调整,rear指向队列的最后一个元素的后一个位置,因为希望空出一个位置
    //rear 的初始值为=0
    private int rear; //队列尾
    private int[] arr;//该数据用于存放数据,模拟队列

    public CircleQueue(int arrMaxSize) {
        maxSize=arrMaxSize;
        arr=new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull(){
        return (rear+1)%maxSize==front;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear==front;
    }

    //填加数据
    public void addQueue(int n){
        //判断队列是否满
        if(isFull()){
            System.out.println("队列满,不能加入数据~");
            return;
        }

        //直接将数据加入
        arr[rear]=n;
        //将rear后移,这里必须取模
        rear=(rear+1)%maxSize;
    }


    //获取队列的数据,出队列
    public  int getQueue(){
        //判断队列是否为空
        if(isEmpty()){
            //通过抛出异常
            throw  new RuntimeException("队列空,不能取数据");
        }
        //这里需要分析front是指向队列的第一个元素
        //1.先把front对应的值保留到一个临时变量
        //2.将front后移，考虑取余
        //3.将零时的变量返回
        int value=arr[front];
        front=(front+1)%maxSize;
        return value;
    }

    //显示队列的所有数据
    public void showQueue(){
        //遍历
        if(isEmpty()){
            System.out.println("队列空的,没有数据");
            return;
        }

        for(int i=front;i<front+size();i++){
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }

    }

    //求出当前队列的有效数据个数
    public int size(){

        return (rear+maxSize-front)%maxSize;
    }


    //显示队列的头数据
    public int headQueue(){
        //判断
        if(isEmpty()){
            throw new RuntimeException("队列为空,没有数据");
        }
        return arr[front];
    }
}
