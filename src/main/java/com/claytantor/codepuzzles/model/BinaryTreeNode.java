package com.claytantor.codepuzzles.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by claytongraham on 6/16/15.
 */
public class BinaryTreeNode {


    private Integer id;

    @JsonIgnore
    private Integer depth;

    private BinaryTreeNode left;
    private BinaryTreeNode right;

    @JsonIgnore
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
    @JsonIgnore
    public BinaryTreeNode getParent() {
        return parent;
    }

    @JsonProperty
    public void setParent(BinaryTreeNode parent) {
        this.parent = parent;
    }

    @JsonIgnore
    public Integer getDepth() {
        return depth;
    }

    @JsonProperty
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
