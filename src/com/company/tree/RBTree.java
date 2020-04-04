package com.company.tree;


import java.util.Scanner;

/**
 * 红黑树的实现
 * 1、创建RBtree，定义颜色
 * 2、创建Node
 * 3、get/set方法
 * 4、左旋/右旋
 * 5、insert方法以及修复方法 insertflxp
 */

public class RBTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private RBNode root;

    /**
     * 返回父节点
     *
     * @param node
     * @return parentNode
     */
    private RBNode parentof(RBNode node) {
        if (node != null)
            return node.parent;
        return null;
    }

    /**
     * 返回节点颜色
     *
     * @param node
     * @return color
     */
    private boolean isRed(RBNode node) {
        if (node != null) {
            return node.color == RED;
        }
        return false;
    }

    /**
     * 返回节点颜色
     *
     * @param node
     * @return color
     */
    private boolean isBlack(RBNode node) {
        if (node != null) {
            return node.color == BLACK;
        }
        return false;
    }

    /**
     * 设置节点为红色
     *
     * @param node
     */
    private void setRed(RBNode node) {
        if (node != null) {
            node.color = RED;
        }
    }

    /**
     * 设置节点为黑色
     *
     * @param node
     */
    private void setBlack(RBNode node) {
        if (node != null) {
            node.color = BLACK;
        }
    }

    /**
     * 中序遍历
     */
    public void printInorder() {
        if (root != null) {
            printInorder(this.root);
        }
    }

    private void printInorder(RBNode node) {
        if (node != null) {
            printInorder(node.left);
            System.out.println("key:" + node.key + " Valve:" + node.value);
            printInorder(node.right);
        }
    }

    /**
     * 左旋
     *
     * @param x
     */
    private void leftRotate(RBNode x) {
        RBNode y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        if (x.parent != null) {
            y.parent = x.parent;
            if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        } else {
            //parent为空则为根节点
            this.root = y;
            this.root.parent = null;
        }
        y.left = x;
        x.parent = y;
    }

    /**
     * 右旋
     *
     * @param y
     */

    private void rightRotate(RBNode y) {
        RBNode x = y.left;
        y.left = x.right;
        if(x.right != null) {
            x.right.parent = y;
        }

        //2.将y的父节点p（非空时）赋值给x的父节点，同时更新p的子节点为x（左或右）
        x.parent = y.parent;

        if(y.parent != null) {
            if(y.parent.left == y) {
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }
        } else {
            this.root = x;
            this.root.parent = null;//这步很重要
        }

        //3.将x的右子节点赋值为y，将y的父节点设置为x
        x.right = y;
        y.parent = x;
    }

    /**
     * 插入接口
     *
     * @param key
     * @param value
     */
    public void insert(K key, V value) {
        RBNode node = new RBNode(key, value);
        insert(node);
    }

    /**
     * 插入
     *
     * @param node
     */
    private void insert(RBNode node) {
        //首先找到插入位置
        RBNode parent = null;//定位node的父节点
        RBNode x = this.root;
        while (x != null) {
            parent = x;
            //大于向右小于向左等于替换值
            int inc = node.key.compareTo(x.key);
            if (inc > 0) {
                x = x.right;
            } else if (inc == 0) {
                x.value = node.value;
                return;
            } else {
                x = x.left;
            }
        }
        node.parent = parent;
        //父节点不为空，确定node是parent的左节点还是右节点
        if (parent != null) {
            int inc = node.key.compareTo(parent.key);
            if (inc > 0) {
                parent.right = node;
            } else {
                parent.left = node;
            }
        } else {
            //父节点为空，则红黑树为空，node为根节点
            this.root = node;
        }
        //检测红黑树的结构并调整
        insertFixUP(node);
    }

    /**
     * 插入后平衡红黑树
     * 1、红黑树为空
     * 2、插入节点key已存在
     * 3、插入节点的父节点为黑色
     * 4、插入节点父节点为红色
     * 4.1、叔叔节点存在，并为红色
     * 4.2、叔叔节点为null或黑色，且父节点为爷爷节点的左节点
     * 4.2.1、插入节点为父节点的左节点(LL)
     * 4.2.2、插入节点为父节点的右节点 (LR)
     * 4.3、叔叔节点为null或黑色，且父节点为爷爷节点的右节点
     * 4.3.1、插入节点为父节点的左节点(RL)
     * 4.3.2、插入节点为父节点的右节点(RR)
     */
    private void insertFixUP(RBNode node) {
        this.root.setColor(BLACK);//应对情况1。情况2、3均不需调整
        RBNode parent = parentof(node);
        RBNode gparent = parentof(parent);
        //情况4 父节点为红色一定存在爷爷节点
        if (parent != null && isRed(parent)) {
            RBNode uncle = null;
            if (parent == gparent.left) {
                uncle = gparent.right;
                if (uncle != null && isRed(uncle)) {
                    //应对4.1，将父和叔都变成黑色，爷爷节点变成红色,以爷爷节点为当前节点
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    insertFixUP(gparent);
                    return;
                }
                if (uncle==null||isBlack(uncle)){//4.2
                    if (node==parent.left){
                        //4.2.1 (LL) 将父节点变黑，爷爷节点变红，以爷爷节点右旋
                        setBlack(parent);
                        setRed(gparent);
                        rightRotate(gparent);
                        return;
                    }
                    if (node == parent.right){
                        //4.2.2 父节点左旋得到(LL),再以父节点为当前节点进行操作
                        leftRotate(parent);
                        insertFixUP(parent);
                        return;
                    }
                }
            } else {
                uncle = gparent.left;
                if (uncle != null && isRed(uncle)) {
                    //应对4.1，将父和叔都变成黑色，爷爷节点变成红色,以爷爷节点为当前节点
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    insertFixUP(gparent);
                    return;
                }
                if (uncle==null||isBlack(uncle)){
                    if (node==parent.right){
                        //情况4.3.2 RR情况，将父节点变黑，爷爷节点变红，以爷爷节点左旋
                        setRed(gparent);
                        setBlack(parent);
                        leftRotate(gparent);
                        return;
                    }
                    if (node == parent.left) {
                        //情况4.3.1 父节点右旋得到RR，以父节点为当前节点
                        rightRotate(parent);
                        insertFixUP(parent);
                        return;
                    }
                }
            }
        }
    }

    public RBNode getRoot() {
        return this.root;
    }


    static class RBNode<K extends Comparable<K>, V> {
        private RBNode parent;
        private RBNode left;
        private RBNode right;
        private boolean color;
        private K key;
        private V value;

        public RBNode(RBNode parent, RBNode left, RBNode right, boolean color, K k, V v) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.color = color;
            this.key = k;
            this.value = v;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public RBNode() {
        }

        public RBNode(boolean color) {
            this.color = color;
        }

        public RBNode(K key, V value) {
            this.color = RED;
            this.key = key;
            this.value = value;
        }

        public RBNode getParent() {
            return parent;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    public void padding ( String ch, int n ) {
        int i;
        for ( i = 0; i < n; i++ )
            System.out.printf(ch);

    }

    void print_node (RBNode root, int level ) {
        if ( root == null ) {
            padding ( "\t", level );
            System.out.println( "NIL" );

        } else {
            print_node ( root.right, level + 1 );
            padding ( "\t", level );
            if(root.color == BLACK) {
                System.out.printf(root.key + "(" + (root.isColor() ? "红" : "黑") +")" + "\n");
            } else
                System.out.printf(root.key  + "(" + (root.isColor() ? "红" : "黑") +")" + "\n");
            print_node ( root.left, level + 1 );
        }
    }

    public void print_tree() {
        print_node(this.root,0);
        System.out.printf("-------------------------------------------\n");
    }

    public static void main(String[] args) {
        RBTree<String, Object> rbt = new RBTree();
        //测试输入：ijkgefhdabc
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入key:");
            String key = sc.next();

            rbt.insert(key, null);
            rbt.print_tree();
        }
    }
}
