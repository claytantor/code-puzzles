package com.claytantor.codepuzzles;

import com.claytantor.codepuzzles.model.BinaryTreeNode;
import com.claytantor.codepuzzles.services.BinaryTreeService;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.hamcrest.Matchers;
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
import java.util.ArrayList;
import java.util.List;

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

    private BinaryTreeNode testNode;

    @Before
    public  void setUp(){
        try {
            String nodes = IOUtils.toString(BinaryTressServiceTest.class.getResourceAsStream("/static/json/binary-tree01.json"), "UTF-8");
            log.debug(nodes);
            ObjectMapper mapper = new ObjectMapper();
            this.testNode = mapper.readValue(nodes, BinaryTreeNode.class);
        } catch (JsonMappingException e) {
            log.error("problem deserializing file",e);
        } catch (IOException e) {
            log.error("problem reading file",e);
        }
    }

    @Test
    public void canFindTestNode() {
        assertThat(testNode.getId(), equalTo(1));
        assertThat(testNode.getLeft().getId(), equalTo(2));
        assertThat(testNode.getLeft().getLeft().getId(), equalTo(4));
        assertThat(testNode.getLeft().getRight().getId(), equalTo(5));
        assertThat(testNode.getRight().getId(), equalTo(3));
        assertThat(testNode.getRight().getLeft().getId(), equalTo(6));
        assertThat(testNode.getRight().getRight().getId(), equalTo(7));
    }

    @Test
    public void canFindNodeById() {
        assertThat(binaryTreeService.searchNodeById(testNode,1,null).getId(), equalTo(1));
        assertThat(binaryTreeService.searchNodeById(testNode,2,null).getId(), equalTo(2));
        assertThat(binaryTreeService.searchNodeById(testNode,3,null).getId(), equalTo(3));
        assertThat(binaryTreeService.searchNodeById(testNode,4,null).getId(), equalTo(4));
        assertThat(binaryTreeService.searchNodeById(testNode,5,null).getId(), equalTo(5));
        assertThat(binaryTreeService.searchNodeById(testNode,6,null).getId(), equalTo(6));
        assertThat(binaryTreeService.searchNodeById(testNode,7,null).getId(), equalTo(7));
    }

    @Test
    public void canFindAncestors() {
        BinaryTreeNode node = binaryTreeService.searchNodeById(testNode,5, null);
        List<BinaryTreeNode> ancestors = new ArrayList<BinaryTreeNode>();
        binaryTreeService.findAncestorsForNode(node,ancestors);
        assertThat(ancestors.size(), equalTo(2));

    }



}
