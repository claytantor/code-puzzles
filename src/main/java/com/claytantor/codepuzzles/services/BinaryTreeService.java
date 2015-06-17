package com.claytantor.codepuzzles.services;

import com.claytantor.codepuzzles.compare.BinaryTreeNodeDepthComparator;
import com.claytantor.codepuzzles.model.BinaryTreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.*;

/**
 * Created by claytongraham on 6/16/15.
 */

@Component
public class BinaryTreeService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    public String getMessage(){
        return "BinaryTreeService active";
    }

    /**
     * Given a root binary find the minimum number of common ancestors.
     *
     * @param root
     * @param nodeId1
     * @param nodeId2
     * @return
     */
    public BinaryTreeNode findMinimumCommonAncestors(
            BinaryTreeNode root, Integer nodeId1, Integer nodeId2){

        //find the nodes by id
        BinaryTreeNode node1 = searchNodeById(root, nodeId1, null, root);
        BinaryTreeNode node2 = searchNodeById(root, nodeId2, null, root);

        //find the list of all ancestors for each node
        List<BinaryTreeNode> ancestors = new ArrayList<BinaryTreeNode>();
        findAncestorsForNode(node1, ancestors);
        findAncestorsForNode(node2, ancestors);

        // filter nodes that are not common ancestors
        Set<BinaryTreeNode> commonAncestors = findCommonAncestors(node1, node2, ancestors);

        // find least (lowest level) common ancestor, this should be
        // the common ancestor with the greatest depth
        return findMaxDepth(commonAncestors);
    }

    /**
     * will return a null value if a match cant be found. You have to search
     * both ways or it will get lost on the right or left directions. Note: We
     * have added the set parent as a convenience so we don't have to traverse down
     * a second time.
     *
     * @param node
     * @param nodeId
     * @return
     */
    public BinaryTreeNode searchNodeById(BinaryTreeNode node, Integer nodeId, BinaryTreeNode parent, BinaryTreeNode root){

        // always set parent if it exists, otherwise the top node
        // will be missing
        if(parent != null){
            log.debug(MessageFormat.format(
                    "node id:{0} parent id:{1}", node.getId(), parent.getId()));
            node.setParent(parent);
            node.setDepth(findDepthFromRoot(node,root));
        } else {
            //is root node
            node.setDepth(0);
        }

        if(node.getId().equals(nodeId)) {
            return node;
        } else if (node.getLeft() != null) {
            BinaryTreeNode nodeLeft = searchNodeById(node.getLeft(), nodeId, node, root);
            if(nodeLeft == null){
                return searchNodeById(node.getRight(),nodeId, node, root);
            } else {
                return nodeLeft;
            }
        } else if (node.getRight() != null) {
            BinaryTreeNode nodeRight = searchNodeById(node.getRight(),nodeId, node, root);
            if(nodeRight == null){
                return searchNodeById(node.getLeft(), nodeId, node, root);
            } else {
                return nodeRight;
            }
        } else {
            return null;
        }
    }

    /**
     * Reverse traversal (up). relies on reference parameter to recurse, the client uses the
     * result of the traversal to add items to the list. WARNING: this
     * assumes that the parent has been set in a previous traversal
     *
     * @param node
     * @param ancestors
     * @return
     */
    public void findAncestorsForNode (
            BinaryTreeNode node, List<BinaryTreeNode> ancestors){

        log.debug(MessageFormat.format("node id:{0} ancestors size:{1}", node.getId(), ancestors.size()));

        //stop at root node
        if(node.getParent() != null) {
            log.debug(MessageFormat.format(
                    "node id:{0} parent id:{1} ancestors size:{2}",
                    node.getId(),
                    node.getParent().getId(),
                    ancestors.size()));
            ancestors.add(node.getParent());
            findAncestorsForNode(node.getParent(), ancestors);
        } else {
            log.debug(MessageFormat.format(
                    "node id:{0} has no parent",
                    node.getId()));
        }

    }

//


    /**
     *
     * While traversing the node tree compute the depth, this can be used later
     * to filter out the "least" common ancestor, which means the common ancestor with
     * the greatest distance from the root. Assumes that the parent has been set.
     *
     * @param node
     * @param root
     *
     */
    public Integer findDepthFromRoot (
            BinaryTreeNode node, BinaryTreeNode root){
        Integer depth = 0;
        while (!node.equals(root)){
            node = node.getParent();
            depth += 1;
        }
        return depth;
    }

    /**
     * Given a list of node ancestors, identify which ones are common
     * and return them as a subset
     *
     * @param ancestors
     * @return
     */
    public Set<BinaryTreeNode> findCommonAncestors (
            BinaryTreeNode node1, BinaryTreeNode node2, List<BinaryTreeNode> ancestors){

        Set<BinaryTreeNode> commonAncestors = new HashSet<>();
        for(BinaryTreeNode ancestor:ancestors){
            if(isAncestor(node1,ancestor) && isAncestor(node2,ancestor))
                commonAncestors.add(ancestor);
        }

        return commonAncestors;
    }

    /**
     * traverse up the path to see if the test node is a parent, if there
     * is no parent then we have reached the root node
     *
     * @param node
     * @param testNode
     * @return
     */
    public boolean isAncestor(BinaryTreeNode node, BinaryTreeNode testNode){
        if(node.getParent() != null && node.getParent().equals(testNode))
            return true;
        else if (node.getParent() == null)
            return false;
        else
            return isAncestor(node.getParent(), testNode);
    }


    /**
     * Given a list of common ancestors find the "least", (the lowest level child)
     * that is common.
     *
     * @param nodes
     * @return
     */
    public BinaryTreeNode findMaxDepth (
            Set<BinaryTreeNode> nodes){
        return Collections.max(nodes, new BinaryTreeNodeDepthComparator());
    }


}
