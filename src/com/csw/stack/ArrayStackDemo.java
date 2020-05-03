package com.csw.stack;

import java.util.Scanner;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/15 13:36 星期三
 * @Description: com.csw.stack
 * @version: 1.0
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        //测试以下ArrayStack是否正确
        //先创建一个ArrayStack对象->表示栈
        ArrayStack stack=new ArrayStack(4);
        String key="";
        boolean loop=true;//控制是否退出菜单
        Scanner scanner=new Scanner(System.in);

        while (loop){
            System.out.println("show :表示显示栈");
            System.out.println("exit :退出程序");
            System.out.println("push :表示添加数据到栈");
            System.out.println("pop :表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择:");
            key=scanner.next();
            switch (key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value=scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res=stack.pop();
                        System.out.println(res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
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

//定义一个ArrayStacke 栈
class ArrayStack{
    private int maxSize;
    private int[] stack; //数组模拟栈,数据放在该数组中
    private int top=-1; //表示栈顶,初始化为-1

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack=new int[this.maxSize];
    }

    /**
     * 栈满
     * @return
     */
    public boolean isFull(){
        return top==maxSize-1;
    }

    /**
     * 栈空
     * @return
     */
    public boolean isEmpty(){
        return top==-1;
    }

    /**
     * 入栈
     * @param value
     */
    public void push(int value){
        //先判断栈是否满
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top]=value;
    }

    /**
     * 出栈,将栈顶的数据返回
     * @return
     */
    public int pop(){
        //先判断栈是否空
        if(isEmpty()){
            throw new RuntimeException("栈空,没有数据");
        }
        int value=stack[top];
        top--;
        return value;
    }

    /**
     * 遍历从栈顶遍历数据
     */
    public void list(){
        if(isEmpty()){
            System.out.println("栈空,没有数据");
            return;
        }
        for(int i=top;i>=0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }
}
