package com.claytantor.codepuzzles.model;

/**
 * Created by claytongraham on 6/16/15.
 */
public class BinaryTreeNode {

    private Integer id;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode(Integer id, BinaryTreeNode left, BinaryTreeNode right) {
        this.id = id;
        this.left = left;
        this.right = right;
    }

    public Integer getId() {
        return id;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }
}
