package com.csw.stack;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/15 20:25 星期三
 * @Description: com.csw.stack
 * @version: 1.0
 */
public class Calculator {
    public static void main(String[] args) {
        //根据思路
        String expression = "7-6-1-8";
        //先创建两个栈,数栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要扫描的相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; //将没次扫描得到的char保存到ch
        String keepNum = "";
        //开始while循环的扫描expression
        while (true) {
            //一次得到experession的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            //判断ch是什么做响应的处理
            if (operStack.isOper(ch)) { //如果是运算符
                //判断当前的符号栈是否为空
                if (!operStack.isEmpty()) {
                    //如果符号栈有操作符,就进行比较,如果当前的操作符优先级,小于火等于栈中的操作符,就需要从数栈
                    //中pop出两个数,再从符号栈中pop出一个符号,进行运算,将得到结果,入数栈后,然后将当前的操作符入符号栈
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        //从数栈中pop出两个数
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //把运算的结果入数栈
                        numStack.push(res);
                        //然后把当前的操作符入符号栈
                        operStack.push(ch);
                    } else {
                        //如果当前的操作符优先级大于,直接入符号栈
                        operStack.push(ch);
                    }
                } else {
                    //如果为空,直接入符号栈
                    operStack.push(ch);
                }
            } else {
                //如果是数就直接用数栈
                // numStack.push(ch-48);//? "1+3"
                //1.当处理多位数时,不能发现一个数就立即入栈,因为可能是多位数
                //2.在处理数,需要想expression的表达式index后再看一位,如果是数
                //3.因此需要定义字符串变量,用于拼接
                //处理多位数
                keepNum += ch;
                //如果ch已经是expression的最后以位,就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.valueOf(keepNum));
                } else {

                    //判断下一个字符是不是数字,如果是数字,则进行继续扫描，如果是运算符则入栈
                    //注意是看后面一位,不是index++
                    //如果后一位是操作符,则入栈
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.valueOf(keepNum));
                        //重要！！！keepNum清空
                        keepNum = "";
                    }
                }

            }
            //让Index+1,并判断是否扫描到expression的最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        //当表达式扫描完毕,就顺序的从数栈和符号栈中pop出相应的符号,并允许
        while (true) {
            //如果符号栈为空,则计算到最后的结果,数栈中只有一个数字
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res); //入栈
        }
        //将数栈中最后数pop出,就是结果
        int res2 = numStack.pop();
        System.out.printf("表达式%s=%d", expression, res2);
    }
}

/**
 * Description:创建的一个栈
 * 需要扩展功能
 *
 * @author Todcsw
 * @date
 */
class ArrayStack2 {
    private int maxSize;
    private int[] stack; //数组模拟栈,数据放在该数组中
    private int top = -1; //表示栈顶,初始化为-1

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //返回当前栈顶的值,但是不pop出来
    public int peek() {
        return stack[top];
    }

    /**
     * 栈满
     *
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 栈空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     *
     * @param value
     */
    public void push(int value) {
        //先判断栈是否满
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈,将栈顶的数据返回
     *
     * @return
     */
    public int pop() {
        //先判断栈是否空
        if (isEmpty()) {
            throw new RuntimeException("栈空,没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 遍历从栈顶遍历数据
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空,没有数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    /**
     * 返回运算符的优先级,假定数字越大,优先级就越高
     *
     * @param oper
     * @return
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1; //假设目前的表达式只有+,-,*,/
        }
    }

    /**
     * 判断是不是运算符
     *
     * @param val
     * @return
     */
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * 计算的方法
     *
     * @param num1
     * @param num2
     * @param oper
     * @return
     */
    public int cal(int num1, int num2, int oper) {
        int res = 0;//res用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1; //注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

}