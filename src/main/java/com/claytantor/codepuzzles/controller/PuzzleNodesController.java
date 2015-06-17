package com.claytantor.codepuzzles.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by claytongraham on 6/16/15.
 */
@RestController
public class PuzzleNodesController {

    @RequestMapping("/puzzle/nodes")
    String home() {
        return "Hello Puzzle Node!";
    }


}
