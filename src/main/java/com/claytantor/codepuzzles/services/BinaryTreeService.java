package com.claytantor.codepuzzles.services;

import com.claytantor.codepuzzles.model.BinaryTreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

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
    public List<BinaryTreeNode> searchMinimumCommonAncestors(
            BinaryTreeNode root, Integer nodeId1, Integer nodeId2){

        //find the nodes by id
        BinaryTreeNode node1 = searchNodeById(root, nodeId1, null);
        BinaryTreeNode node2 = searchNodeById(root, nodeId2, null);

        //find the list of all ancestors for each node
        List<BinaryTreeNode> ancestors = new ArrayList<BinaryTreeNode>();
        findAncestorsForNode(node1, ancestors);
        findAncestorsForNode(node2, ancestors);

        // add the ids to a common set

        //reduce the set

        //get nodes by id
            //add to return list

        return null;
    }

    /**
     * will return a null value if a match cant be found. You have to search
     * both ways or it will get lost on the right or left directions. Note: We
     * have added the set parent as a convenience so we dont have to traverse down
     * a second time.
     *
     * @param node
     * @param nodeId
     * @return
     */
    public BinaryTreeNode searchNodeById(BinaryTreeNode node, Integer nodeId, BinaryTreeNode parent){

        // always set parent if it exists, otherwise the top node
        // will be missing
        if(parent != null){
            log.debug(MessageFormat.format("node id:{0} parent id:{1}", node.getId(), parent.getId()));
            node.setParent(parent);
        }

        if(node.getId().equals(nodeId)) {
            return node;
        } else if (node.getLeft() != null) {
            BinaryTreeNode nodeLeft = searchNodeById(node.getLeft(), nodeId, node);
            if(nodeLeft == null){
                return searchNodeById(node.getRight(),nodeId, node);
            } else {
                return nodeLeft;
            }
        } else if (node.getRight() != null) {
            BinaryTreeNode nodeRight = searchNodeById(node.getRight(),nodeId, node);
            if(nodeRight == null){
                return searchNodeById(node.getLeft(), nodeId, node);
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





}
