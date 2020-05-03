package com.csw.huffmantree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/27 13:51 星期一
 * @Description: com.csw.huffmantree 赫夫曼树
 * @version: 1.0
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node node = createHuffmanTree(arr);
        preOrder(node);

    }

    //编写一个前序遍历的方法
    public static void preOrder(Node root){
        if(root!=null){
            root.preOrder();
        }else{
            System.out.println("树是空树");
        }
    }



    /**
     *  创建赫夫曼树
     * @param arr 需要创建哈夫曼树的数组
     * @return 创建后的赫夫曼树的root结点
     */
    public static Node createHuffmanTree(int[] arr) {
        //第一步为了操作方便
        //1遍历arr数组
        //2.将arr的每个元素构成一个Node
        //3.将Node放入到ArrayList中
        List<Node> nodes = new ArrayList<Node>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }
        while (nodes.size() > 1) {
            //排序从小到大
            Collections.sort(nodes);
            System.out.println("nodes="+nodes);
            //取出权值最小的结点(二叉树)
            Node leftNode = nodes.get(0);
            //取出第二小
            Node rightNode = nodes.get(1);
            //构建新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将parent加入到nodes
            nodes.add(parent);
        }
        //返回哈夫曼树树的root结点
        return nodes.get(0);
    }
}

/**
 * Description: 为了让node对象持续排序Collection集合排序
 * 让Node实现comparable接口
 * 创建结点类
 *
 * @author Todcsw
 * @date
 */
class Node implements Comparable<Node> {
    int value;  //结点权值
    Node left;  //指向左子结点
    Node right; //指向右子结点

    //写一个前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node[" +
                "value=" + value +
                ']';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大
        return this.value - o.value;
    }
}
