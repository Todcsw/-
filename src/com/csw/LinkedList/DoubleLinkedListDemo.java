package com.csw.LinkedList;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/14 20:11 星期二
 * @Description: com.csw.LinkedList
 * @version: 1.0
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表的测试");
        //创建节点
        HeroNode2 hero1 = new HeroNode2(1, "松江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢", "玉");
        HeroNode2 hero3 = new HeroNode2(3, "无用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        //创建一个双向链表
        DoubleLinkedList linkedList=new DoubleLinkedList();
        //添加
        linkedList.add(hero1);
        linkedList.add(hero2);
        linkedList.add(hero3);
        linkedList.add(hero4);

        //显示
        linkedList.list();
        //修改
        HeroNode2 node2=new HeroNode2(4,"公孙胜","入云龙");
        linkedList.update(node2);
        //显示
        System.out.println("修改后的链表");
        linkedList.list();

        //删除
        linkedList.del(3);
        System.out.println("删除后的链表");
        linkedList.list();
    }
}

//创建一个双向链表的类
class DoubleLinkedList {
    //初始化一个头节点,头节点不动,不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    /**
     * 遍历双向链表的方法
     * 显示链表[遍历]
     */
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点,不能动,因次需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while (true) {

            //判断链表是否到最后
            if (temp == null) {
                break;
            }
            //输出节点的信息
            System.out.println(temp.toString());
            //将temp后移
            temp = temp.next;
        }
    }

    /**
     * 添加一个节点到双向链表的最后
     *
     * @param heroNode
     */
    public void add(HeroNode2 heroNode) {
        //因为head节点不能动,因此我们需要一个辅助遍历temp
        HeroNode2 temp = head;

        //遍历链表,找到最后
        while (true) {

            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后,就将temp后移
            temp = temp.next;
        }
        //当退出while循环时,temp就指向了链表的最后
        //形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    /**
     * 修改一个节点的内容,可以看到几乎和单链表一样
     * 节点的类型
     *
     * @param newHeroNode
     */
    public void update(HeroNode2 newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的链表,根据no编号
        //定义一个辅助变量
        HeroNode2 temp = head.next;
        boolean flag = false; //表示是否找到改节点
        while (true) {
            if (temp == null) {
                break;//到链表的最后的下一个(表示链表已经遍历完)
            }
            if (temp.no == newHeroNode.no) {
                //找到了
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;

        } else { //没有找到
            System.out.printf("没有找到编号%d的节点\n", newHeroNode.no);
        }
    }

    /**
     * 从双向链表中删除一个节点
     * 对于双向链表,可以直接找到要删除的节点,找到后自我删除
     */
    public void del(int no) {

        //判断当前链表是否为空
        if (head.next == null) {
            System.out.println("链表为空,无法删除");
            return;
        }

        HeroNode2 temp = head.next; //辅助变量
        boolean flag = false; //标识是否找到待删除节点
        while (true) {
            if (temp == null) {
                //已经到链表的最后
                break;
            }
            if (temp.no == no) {
                //找到待删除节点的前一个节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //判断flag
        if (flag) { //找到节点,可以删除
            temp.pre.next = temp.next;
            //如果是最后一个节点,就不需要指向
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的%d节点,不存在\n", no);
        }
    }

}

//定义一个heroNode,每个heroNode对象就是一个节点
class HeroNode2 {

    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; //指向下一个节点,默认为null
    public HeroNode2 pre;  //指向前一个节点,默认为null

    //构造器

    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", next=" + next +
                '}';
    }
}


