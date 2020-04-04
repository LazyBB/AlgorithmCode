package com.company.tree;
/**
 * 根据前序中序重建一颗二叉树
 */


public class rebulidtree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int length=preorder.length;
        if(length<1)
            return null;
        TreeNode node =new TreeNode(preorder[0]);
        if (length==1){
            node.right=null;
            node.left=null;
            return node;
        }
        int root=0;
        for(int i=0;i<inorder.length;i++){
            if (inorder[i]==preorder[0])
                root=i;
        }
        int[] leftpreorder=new int[root];
        int[] leftinorder=new int[root];
        int[] rightpreorder =new int[length-root-1];
        int[] rightinorder =new int[length-root-1];
        for(int i=0;i<root;i++){
            leftpreorder[i]=preorder[i+1];
        }
        for(int i=0;i<root;i++){
            leftinorder[i]=inorder[i];
        }
        for(int i=0;i<length-root-1;i++){
            rightpreorder[i]=preorder[i+root+1];
        }
        for(int i=0;i<length-root-1;i++){
            rightinorder[i]=inorder[i+root+1];
        }
        if (root==0){
            node.left=null;
            node.right=buildTree(rightpreorder,rightinorder);
        }else if (root<length-1){
            node.left=buildTree(leftpreorder,leftinorder);
            node.right=buildTree(rightpreorder,rightinorder);
        }else if (root==length-1){
            node.left=buildTree(leftpreorder,leftinorder);
            node.right=null;
        }
        return node;
    }
}
