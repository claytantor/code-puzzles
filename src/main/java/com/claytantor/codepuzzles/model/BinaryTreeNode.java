package com.claytantor.codepuzzles.model;

/**
 * Created by claytongraham on 6/16/15.
 */
public class BinaryTreeNode {


    private Integer id;
    private Integer depth;
    private BinaryTreeNode left;
    private BinaryTreeNode right;
    private BinaryTreeNode parent;

    public BinaryTreeNode() {
    }

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

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }


    /* ----------- set post constructor as part of traversal -----------*/
    public BinaryTreeNode getParent() {
        return parent;
    }

    public void setParent(BinaryTreeNode parent) {
        this.parent = parent;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    /**
     * Custom equals to insure that the sets and comparisions
     * are based on Id
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        BinaryTreeNode compareTo = (BinaryTreeNode)obj;
        return getId().equals(compareTo.getId());
    }
}
