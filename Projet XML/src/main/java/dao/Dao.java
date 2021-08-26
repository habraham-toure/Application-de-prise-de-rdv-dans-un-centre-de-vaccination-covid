package dao;

import java.util.ArrayList;

import com.mongodb.client.MongoDatabase;

import fr.uco.ima.mongoproject.MongoDBConnection;
import model.CentreVaccination;
import model.Rdv;



public abstract class Dao<T> {

	// ici je récupère ma database qui est initialisée à l'aide de la classe que l'on a créé MongoDBConnection
	protected MongoDatabase database = MongoDBConnection.getDatabase();
	
	public abstract boolean create(T obj);
	public abstract boolean delete(T obj);
	public abstract boolean update(T obj);
	public abstract T find(String id);
	public abstract ArrayList<T> findAll();
	public abstract T findparnom(String nom);

	
}
