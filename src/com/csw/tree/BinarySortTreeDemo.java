package com.csw.tree;

/**
 * @Auther: 行路
 * @Date: Created on 2020/5/1 10:56 星期五
 * @Description: com.csw.tree 二叉排序树
 * @version: 1.0
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加节点到二叉排序树
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        //中序遍历二叉树
        System.out.println("中序遍历二叉树结果");
        binarySortTree.infixOrder();

        System.out.println("测试删除叶子结点");

        binarySortTree.delNode(10);

        binarySortTree.infixOrder();
    }
}

//创建二叉排序树
class BinarySortTree {
    private Node root;

    //查找要删除的结点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }

    //查找父结点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }


    /**
     * 方法:返回以node为根结点的二叉排序树的最小结点的值
     * 2.删除node,为根结点的二叉排序树的最小结点
     *
     * @param node 传入的结点,当作一个二叉排序树的根结点
     * @return 返回的以node为根结点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(Node node) {
        Node target = node;
        //循环的查找左结点，找到最小的结点
        while (target.left != null) {
            target = target.left;
        }
        //这时target就指向了最小结点
        //删除最小结点
        delNode(target.value);
        return target.value;
    }

    //删除结点
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //1.需要先去找到要删除的结点 targetNode
            Node targetNode = search(value);
            //如果没有找到要删除的结点
            if (targetNode == null) {
                return;
            }
            //如果我们发现当前这颗二叉排序树只有一个结点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            //去查找targetNode的父结点
            Node parent = searchParent(value);
            //如果要删除的结点是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是父结点的左子结点还是右子结点
                if (parent.left != null && parent.left.value == value) { //左子结点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) { //右子结点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                //删除有两颗子树的节点
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else {
                //删除只有一棵子树的结点
                //如果要删除的结点有左子结点
                if (targetNode.left != null) {
                    if (parent != null) {
                        //如果targetNode是parent的左子结点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            //target是parent的右子结点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else { //如果要删除的结点有右子结点
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            //如果targetNode是parent的右子结点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    //添加节点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;  //如果root为空值,直接让root指向node节点
        } else {
            root.add(node);
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


//创建node节点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }


    /**
     * 查找要删除的结点
     *
     * @param value 希望删除结点的值
     * @return 如果找到返回该结点的值.否则返回Null
     */
    public Node search(int value) {
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
    public Node searchParent(int value) {
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
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 添加结点方法
     * 递归的形式添加结点,需要满足二叉排序树
     *
     * @param node
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //判断传入的节点的值,和当前根节点的值关系
        if (node.value < this.value) {
            //当前节点的左子节点为null
            if (this.left == null) {
                this.left = node;
            } else {
                //递归的向左子树添加
                this.left.add(node);
            }
        } else { //添加的节点的值大于当前节点的值
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
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
