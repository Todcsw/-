package com.csw.LinkedList;

import jdk.nashorn.internal.ir.Flags;

import java.util.Stack;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/8 22:09 星期三
 * @Description: com.csw.LinkedList 该链表按照添加顺序存储。
 * @version: 1.0
 */
public class SIngleLinkedListDemo {
    public static void main(String[] args) {
        //测试代码
        //创建节点
        HeroNode hero1 = new HeroNode(1, "松江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢", "玉");
        HeroNode hero3 = new HeroNode(3, "无用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建链表
        SingleLinkedList singleLinkedList=new SingleLinkedList();
        //加入按照编号的顺序
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);


        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);

        //修改前

        singleLinkedList.list();

        //测试修改节点的代码
        HeroNode hero5=new HeroNode(2,"骁龙","855");
        singleLinkedList.update(hero5);

        System.out.println("~~~~~~~~");
        //显示
        singleLinkedList.del(4);
        singleLinkedList.list();

        System.out.println("有效节点的个数"+getLength(singleLinkedList.getHead()));

        System.out.println("测试得到倒数第k个");
        HeroNode res=findLastIndexNode(singleLinkedList.getHead(),3);
        System.out.println(res);


        System.out.println("逆序打印");
        reversePrint(singleLinkedList.getHead()); //没有改变链表的本身结构

        System.out.println("测试反转");
        reversetList(singleLinkedList.getHead());
        singleLinkedList.list();


    }


    /**
     * //方法,获取单链表的节点个数(如果带头节点的链表,需求不统计头节点)
     *这是一个方法,返回单链表的有效的节点个数
     * @param head 链表的头节点
     * @return 返回的是有效节点的个数
     */
    public static int getLength(HeroNode head){
        if(head.next==null){ //空链表
            return 0;
        }
        int length=0;
        //定义一个辅助变量
        HeroNode cur=head.next;
        while(cur!=null){
            length++;
            cur=cur.next;
        }
        return length;
    }



    /**
     * //查找单链表中的倒数第k个结点(新浪面试题)
     *     //思路
     *     //1.编写一个方法,接收head节点,同时接收一个index
     *     //2.index 表示是倒数第Index个节点
     *     //3.先把链表从头到尾遍历,得到链表的总长度,getLength
     *     //4.得到size后,我们从链表的第一个开始遍历(size-index)个,就可以得到
     *     //5,如果找到了,则返回该节点,否则返回null
     * @param head
     * @param index
     * @return
     */
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        //判断如果链表为空,返回null
        if(head.next==null){
            return null; //没有找到
        }
        //第一次遍历得到的链表的长度(节点的个数)
        int size=getLength(head);
        //第二次遍历 size-index位置,就是我们倒数的第k个节点
        //先做一个(index)数据的校验
        if(index<=0||index>size){
            return null;
        }
        //定义一个辅助变量,for循环定位到倒数的Index
        HeroNode cur=head.next;
        for(int i=0;i<size-index;i++){
            cur=cur.next;
        }
        return cur;

    }


    //将单链表进行反转
    public static void  reversetList(HeroNode head){
        //如果链表为空,或者只有一个节点,无需反转,直接返回
        if(head.next==null||head.next.next==null){
            return ;
        }
        //先定义一个辅助的变量,帮助我们遍历原来的链表
        HeroNode cur=head.next;
        HeroNode next=null; //指向当前节点[cur]的下一个节点
        HeroNode reverseHead=new HeroNode(0,"","");
        //遍历原来的链表,并从点到尾遍历原来的链表,每遍历一个节点,就将其取出,并放在新的链表reverseHead的最前端
        while(cur!=null){
            next=cur.next;//先暂时保存当前节点的下一个节点
            cur.next=reverseHead.next; //将cur的下一个节点指向新的链表的最前端
            reverseHead.next=cur; //将cur连接到新的链表上
            cur=next; //让cur指向下一个节点,后移一次
        }
        //将head.next指向reverseHead.next,实现单链表的反转
        head.next=reverseHead.next;
    }

    //从尾到头打印单链表(逆序打印单链表)
    //方式一:先将单链表进行反转,然后再遍历,破坏原来的单链表的结构
    //方式二:利用栈,将各个节点压入栈,然后利用栈的先进后厨特点,实现逆序打印的效果。
    //使用方式二
    public static void  reversePrint(HeroNode head){
        if(head.next==null){
            return;//空链表不能打印
        }
        //创建一个栈,将各个节点压入栈
        Stack<HeroNode> stack=new Stack<>();
        HeroNode cur=head.next;
        //将链表的所有节点压入栈中
        while (cur!=null){
            stack.push(cur);
            cur=cur.next; //将cur后移,这样就可以压入下一个节点
        }
        //将栈中的节点进行打印pop出栈
        while (stack.size()>0){
            System.out.println(stack.pop()); //stack的特点是先进后厨
        }
    }



}

