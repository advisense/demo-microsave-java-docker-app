package com.advisense.microsave.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

@RestController
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);
    private Random random = new Random();
    @Autowired
    private HttpServletRequest context;

    @GetMapping("/")
    public HashMap index() {
        int id = random.nextInt(10000);
        logger.debug("request served: "+ id);
        

        return new HashMap<String,Object>() {{
            put("success", true);
            put("hello", "world");
            put("new-message", "adding a new msg");
            put("message-id", id);
            put("method", context.getMethod());
        }};
    }
}
