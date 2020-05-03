package com.csw.queue.test;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/9 20:46 星期四
 * @Description: com.csw.queue.Test
 * @version: 1.0
 */
public class 队列练习1 {
    public static void main(String[] args) {
        LinkedQueue linkedQueue=new LinkedQueue(4);
        linkedQueue.add(1);
        linkedQueue.add(2);
        linkedQueue.add(3);
        linkedQueue.showQueue();
        linkedQueue.get();
        linkedQueue.showQueue();
    }

}
class LinkedQueue{
    //队列的队长(chang)
    public int maxSize;

    //队尾,指向队列的最后一个元素的后一个位置
    public int  rear=0;

    //队头,指向队列的第一个元素
    public int front=0;

    //该数据用于存放数据,模拟队列
    public int[] arr;

    public LinkedQueue(int maxSize){
        this.maxSize=maxSize;
        arr=new int[maxSize];
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isEmpty(){
        return rear==front;
    }

    /**
     * 判断队列是否为满
     * @return
     */
    public boolean isFull(){
        return (rear+1)%maxSize==front;
    }

    /**
     * 向队列添加元素
     */
    public void add(int i){
        if(isFull()){
            System.out.println("队列已满,不能添加");
            return;
        }
        arr[rear]=i;
        //将rear后移
        rear=(rear+1)%maxSize;
    }

    /**
     * 出队列
     * @return
     */
    public int get(){
        if(isEmpty()){
            throw  new RuntimeException("队列空,不能取数据");
        }
        int i=arr[front];
        front=(front+1)%maxSize;
        return i;
    }

    /**
     * 显示队列所有数据
     */
    public void showQueue(){

        if(isEmpty()){
            System.out.println("队列空的,没有数据");
            return;
        }
        //遍历
        for(int i=front;i<front+Size();i++)
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
    }

    public int Size(){
        return (rear-front+maxSize)%maxSize;
    }

}



