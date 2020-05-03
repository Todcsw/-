package com.csw.tree;

/**
 * @Auther: 行路
 * @Date: Created on 2020/5/1 14:12 星期五
 * @Description: com.csw.tree 平衡二叉树
 * @version: 1.0
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {10, 12, 8, 9, 7, 6};

        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        //添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node1(arr[i]));
        }

        //中序遍历
        avlTree.infixOrder();

        System.out.println("没有平衡之前");
        System.out.println("树的高度=" + avlTree.getRoot().height());
        System.out.println("树的右子树高度=" + avlTree.getRoot().leftHeight());
        System.out.println("书的右子树的高度=" + avlTree.getRoot().rightHeight());
        System.out.println("当前根结点" + avlTree.getRoot());

        System.out.println("根结点的左子结点"+avlTree.getRoot().right.left);
    }
}

//创建AVL树
class AVLTree {
    private Node1 root;

    public Node1 getRoot() {
        return root;
    }

    public void setRoot(Node1 root) {
        this.root = root;
    }

    //查找要删除的结点
    public Node1 search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父结点
    public Node1 searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }


    /**
     * 方法:返回以Node1为根结点的二叉排序树的最小结点的值
     * 2.删除Node1,为根结点的二叉排序树的最小结点
     *
     * @param Node1 传入的结点,当作一个二叉排序树的根结点
     * @return 返回的以Node1为根结点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(Node1 Node1) {
        Node1 target = Node1;
        //循环的查找左结点，找到最小的结点
        while (target.left != null) {
            target = target.left;
        }
        //这时target就指向了最小结点
        //删除最小结点
        delNode1(target.value);
        return target.value;
    }

    //删除结点
    public void delNode1(int value) {
        if (root == null) {
            return;
        } else {
            //1.需要先去找到要删除的结点 targetNode1
            Node1 targetNode1 = search(value);
            //如果没有找到要删除的结点
            if (targetNode1 == null) {
                return;
            }
            //如果我们发现当前这颗二叉排序树只有一个结点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            //去查找targetNode1的父结点
            Node1 parent = searchParent(value);
            //如果要删除的结点是叶子结点
            if (targetNode1.left == null && targetNode1.right == null) {
                //判断targetNode1是父结点的左子结点还是右子结点
                if (parent.left != null && parent.left.value == value) { //左子结点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) { //右子结点
                    parent.right = null;
                }
            } else if (targetNode1.left != null && targetNode1.right != null) {
                //删除有两颗子树的节点
                int minVal = delRightTreeMin(targetNode1.right);
                targetNode1.value = minVal;
            } else {
                //删除只有一棵子树的结点
                //如果要删除的结点有左子结点
                if (targetNode1.left != null) {
                    if (parent != null) {
                        //如果targetNode1是parent的左子结点
                        if (parent.left.value == value) {
                            parent.left = targetNode1.left;
                        } else {
                            //target是parent的右子结点
                            parent.right = targetNode1.left;
                        }
                    } else {
                        root = targetNode1.left;
                    }
                } else { //如果要删除的结点有右子结点
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode1.right;
                        } else {
                            //如果targetNode1是parent的右子结点
                            parent.right = targetNode1.right;
                        }
                    } else {
                        root = targetNode1.right;
                    }
                }
            }
        }
    }

    //添加节点的方法
    public void add(Node1 Node1) {
        if (root == null) {
            root = Node1;  //如果root为空值,直接让root指向Node1节点
        } else {
            root.add(Node1);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空");
        }
    }
}


//创建Node1节点
class Node1 {
    int value;
    Node1 left;
    Node1 right;

    public Node1(int value) {
        this.value = value;
    }

    //返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }


    //返回当前节点的高度,以该结点为根节点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋转方法
    private void leftRotate() {
        //创建新的结点,以当前结点的值
        Node1 newNode = new Node1(value);
        //把新的结点的左子树设置成当前结点的左子树
        newNode.left = left;
        //把更新的结点的右子树设置成带你过去结点的右子树
        newNode.right = right.left;
        //把当前结点的值替换成右子结点的值
        value = right.value;
        //把当前结点的右子树设置成右子树的右子树
        right = right.right;
        //把当前结点的左子树(左子结点)设置成新的结点
        left = newNode;
    }

    //右旋转
    private void rightRotate() {
        Node1 newNode = new Node1(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    /**
     * 查找要删除的结点
     *
     * @param value 希望删除结点的值
     * @return 如果找到返回该结点的值.否则返回Null
     */
    public Node1 search(int value) {
        if (value == this.value) {
            //找到就是该点
            return this;
        } else if (value < this.value) {
            //如果查找的值小于当前结点,向左子树递归查找
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        } else {
            //如果查找的值不小于当前结点,向右子树递归查找
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }


    /**
     * 查找要删除结点的父结点
     *
     * @param value 要找的结点的值
     * @return 返回的时要删除结点的父结点, 如果没有就返回null
     */
    public Node1 searchParent(int value) {
        //如果当前结点就是要删除结点的父结点,就返回
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //如果查找的值小于当前结点的值,并且当前结点的左子结点不为空
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null; //没有找到父结点
            }
        }
    }

    @Override
    public String toString() {
        return "Node1{" +
                "value=" + value +
                '}';
    }

    /**
     * 添加结点方法
     * 递归的形式添加结点,需要满足二叉排序树
     *
     * @param Node1
     */
    public void add(Node1 Node1) {
        if (Node1 == null) {
            return;
        }
        //判断传入的节点的值,和当前根节点的值关系
        if (Node1.value < this.value) {
            //当前节点的左子节点为null
            if (this.left == null) {
                this.left = Node1;
            } else {
                //递归的向左子树添加
                this.left.add(Node1);
            }
        } else { //添加的节点的值大于当前节点的值
            if (this.right == null) {
                this.right = Node1;
            } else {
                this.right.add(Node1);
            }
        }
        //当添加完一个结点后,如果右子树的高度比左子树的高度>1 发生左旋转
        if (rightHeight() - leftHeight() > 1) {
            //如果它的右子树的左子树的高度大于它的右子树的右子树高度
            if (right != null && right.leftHeight() > right.rightHeight()) {
                //先对右子结点进行右旋转
                right.rightRotate();
                //然后在对当前结点进行左旋转
                leftRotate();
            } else {
                //直接进行左旋转
                leftRotate();
            }
            return;
        }
        //当添加完一个结点后,如果左子树的高度-右子树的高度>1 ,右旋转
        if (leftHeight() - rightHeight() > 1) {
            if (left != null && left.rightHeight() > left.leftHeight()) {
                //先对当前结点的左结点 左子树 （右旋转）
                left.leftRotate();
                rightRotate();
            } else {
                //直接进行右旋转
                rightRotate();
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
}