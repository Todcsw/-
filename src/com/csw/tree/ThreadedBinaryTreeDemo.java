package com.csw.tree;

/**
 * @Auther: 行路
 * @Date: Created on 2020/4/25 14:19 星期六
 * @Description: com.csw.tree 线索二叉树(遍历线索二叉树)
 * @version: 1.0
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试中序线索二叉树的功能
        HeroNode1 root = new HeroNode1(1, "tom");
        HeroNode1 node2 = new HeroNode1(3, "jack");
        HeroNode1 node3 = new HeroNode1(6, "smith");
        HeroNode1 node4 = new HeroNode1(8, "mary");
        HeroNode1 node5 = new HeroNode1(10, "king");
        HeroNode1 node6 = new HeroNode1(14, "dim");
        //后续会递归创建,目前手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //测试:以10结点测试 左3右1

        System.out.println("前驱结点:" + node5.getLeft() + "\n后继结点:" + node5.getRight());

        System.out.println("使用线索化的方式遍历线索化二叉树");
        threadedBinaryTree.threadedList();
    }
}

/**
 * Description: 定义BinaryTree1二叉树
 * 定义ThreadedBinaryTree实现了线索化功能的二叉树
 *
 * @author Todcsw
 * @date
 */
class ThreadedBinaryTree {
    private HeroNode1 root;

    //为了实现线索化,需要创建要给指向当前结点的前驱结点的指针
    
    //递归进行线索化,pre总是保留前一个结点
    private HeroNode1 pre = null;

    public void setRoot(HeroNode1 root) {
        this.root = root;
    }

    public void threadedNodes() {
        this.threadNodes(root);
    }

    //遍历线索化二叉树的方法
    public void threadedList() {
        //定义变量,存储当前遍历的结点,从root开始
        HeroNode1 node = root;
        while (node != null) {
            //循环的找到leftType==1结点,第一个找到的是8结点
            //后面随着遍历而变化,因为leftType==1时,说明该结点时按照线索化
            //处理后的有效结点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            //打印当前这个结点
            System.out.println(node);
            //如果当前结点的右指针指向的是后继结点,就一直输出
            while (node.getRightType()==1){
                node=node.getRight();
                System.out.println(node);
            }
            //替换这个遍历的结点
            node=node.getRight();
        }
    }


    //编写对二叉树进行中序线索化的方法、

    /**
     * @param node 就是当前需要线索化的结点
     */
    public void threadNodes(HeroNode1 node) {
        //如果node=null，不能线索化
        if (node == null) {
            return;
        }
        //先线索化左子树
        threadNodes(node.getLeft());
        //线索化当前结点,处理当前结点的前驱结点
        //以8号结点 .left=null,8结点的.leftType=1
        if (node.getLeft() == null) {
            //让当前结点的左子针指向前驱结点
            node.setLeft(pre);
            //修改当前结点的左指针类型,指向前驱结点
            node.setLeftType(1);
        }

        //处理后继结点
        if (pre != null && pre.getRight() == null) {
            //让前驱结点的右指针指向当前这个结点
            pre.setRight(node);
            //修改前驱结点的右指针类型
            pre.setRightType(1);
        }
        //!!!每处理一个结点后,让当前结点是下一个结点的前驱结点
        pre = node;
        //在线索化右子树
        threadNodes(node.getRight());
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
     *
     * @param no
     */
    public void delNode(int no) {
        if (root != null) {
            //判断root是不是要删除的
            if (root.getNo() == no) {
                root = null;
            } else {
                //递归删除
                root.delNode(no);
            }
        } else {
            System.out.println("空树不能删除");
        }
    }


    /**
     * 前序查找
     *
     * @param no
     * @return
     */
    public HeroNode1 preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    /**
     * 中序查找
     *
     * @param no
     * @return
     */
    public HeroNode1 infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    /**
     * 后序查找
     *
     * @param no
     * @return
     */
    public HeroNode1 postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }


}


/**
 * Description: 创建HeroNode11节点
 *
 * @author Todcsw
 * @date
 */
class HeroNode1 {
    private int no;
    private String name;
    private HeroNode1 left; //默认为Null
    private HeroNode1 right; //默认null

    //说明leftType==0表示指向的是左子树,如果是1则表示指向前驱结点
    //2.说明rightType==0表示指向的右子树,如果是1则表示指向后继结点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode1(int no, String name) {
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

    public HeroNode1 getLeft() {
        return left;
    }

    public void setLeft(HeroNode1 left) {
        this.left = left;
    }

    public HeroNode1 getRight() {
        return right;
    }

    public void setRight(HeroNode1 right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode11{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }


    /**
     * 递归删除节点
     * 如果删除的节点是叶子节点,则删除该节点
     * 如果删除的节点是非叶子节点,则删除该子树
     * 1.因为我们的二叉树是单向的,所以我们是判断当前节点的子节点是否需要删除的节点,而不能去判断,当前这个结点是不是需要删除的结点
     * 2.如果当前结点的左子结点不为空,并且左子结点就是要删除的结点,就将this.left=null;并且就返回(结束递归删除)
     * 3.如果当前结点的右子结点不为空,并且右子结点就是要删除的结点,就将this.right=null;并且就返回(结束递归删除)
     * 4.如果第2和3步没有删除结点,那么晚我们需要向左子树就行递归删除
     * 5.如果第4步也没有删除结点,则应当向右子树进行递归删除
     *
     * @param no
     */

    public void delNode(int no) {

        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
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
     *
     * @param no 查找no
     * @return 如果找到就返回该node，如果没有找到就返回Null
     */
    public HeroNode1 preOrderSearch(int no) {
        System.out.println("进入前序遍历");
        //比较当前节点是不是
        if (this.no == no) {
            return this;
        }
        //则判断当前节点的左子节点是否为空,如果不为空,则递归前序查找
        HeroNode1 resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            //说明左子树找到了
            return resNode;
        }

        //1左递归前序查找,找到节点,则返回,否则继续判断
        //2当前的结点的右子节点是否为空,如果不空,则继续向右递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }


    /**
     * 中序遍历查找
     *
     * @param no
     * @return
     */
    public HeroNode1 infixOrderSearch(int no) {
        //判断当前节点的左子节点是否为空,如果不为空,则递归中序查找
        HeroNode1 resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入中序遍历");
        if (this.no == no) {
            return this;
        }

        //向右进行中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;

    }

    /**
     * 后续遍历查找
     *
     * @param no
     * @return
     */
    public HeroNode1 postOrderSearch(int no) {

        HeroNode1 resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入后序遍历");
        if (this.no == no) {
            return this;
        }
        return resNode;
    }
}

