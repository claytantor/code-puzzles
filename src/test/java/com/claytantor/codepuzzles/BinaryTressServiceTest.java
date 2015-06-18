package com.claytantor.codepuzzles;

import com.claytantor.codepuzzles.model.BinaryTreeNode;
import com.claytantor.codepuzzles.services.BinaryTreeService;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.*;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by claytongraham on 6/16/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)   // 1
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class BinaryTressServiceTest {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BinaryTreeService binaryTreeService;

    //static/json/binary-tree01.json
    private BinaryTreeNode testNode01;

    //static/json/binary-tree02.json
    private BinaryTreeNode testNode02;

    @Before
    public  void setUp(){
        try {
            ObjectMapper mapper = new ObjectMapper();

            //load nodes01
            String nodes01 = IOUtils.toString(
                    BinaryTressServiceTest.class.getResourceAsStream(
                            "/static/json/binary-tree01.json"), "UTF-8");
            this.testNode01 = mapper.readValue(nodes01, BinaryTreeNode.class);

            //load nodes02
            String nodes02 = IOUtils.toString(
                    BinaryTressServiceTest.class.getResourceAsStream(
                            "/static/json/binary-tree02.json"), "UTF-8");
            this.testNode02 = mapper.readValue(nodes02, BinaryTreeNode.class);


        } catch (JsonMappingException e) {
            log.error("problem deserializing file",e);
        } catch (IOException e) {
            log.error("problem reading file",e);
        }
    }

    @Test
    public void canFindTestNode() {
        assertThat(testNode01.getId(), equalTo(1));
        assertThat(testNode01.getLeft().getId(), equalTo(2));
        assertThat(testNode01.getLeft().getLeft().getId(), equalTo(4));
        assertThat(testNode01.getLeft().getRight().getId(), equalTo(5));
        assertThat(testNode01.getRight().getId(), equalTo(3));
        assertThat(testNode01.getRight().getLeft().getId(), equalTo(6));
        assertThat(testNode01.getRight().getRight().getId(), equalTo(7));
    }

    @Test
    public void canFindNodeById() {
        assertThat(binaryTreeService.searchNodeById(testNode01,1,null,testNode01).getId(), equalTo(1));
        assertThat(binaryTreeService.searchNodeById(testNode01,2,null,testNode01).getId(), equalTo(2));
        assertThat(binaryTreeService.searchNodeById(testNode01,3,null,testNode01).getId(), equalTo(3));
        assertThat(binaryTreeService.searchNodeById(testNode01,4,null,testNode01).getId(), equalTo(4));
        assertThat(binaryTreeService.searchNodeById(testNode01,5,null,testNode01).getId(), equalTo(5));
        assertThat(binaryTreeService.searchNodeById(testNode01,6,null,testNode01).getId(), equalTo(6));
        assertThat(binaryTreeService.searchNodeById(testNode01,7,null,testNode01).getId(), equalTo(7));
    }

    @Test
    public void canFindAncestors() {
        BinaryTreeNode node = binaryTreeService.searchNodeById(testNode01,5, null,testNode01);
        List<BinaryTreeNode> ancestors = new ArrayList<>();
        binaryTreeService.findAncestorsForNode(node,ancestors);
        assertThat(ancestors.size(), equalTo(2));
    }

    @Test
    public void canFindCommonAncestors01() {

        // find the nodes by id
        BinaryTreeNode node1 = binaryTreeService.searchNodeById(testNode01, 5, null,testNode01);
        BinaryTreeNode node2 = binaryTreeService.searchNodeById(testNode01, 7, null,testNode01);

        // find the list of all ancestors for each node
        List<BinaryTreeNode> ancestors = new ArrayList<BinaryTreeNode>();
        binaryTreeService.findAncestorsForNode(node1, ancestors);
        binaryTreeService.findAncestorsForNode(node2, ancestors);

        // filter nodes that are not common ancestors
        Set<BinaryTreeNode> commonAncestors =
                binaryTreeService.findCommonAncestors(node1, node2, ancestors);

        //should be id=1
        assertThat(commonAncestors.size(), equalTo(1));

        // Create an array containing the elements in a set
        Object[] objectArray = commonAncestors.toArray();
        BinaryTreeNode[] array =
                (BinaryTreeNode[])commonAncestors.toArray(
                        new BinaryTreeNode[commonAncestors.size()]);

        assertThat(array[0].getId(), equalTo(1));

    }

    @Test
    public void canFindCommonAncestors02() {

        // find the nodes by id
        BinaryTreeNode node1 = binaryTreeService.searchNodeById(testNode02, 10, null, testNode02);
        BinaryTreeNode node2 = binaryTreeService.searchNodeById(testNode02, 15, null, testNode02);

        // find the list of all ancestors for each node
        List<BinaryTreeNode> ancestors = new ArrayList<>();
        binaryTreeService.findAncestorsForNode(node1, ancestors);
        binaryTreeService.findAncestorsForNode(node2, ancestors);

        // filter nodes that are not common ancestors
        Set<BinaryTreeNode> commonAncestors =
                binaryTreeService.findCommonAncestors(node1, node2, ancestors);

        //should be id=7,3,1
        assertThat(commonAncestors.size(), equalTo(3));
        Map<Integer,BinaryTreeNode> lookup = new HashMap<>();
        for(BinaryTreeNode node:commonAncestors)
            lookup.put(node.getId(),node);
        assertThat(lookup.get(7).getId(), equalTo(7));
        assertThat(lookup.get(3).getId(), equalTo(3));
        assertThat(lookup.get(1).getId(), equalTo(1));
    } 

    @Test
    public void canFindLeastCommonAncestors() {

        BinaryTreeNode minCommonAncestor10_15_02 =
                binaryTreeService.findMinimumCommonAncestors(testNode02,10,15);
        assertThat(minCommonAncestor10_15_02.getId(), equalTo(7));

    }



}
