package fr.uco.ima.mongoproject;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {
	  private String url;
	    private static String database; //ici je met le nom de ma database
	    private int port;
	    private static MongoClient mongoClient;
	    private static MongoDatabase mongoDatabase;
	
	    private MongoDBConnection() {
	        if (mongoClient == null){
	            readConfig();
	            mongoClient = new MongoClient(url,port);
	        }
	    }

	    public void readConfig(){
	        try {
	            // je charge mon fichier de configuration
	            InputStream input = new FileInputStream("config.properties");
	            Properties properties = new Properties();
	            // je charge les propriétés
	            properties.load(input);
	            // je récupère les valeurs des propriétés
	            this.url = properties.getProperty("url");
	            this.port = Integer.parseInt(properties.getProperty("port"));
	            this.database = properties.getProperty("database");
	        }catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public static MongoClient getInstance(){
	        if(mongoClient == null)
	            new MongoDBConnection();

	        return mongoClient;
	    }

	    public static MongoDatabase getDatabase() {
	        if (mongoDatabase == null)
	            mongoDatabase = getInstance().getDatabase(database);
	        return mongoDatabase;

	    }
}
