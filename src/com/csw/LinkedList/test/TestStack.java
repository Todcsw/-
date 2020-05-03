package com.csw.LinkedList.test;

import java.util.Stack;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/13 21:38 星期一
 * @Description: com.csw.LinkedList.test
 * @version: 1.0
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack=new Stack<>();
        stack.add("java");
        stack.add("love");
        stack.add("c++");
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }
}
