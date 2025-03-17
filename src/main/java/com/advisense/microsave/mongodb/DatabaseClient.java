package com.advisense.microsave.mongodb;

import java.util.List;

import org.bson.Document;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;


public class DatabaseClient {
    private Logger logger = LoggerFactory.getLogger(DatabaseClient.class);

    private static final String customer_collection = "customers";


    private static MongoClient mongoClient;
    private static MongoDatabase mongoDatabase;



    public DatabaseClient(String databaseUri, String databaseName) {
            logger.debug("MongoDatabase:getDatabase: "+databaseUri + " " + databaseName);
            mongoClient = MongoClients.create(databaseUri);
            // if database doesn't exists, MongoDB will create it for you 
            mongoDatabase = mongoClient.getDatabase(databaseName);
        }



    protected JSONArray getCollection(String collectionName) {
        MongoCollection<Document> gradesCollection = mongoDatabase.getCollection(collectionName);
        FindIterable<Document> iterable = gradesCollection.find();
        MongoCursor<Document> cursor = iterable.iterator();

        JSONArray result = new JSONArray();
        while (cursor.hasNext()) {
            result.put(cursor.next().toJson());
        }
        return result;
    }


    public JSONArray readDatabase() {

        // if collection doesn't exists, MongoDB will create it for you 
        JSONArray collection = getCollection(customer_collection);
        return collection;
    }

    public void putDocument(JSONArray jsonString) {
        logger.debug("MongoDatabase:putDocument-jsonString: "+jsonString);
        MongoCollection<Document> collection = mongoDatabase.getCollection(customer_collection);
        for(int i=0; i<jsonString.length(); i++) {
            logger.debug("MongoDatabase:putDocument-json: "+jsonString.get(i));
            Document document = Document.parse(jsonString.get(i).toString());
            collection.insertOne(document);
        }
        
    }

    


}
