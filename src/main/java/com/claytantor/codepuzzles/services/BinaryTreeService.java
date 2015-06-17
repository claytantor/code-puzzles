package com.claytantor.codepuzzles.services;

import com.claytantor.codepuzzles.model.BinaryTreeNode;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by claytongraham on 6/16/15.
 */

@Component
public class BinaryTreeService {


    public String getMessage(){
        return "BinaryTreeService active";
    }

    public List<BinaryTreeNode> searchMinimumCommonAncestors(
            BinaryTreeNode root, Integer nodeId1, Integer nodeId2){

        //find the nodes by id

        //find the list of all ancestors for each node
            // add the ids to a common set

        //reduce the set

        //get nodes by id
            //add to return list

        return null;
    }

    /**
     * will return a null value if a match cant be found. You have to search
     * both ways or it will get lost on the right or left directions
     *
     * @param root
     * @param nodeId
     * @return
     */
    public BinaryTreeNode searchNodeById(BinaryTreeNode root, Integer nodeId, BinaryTreeNode parent){
        if(root.getId().equals(nodeId)) {
            root.setParent(parent);
            return root;
        } else if (root.getLeft() != null) {
            BinaryTreeNode node = searchNodeById(root.getLeft(),nodeId,root);
            if(node == null){
                return searchNodeById(root.getRight(),nodeId,root);
            } else {
                node.setParent(root);
                return node;
            }
        } else if (root.getRight() != null) {
            BinaryTreeNode node = searchNodeById(root.getRight(),nodeId,root);
            if(node == null){
                return searchNodeById(root.getLeft(),nodeId,root);
            } else {
                node.setParent(root);
                return node;
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
     * @param root
     * @param nodeId
     * @param ancestors
     * @return
     */
    public void findAncestorsForNode (
            BinaryTreeNode root, Integer nodeId, List<BinaryTreeNode> ancestors){

        if(root.getParent() != null) {
            ancestors.add(root.getParent());
        } else {

        }
        

    }





}
