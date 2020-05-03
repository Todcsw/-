package com.csw.tree;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/23 17:00 星期四
 * @Description: com.csw.tree 二叉树的前序中序后序遍历和查找及删除
 * @version: 1.0
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode root=new HeroNode(1,"宋江");
        HeroNode node2=new HeroNode(2,"吴用");
        HeroNode node3=new HeroNode(3,"卢俊义");
        HeroNode node4=new HeroNode(4,"林冲");
        HeroNode node5=new HeroNode(5,"关胜");
        //说明,我们先手动创建二叉树,后面会递归方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

        //测试
        System.out.println("前序遍历"); //
        binaryTree.preOrder();

        System.out.println("中序遍历"); //
        binaryTree.infixOrder();

        System.out.println("后序遍历"); //
        binaryTree.postOrder();

        System.out.println("前序查找~~~");
        HeroNode resNode=binaryTree.postOrderSearch(5);
        if(resNode!=null){
            System.out.println("找到了信息为:"+resNode.getNo()+resNode.getName());
        }else{
            System.out.println("找不到");
        }

        System.out.println("测试删除结点");
        binaryTree.preOrder();
        binaryTree.delNode(5);
        System.out.println("删除后的结点");
        binaryTree.preOrder();
    }
}


/**
 * Description: 定义BinaryTree二叉树
 *
 * @author Todcsw
 * @date
 */
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }


    /**
     * 前序遍历
     */
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空无法遍历");
        }
    }


    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空无法遍历");
        }
    }


    /**
     * 后序遍历
     */
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空无法遍历");
        }
    }

    /**
     * 递归删除
     * @param no
     */
    public void delNode(int no){
        if(root!=null){
            //判断root是不是要删除的
            if(root.getNo()==no){
                root=null;
            }else{
                //递归删除
                root.delNode(no);
            }
        }else{
            System.out.println("空树不能删除");
        }
    }


    /**
     * 前序查找
     * @param no
     * @return
     */
    public HeroNode preOrderSearch(int no){
        if(root!=null){
            return root.preOrderSearch(no);
        }else{
            return null;
        }
    }
    /**
     * 中序查找
     * @param no
     * @return
     */
    public HeroNode infixOrderSearch(int no){
        if(root!=null){
            return root.infixOrderSearch(no);
        }else{
            return null;
        }
    }
    /**
     * 后序查找
     * @param no
     * @return
     */
    public HeroNode postOrderSearch(int no){
        if(root!=null){
            return root.postOrderSearch(no);
        }else{
            return null;
        }
    }




}


/**
 * Description: 创建HeroNode节点
 *
 * @author Todcsw
 * @date
 */
class HeroNode {
    private int no;
    private String name;
    private HeroNode left; //默认为Null
    private HeroNode right; //默认null

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }




    /**
     *递归删除节点
     *如果删除的节点是叶子节点,则删除该节点
     *如果删除的节点是非叶子节点,则删除该子树
     * 1.因为我们的二叉树是单向的,所以我们是判断当前节点的子节点是否需要删除的节点,而不能去判断,当前这个结点是不是需要删除的结点
     * 2.如果当前结点的左子结点不为空,并且左子结点就是要删除的结点,就将this.left=null;并且就返回(结束递归删除)
     * 3.如果当前结点的右子结点不为空,并且右子结点就是要删除的结点,就将this.right=null;并且就返回(结束递归删除)
     * 4.如果第2和3步没有删除结点,那么晚我们需要向左子树就行递归删除
     * 5.如果第4步也没有删除结点,则应当向右子树进行递归删除
     * @param no
     */

    public void delNode(int no){

        if(this.left!=null&&this.left.no==no){
            this.left=null;
            return;
        }
        if(this.right!=null&&this.right.no==no){
            this.right=null;
            return;
        }
        if(this.left!=null){
            this.left.delNode(no);
        }
        if(this.right!=null){
            this.right.delNode(no);
        }
    }



    /**
     * 编写前序遍历的方法
     */
    public void preOrder() {
        System.out.println(this); //先输出父节点
        //递归向左子树遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 编写中序遍历的方法
     */
    public void infixOrder() {
        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 编写后序遍历的方法
     */
    public void postOrder() {
        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.postOrder();
        }

        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }


    /**
     * 前序遍历查找
     * @param no 查找no
     * @return 如果找到就返回该node，如果没有找到就返回Null
     */
    public HeroNode preOrderSearch(int no){
        System.out.println("进入前序遍历");
        //比较当前节点是不是
        if(this.no==no){
            return this;
        }
        //则判断当前节点的左子节点是否为空,如果不为空,则递归前序查找
        HeroNode resNode=null;
        if(this.left!=null){
            resNode=this.left.preOrderSearch(no);
        }
        if(resNode!=null){
            //说明左子树找到了
            return resNode;
        }

        //1左递归前序查找,找到节点,则返回,否则继续判断
        //2当前的结点的右子节点是否为空,如果不空,则继续向右递归前序查找
        if(this.right!=null){
            resNode=this.right.preOrderSearch(no);
        }
        return resNode;
    }


    /**
     * 中序遍历查找
     * @param no
     * @return
     */
    public HeroNode infixOrderSearch(int no){
        //判断当前节点的左子节点是否为空,如果不为空,则递归中序查找
        HeroNode resNode=null;
        if(this.left!=null){
            resNode=this.left.infixOrderSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }
        System.out.println("进入中序遍历");
        if(this.no==no){
            return this;
        }

        //向右进行中序查找
        if(this.right!=null){
            resNode=this.right.infixOrderSearch(no);
        }
        return resNode;

    }

    /**
     * 后续遍历查找
     * @param no
     * @return
     */
    public HeroNode postOrderSearch(int no){

        HeroNode resNode=null;
        if(this.left!=null){
            resNode=this.left.postOrderSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }
        if(this.right!=null){
            resNode=this.right.postOrderSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }
        System.out.println("进入后序遍历");
        if(this.no==no){
            return this;
        }
        return resNode;
    }
}

