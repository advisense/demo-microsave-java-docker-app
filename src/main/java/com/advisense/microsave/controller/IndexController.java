package com.advisense.microsave.controller;

import java.io.IOException;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advisense.microsave.mongodb.DatabaseClient;
import com.advisense.microsave.util.ImportUtils;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private HttpServletRequest context;


    @Value("${spring.data.mongodb.uri}")
    private String mongodbUri;

    @Value("${spring.data.mongodb.database}")
    private String mongodbName;    

    @GetMapping("/")
    public String index() throws IOException {
        logger.debug("IndexController: request served: "+ context.getContextPath());
        DatabaseClient databaseClient = new DatabaseClient(mongodbUri,mongodbName);
        JSONArray jsonArray = databaseClient.readDatabase();
        
        String jsonLines = ImportUtils.linesFromResource();
        
        return jsonLines;

    }
}
