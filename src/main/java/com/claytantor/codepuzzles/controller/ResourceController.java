package com.claytantor.codepuzzles.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by claytongraham on 6/16/15.
 */
@RestController
public class ResourceController {

    @RequestMapping("/resource")
    public Map<String,Object> resource() {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

}
