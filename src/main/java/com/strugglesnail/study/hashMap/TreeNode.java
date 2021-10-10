package com.strugglesnail.study.hashMap;


/**
 * @auther strugglesnail
 * @date 2020/11/16 19:40
 * @desc 红黑树
 */
public class TreeNode<V> {

    private TreeNode<V> parent;  // red-black tree links
    private TreeNode<V> left;
    private TreeNode<V> right;
    private TreeNode<V> prev;    // needed to unlink next upon deletion
    private boolean red;
    V value;

    public TreeNode(V value) {
        this(null, null, null, null, false, value);

    }

    public TreeNode(boolean red, V value) {
        this(null, null, null, null, red, value);
    }



    public TreeNode(TreeNode<V> parent, TreeNode<V> left, TreeNode<V> right, TreeNode<V> prev, boolean red, V value) {
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.prev = prev;
        this.red = red;
        this.value = value;
    }

    public static void main(String[] args) {
        TreeNode<Integer> nodeLeft1 = new TreeNode<>(1);
        TreeNode<Integer> nodeRight3 = new TreeNode<>(3);
        TreeNode<Integer> nodeRoot2 = new TreeNode<>(null, nodeLeft1, nodeRight3, null, false, 2);

    }
}
