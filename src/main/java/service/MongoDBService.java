package service;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import util.PropertiesUtil;

public class MongoDBService {
    private Mongo mongo;
    private PropertiesUtil propertiesUtil = new PropertiesUtil();
    private String dbHost= propertiesUtil.getProperty("mongoDBHost");
    private int dbPort = Integer.parseInt(propertiesUtil.getProperty("mongoDBPort"));

    public MongoDBService() {
        mongo = new MongoClient(dbHost, dbPort);
    }

    public void closeDBConnection() {
        mongo.close();
    }
}
