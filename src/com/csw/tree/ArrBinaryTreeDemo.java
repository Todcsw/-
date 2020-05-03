package com.csw.tree;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/24 15:40 星期五
 * @Description: com.csw.tree 顺序存储二叉树 (通常只考虑完全二叉树)
 * @version: 1.0
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.postOrder();
    }
}

/**
 * Description:编写一个ArrayBinaryTree,实现顺序存储二叉树遍历
 *
 * @author Todcsw
 * @date
 */
class ArrBinaryTree{
    private int[] arr;//存储数据结点的数组

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }
    //重载preOrder
    public void preOrder(){
        this.preOrder(0);
    }
    //重载infixOrder
    public void infixOrder(){
        this.infixOrder(0);
    }
    //重载postOrder
    public void postOrder(){
        this.postOrder(0);
    }



    /**
     *编写一个方法,完成顺序存储二叉树的前序遍历
     * @param index 数组的下标
     */
    public void preOrder(int index){
        //如果数组为空,或者arr.length=0
        if(arr==null||arr.length==0){
            System.out.println("数组为空,不能按照二叉树的前序遍历");
        }

        //输出当前的这个元素
        System.out.println(arr[index]);
        //向左递归遍历
        if((index*2+1)<arr.length){
            preOrder(2*index+1);
        }
        //向右递归遍历
        if((index*2+2)<arr.length){
            preOrder(2*index+2);
        }
    }


    /**
     *编写一个方法,完成顺序存储二叉树的中序遍历
     * @param index 数组的下标
     */
    public void infixOrder(int index){
        //如果数组为空,或者arr.length=0
        if(arr==null||arr.length==0){
            System.out.println("数组为空,不能按照二叉树的前序遍历");
        }
        //向左递归遍历
        if((index*2+1)<arr.length){
            infixOrder(2*index+1);
        }
        //输出当前的这个元素
        System.out.println(arr[index]);
        //向右递归遍历
        if((index*2+2)<arr.length){
            infixOrder(2*index+2);
        }
    }

    /**
     *编写一个方法,完成顺序存储二叉树的中序遍历
     * @param index 数组的下标
     */
    public void postOrder(int index){
        //如果数组为空,或者arr.length=0
        if(arr==null||arr.length==0){
            System.out.println("数组为空,不能按照二叉树的前序遍历");
        }
        //向左递归遍历
        if((index*2+1)<arr.length){
            postOrder(2*index+1);
        }
        //向右递归遍历
        if((index*2+2)<arr.length){
            postOrder(2*index+2);
        }
        //输出当前的这个元素
        System.out.println(arr[index]);
    }
}