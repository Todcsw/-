package com.csw.stack;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/17 13:58 星期五
 * @Description: com.csw.stack 中缀表达式转后缀表达式  再使用栈计算逆波兰表达式(后缀表达式)
 * @version: 1.0
 */
public class PolandNotation {
    public static void main(String[] args) {
        //完成将一个中缀表达式转成后缀表达式的功能
        //说明
        //1. 1+((2+3)*4)-5 => 转成1 2 3 + 4 * + 5 -
        //2. 因为直接对str进行操作不方便,先将字符串装成对应的List;
        //即 ArrayList[1,+,(,]
        //3.得到的中缀表达式对应的List->后缀表达式对应的List
        //即ArrayList[12, +, (, (, 20, +, 3, ), *, 4, ), -, 5] => ArrayList[1 ,2, 3, +, 4, *, +, 5 ,-]
        String expression = "1+((2+3)*4)-5";
        List<String> list = toInfixExpressionList(expression);
        System.out.println("中缀表达式:"+list);
        //后缀表达式
        List<String> list1 = parseSuffixExpressionList(list);
        System.out.println("后缀表达式:"+list1);
        System.out.printf("expressioon=%d",calculate(list1));


        //先定义一个逆波兰表达式
        //(3+4)*5-6 -> 3 4 + 5 * 6 -
        //为了方便,数字和符号用空格隔开
//        String suffixExpression = "30 4 + 5 * 6 -";
        //思路
        //1.先将3 4 + 5 * 6 - 放到ArrayList中
        //2. 将ArrayList传递给一个方法,遍历arrayList配合栈,完成计算
//        List<String> rpnList = getListString(suffixExpression);
//        System.out.println(rpnList);
//        int res = calculate(rpnList);
//        System.out.println("计算结果:" + res);
    }

    /**
     * 将第一个逆波兰表达式,依次将数据和运算符放入到arrayList中
     *
     * @param suffixExpression
     * @return
     */
    public static List<String> getListString(String suffixExpression) {
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }


    /**
     * 将中缀表达式转成对应的List
     * s="1+((2+3)*4)-5"
     */
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List,存放中缀表达式对应的内容
        List<String> ls = new ArrayList<>();
        int i = 0; //这时一个指针,用于遍历中缀表达式字符串
        String str; //对多位数的拼接
        char c; //每遍历到一个字符,就放入到c
        do {
            //如果c是一个非数字,我们就需要加入到ls中

            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++; //i需要后移
            } else {
                //如果是一个数,需要考虑多为数
                str = "";//先将str置成""
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;
    }

    /**
     * //即ArrayList[12, +, (, (, 20, +, 3, ), *, 4, ), -, 5] => ArrayList[1 ,2, 3, +, 4, *, +, 5 ,-]
     *
     * @param ls
     * @return
     */
    public static List<String> parseSuffixExpressionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<String>(); //符号栈
        //说明s2这个栈,在整个转换过程中，没有pop操作,而且后面我们还需要逆序输出
        //因此我们直接使用List<String> s2
        List<String> s2 = new ArrayList<>();//存储中间结果的list

        //遍历ls
        for (String item : ls) {
            //如果是一个数,加入到s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号“）”,则依次弹出s1栈顶的运算符,并压入s2，直到遇到左括号位置,此时将一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//将“（”弹出s1这个栈
            } else {
                //当item的优先级小于等于s1栈顶运算符，将s1栈顶的运算符弹出并加入到s2中,再次转到4,1与s1中新的栈顶运算符比较
                //问题:缺少一个比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek())>=Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //还需要将item压入栈中
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次弹出并加入到s2
        while (s1.size()!=0){
            s2.add(s1.pop());
        }
        return s2; //因为存放到list，因此按照顺序输出就是顺序的
    }


    /**
     * 3 4 + 5 * 6 -
     * 完成对逆波兰表达式的运算
     * 1.从左至右扫描,将3和4压入堆栈中
     * 2.遇到+运算符,因此弹出4和3(4为栈顶元素,3为次顶元素,)计算出3+4的值,得7再将7入栈;
     * 3.将5入栈
     * 4.接下来是x运算,因此弹出5和7计算5*7=35,将35入栈,将6入栈
     * 最后是-运算符,计算出35-6的值,即29由此得出结果
     *
     * @param ls
     * @return
     */
    public static int calculate(List<String> ls) {
        //创建一个栈,只需要一个栈
        Stack<String> stack = new Stack<>();
        //遍历ls
        for (String item : ls) {
            //使用正则表达式来取出数
            if (item.matches("\\d+")) {
                //匹配的是多位数
                stack.push(item);
            } else {
                //pop出两个数,并运算,再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push(res + "");
            }

        }
        //最后留在stack中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }
}

/**
 * Description:编写一个类Operation可以返回一个运算符对应的优先级
 *
 * @author Todcsw
 * @date
 */
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法,返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result =ADD;
            break;
            case "-":
                result = SUB;
                break;
            case "*":
                result =MUL;
                break;
            case "/":
                result =DIV;
                break;
            default:
                //result=0;
                //System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}