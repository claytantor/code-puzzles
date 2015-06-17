package com.claytantor.codepuzzles.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by claytongraham on 6/16/15.
 */
@Controller
public class HomeController {

    @Value("${application.message:Hello World}")
    private String message = "Hello World";

    @RequestMapping(value = { "/","/index.html" }, method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", this.message);
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return "redirect:/appindex.html";
    }

//    @RequestMapping(value = { "home.html" })
//    public String home(Map<String, Object> model) {
//        model.put("message", new Date());
//        return "home";
//    }

}
