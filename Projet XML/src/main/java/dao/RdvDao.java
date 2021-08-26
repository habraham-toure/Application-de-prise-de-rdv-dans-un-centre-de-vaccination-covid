package dao;


import java.util.ArrayList;

import model.Rdv;
import java.util.Date;
import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

public class RdvDao extends Dao<Rdv> {

	MongoCollection<Document> collection = database.getCollection("rdv");

	@Override
	public boolean create(Rdv obj) {
		int id = (int) collection.countDocuments() + 1;
		Document document = new Document();
		try {
//			document.put("id", id);
			document.put("numéro de sécurité social", obj.getSocial());
			document.put("nom du centre", obj.getNom());
			document.put("date", obj.getDate());
			collection.insertOne(document);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Rdv obj) {
		collection.deleteOne(Filters.eq("numéro de sécurité social", obj.getSocial()));
		return true;
	}

	@Override
	public boolean update(Rdv obj) {
		try {
			Document document = new Document();
			document.put("numéro de sécurité social", obj.getSocial());
			document.put("nom du centre", obj.getNom());
			document.put("date",obj.getDate());
			UpdateResult updateResult = collection.updateOne(Filters.eq("numéro de sécurité social", obj.getSocial()),
					new Document("$set", document));
			if (updateResult.getMatchedCount() == 1)
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Rdv find(String id) {
		Document doc = collection.find(Filters.eq("numéro de sécurité social", id)).first();
		if (doc == null)
			return null;
		else {
			// System.out.println(doc.toString());
			Rdv rdv = new Rdv(doc.getString("numéro de sécurité social"), doc.getString("nom du centre"), doc.getDate("date"));
			return rdv;
		}
	}

	public Rdv findparnom(String nom) {
		Document doc = collection.find(Filters.eq("nom du centre", nom)).first();
		if (doc == null)
			return null;
		else {
			// System.out.println(doc.toString());
			Rdv rdv = new Rdv(doc.getString("numéro de sécurité social"), doc.getString("nom du centre"), doc.getDate("date"));
			return rdv;
		}
	}
	

	@Override
	public ArrayList<Rdv> findAll() {
		FindIterable<Document> documents = collection.find();
		MongoCursor<Document> cursor = documents.iterator();
		ArrayList<Rdv> rdv = new ArrayList<Rdv>();
		try {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				Rdv r = new Rdv(doc.getString("numéro de sécurité social"), doc.getString("nom du centre"),doc.getDate("date"));
				rdv.add(r);
			}
		} finally {
			cursor.close();
		}
		return rdv;
	}


}
