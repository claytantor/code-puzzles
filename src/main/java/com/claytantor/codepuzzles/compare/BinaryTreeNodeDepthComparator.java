package com.claytantor.codepuzzles.compare;

import com.claytantor.codepuzzles.model.BinaryTreeNode;

import java.util.Comparator;

/**
 * Created by claytongraham on 6/17/15.
 */
public class BinaryTreeNodeDepthComparator implements Comparator<BinaryTreeNode> {

    @Override
    public int compare(BinaryTreeNode o1, BinaryTreeNode o2) {
        return o1.getDepth().compareTo(o2.getDepth());
    }
}
