package com.advisense.microsave.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.advisense.microsave.mongodb.DatabaseClient;
import com.advisense.microsave.util.ImportUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class PopulateController {
    private Logger logger = LoggerFactory.getLogger(PopulateController.class);

    @Value("${spring.data.mongodb.uri}")
    private String mongodbUri;

    @Value("${spring.data.mongodb.database}")
    private String mongodbName;

    @GetMapping("/dbName")
    public String getMethodName() {
        return mongodbName;
    }
    
    @GetMapping("/populateMicrosaveDB")
    public String populateDB() throws IOException {
        logger.debug("PopulateController: request recived.");

        String jsonLines = ImportUtils.linesFromResource();

        JSONObject jsnobject = new JSONObject(jsonLines);
        JSONArray jsonArray = jsnobject.getJSONArray("customers");
   
        DatabaseClient databaseClient = new DatabaseClient(mongodbUri,mongodbName);
        databaseClient.putDocument(jsonArray);
        return jsonLines;

    }
}
