package com.claytantor.codepuzzles.controller;

import com.claytantor.codepuzzles.model.BinaryTreeNode;
import com.claytantor.codepuzzles.model.FindLeastAncestor;
import com.claytantor.codepuzzles.services.BinaryTreeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by claytongraham on 6/16/15.
 */
@RestController

public class BinaryTreeController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BinaryTreeService binaryTreeService;

    @RequestMapping(value = { "/ancestor" }, method = RequestMethod.POST)
    public Map<String,Object> ancestor(@RequestBody FindLeastAncestor form) {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("root", form.getRoot());

        BinaryTreeNode minCommonAncestor =
                binaryTreeService.findMinimumCommonAncestors(
                        form.getRoot(),
                        form.getNodeId1(),
                        form.getNodeId2());
        log.debug("got node id:"+minCommonAncestor.getId());
        model.put("ancestorId", minCommonAncestor.getId());

        return model;
    }


}