//定义SingleLinkedList管理我们的英雄
class SingleLinkedList{
    //先创建一个头节点,头节点不要动
    private HeroNode head=new HeroNode(0,"","");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }

    //添加节点到单向链表
    //思路,当不考虑编号顺序
    //1.找到当前链表的最后节点
    //2.将最后这个节点的next,指向新的链表
    public void add(HeroNode heroNode){
        //因为head节点不能动,因此我们需要一个辅助遍历temp
        HeroNode temp=head;

        //遍历链表,找到最后
        while (true){

            //找到链表的最后
            if(temp.next==null){
                break;
            }
            //如果没有找到最后,就将temp后移
            temp=temp.next;
        }
        //当退出while循环时,temp就指向了链表的最后
        //将最后这个 节点的next指向新的节点
        temp.next=heroNode;
    }

    //第二种添加人物按照顺序添加到指定的位置
    public  void  addByOrder(HeroNode heroNode){
        //头节点不能动,因此我们任然需要通过辅助指针
        //因为单链表,我们找的temp是位于添加位置的前一个节点,否则加入失败
        HeroNode temp=head;

        boolean flag=false; //标识添加的编号是否存在,默认为false;
        while (true){
            if(temp.next==null){
                //说明temp已经在链表的最后
                break;
            }
            if(temp.next.no>heroNode.no){ //位置找到,就在temp的后面插入
                break;
            }else if(temp.next.no==heroNode.no){ //说明希望添加的heroNode的编号已经存在
                flag=true; //说明编号存在
                break;
            }
            temp=temp.next; //后移,遍历当前链表
        }
        //判断flag的值
        if(flag){ //不能添加,说明编号已经存在
            System.out.println("准备插入的人的编号已经存在,不能加入,"+heroNode.no);
        }else{
            //插入到链表中,temp的后边
            heroNode.next=temp.next;
            temp.next=heroNode;

        }
    }

    //删除节点
    //思路
    //1.head节点不能动,因此需要一个temp辅助节点,找到待删除节点的前一个节点
    //2.说明我们在比较时,是temp.next.no和要删除的节点的no比较
    public void del(int no){
        HeroNode temp=head;
        boolean flag=false; //标识是否找到待删除节点
        while(true){
            if(temp.next==null){
                //已经到链表的最后
                break;
            }
            if(temp.next.no==no){
                //找到待删除节点的前一个节点
                flag=true;
                break;
            }
            temp=temp.next;
        }
        //判断flag
        if(flag){ //找到节点,可以删除
            temp.next=temp.next.next;
        }else {
            System.out.printf("要删除的%d节点,不存在\n",no);
        }
    }


    //修改节点的信息,根据编号来修改,即no编号不能改
    //说明:根据newHeroNode的no来修改
    public void update(HeroNode newHeroNode){

        //判断是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的链表,根据no编号
        //定义一个辅助变量
        HeroNode temp=head.next;
        boolean flag=false; //表示是否找到改节点
        while(true){
            if(temp==null){
                break;//到链表的最后的下一个(表示链表已经遍历完)
            }
            if(temp.no==newHeroNode.no){
                //找到了
                flag=true;
                break;
            }
            temp=temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if(flag){
            temp.name=newHeroNode.name;
            temp.nickname=newHeroNode.nickname;

        }else{ //没有找到
            System.out.printf("没有找到编号%d的节点\n",newHeroNode.no);
        }
    }


    //显示链表[遍历]
    public void list(){
        //判断链表是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return ;
        }

        //因为头节点,不能动,因次需要一个辅助变量来遍历
        HeroNode temp=head.next;
        while (true){

            //判断链表是否到最后
            if(temp==null){
                break;
            }
            //输出节点的信息
            System.out.println(temp.toString());
            //将temp后移
            temp=temp.next;
        }
    }



}


//定义一个heroNode,每个heroNode对象就是一个节点
class HeroNode{

    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点

    //构造器

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
