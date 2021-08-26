package dao;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

import model.Utilisateur;

public class UtilisateurDao extends Dao<Utilisateur> {

	MongoCollection<Document> collection = database.getCollection("utilisateur");

	@Override
	public boolean create(Utilisateur obj) {
//		int id = (int)collection.countDocuments()+1;
		Document document = new Document();
		try {
//			document.put("id" , obj.getId());
			document.put("nom", obj.getNom());
			document.put("prénom", obj.getPrenom());
			document.put("civilité", obj.getCivil());
			document.put("date de naissance", obj.getNaissance());
			document.put("numéro de sécurité social", obj.getSocial());
			document.put("numéro de téléphone", obj.getTel());
			document.put("numero", obj.getNumero());
			document.put("voie", obj.getVoie());
			document.put("code postal", obj.getCodePostal());
			document.put("ville", obj.getVille());
			collection.insertOne(document);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Utilisateur obj) {
		collection.deleteOne(Filters.eq("numéro de sécurité social", obj.getSocial()));
		return true;
	}

	@Override
	public boolean update(Utilisateur obj) {
		try {
			Document document = new Document();
//			document.put("id" , obj.getId());
			document.put("nom", obj.getNom());
			document.put("prénom", obj.getPrenom());
			document.put("civilité", obj.getCivil());
			document.put("date de naissance", obj.getNaissance());
			document.put("numéro de sécurité social", obj.getSocial());
			document.put("numéro de téléphone", obj.getTel());
			document.put("numero", obj.getNumero());
			document.put("voie", obj.getVoie());
			document.put("code_postal", obj.getCodePostal());
			document.put("ville", obj.getVille());
			UpdateResult updateResult = collection.updateOne(Filters.eq("id", obj.getSocial()),
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
	public Utilisateur find(String social) {

		Document doc = collection.find(Filters.eq("numéro de sécurité social", social)).first();
		if (doc == null)
			return null;
		else {
			// System.out.println(doc.toString());
			Utilisateur utilisateur = new Utilisateur(doc.getString("nom"), doc.getString("prénom"),
					doc.getString("civilité"), doc.getString("date de naissance"),
					doc.getString("numéro de sécurité social"), doc.getString("numéro de téléphone"),
					doc.getString("numero"), doc.getString("voie"), doc.getString("code postal"),
					doc.getString("ville"));
			return utilisateur;
		}
	}

	@Override
	public ArrayList<Utilisateur> findAll() {
		FindIterable<Document> documents = collection.find();
		MongoCursor<Document> cursor = documents.iterator();
		ArrayList<Utilisateur> utilisateur = new ArrayList<Utilisateur>();
		try {
			while (cursor.hasNext()) {
				Document doc = cursor.next();
				Utilisateur u = new Utilisateur(doc.getString("nom"), doc.getString("prénom"),
						doc.getString("civilité"), doc.getString("date de naissance"),
						doc.getString("numéro de sécurité social"), doc.getString("numéro de téléphone"),
						doc.getString("numero"), doc.getString("voie"), doc.getString("code postal"),
						doc.getString("ville"));
				utilisateur.add(u);
			}
		} finally {
			cursor.close();
		}
		return utilisateur;
	}

	@Override
	public Utilisateur findparnom(String nom) {
		Document doc = collection.find(Filters.eq("nom", nom)).first();
		if (doc == null)
			return null;
		else {
			// System.out.println(doc.toString());
			Utilisateur utilisateur = new Utilisateur(doc.getString("nom"), doc.getString("prénom"),
					doc.getString("civilité"), doc.getString("date de naissance"),
					doc.getString("numéro de sécurité social"), doc.getString("numéro de téléphone"),
					doc.getString("numero"), doc.getString("voie"), doc.getString("code postal"),
					doc.getString("ville"));
			return utilisateur;
		}
		
	}



}
