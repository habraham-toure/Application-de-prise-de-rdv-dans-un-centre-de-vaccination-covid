package dao;

import java.sql.Date;
import java.util.ArrayList;
import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import model.CentreVaccination;
import model.CollectionModel;
import model.Propertie;
import model.Utilisateur;


public class CentreVaccinationDao extends Dao<CentreVaccination> {

	MongoCollection<Document> collection = database.getCollection("CentreVaccination");
	
	
	@Override
	public boolean create(CentreVaccination obj) {
		// TODO Auto-generated method stub
		Document document = new Document();
		try {
			document.put("id_centre_formation", obj.getId_centre_formation());
			document.put("nom", obj.getNom());
			document.put("numero", obj.getNumero());
			document.put("voie", obj.getVoie());
			document.put("codePostal", obj.getCodePostal());
			document.put("ville", obj.getVille());
			document.put("latitude", obj.getLatitude());
/*			document.put("longitude", obj.getLongitude());
			document.put("telephone", obj.getTelephone());
			document.put("rdv", obj.getRdv());
			document.put("rdv_site_web", obj.getRdv_site_web());
			document.put("rdv_tel2", obj.getRdv_tel2());
			document.put("rdv_modalite", obj.getRdv_modalite());
			document.put("rdv_prevaccination", obj.getRdv_prevaccination());
			document.put("rdv_lundi", obj.getRdv_lundi());
			document.put("rdv_mardi", obj.getRdv_mardi());
			document.put("rdv_mercredi", obj.getRdv_mercredi());
			document.put("rdv_jeudi", obj.getRdv_jeudi());
			document.put("rdv_vendredi", obj.getRdv_vendredi());
			document.put("rdv_samedi", obj.getRdv_vendredi());
			document.put("rdv_dimanche", obj.getRdv_dimanche());
*/			document.put("date_ouverture", obj.getDate_ouverture());
			document.put("date_fermeture", obj.getDate_fermeture());
			collection.insertOne(document);
			return true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean delete(CentreVaccination obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(CentreVaccination obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CentreVaccination find(String id_centre_formation) {
		// TODO Auto-generated method stub
		Document doc = collection.find(Filters.eq("id_centre_formation", id_centre_formation)).first();
		CentreVaccination centrevaccination = new CentreVaccination(
				doc.getInteger("id_centre_formation"),
				doc.getString("nom"),
                doc.getString("numero"),
                doc.getString("voie"),
                doc.getString("codePostal"),
                doc.getString("ville"),
/*                doc.getDouble("latitude"),
                doc.getDouble("longitude"),
                doc.getBoolean("rdv"),
                doc.getString("rdv_site_web"),
                doc.getString("rdv_tel2"),
                doc.getString("rdv_modalite"),
                doc.getString("rdv_prevaccination"),
                doc.getString("rdv_lundi"),
                doc.getString("rdv_mardi"),
                doc.getString("rdv_mercredi"),
                doc.getString("rdv_jeudi"),
                doc.getString("rdv_vendredi"),
                doc.getString("rdv_samedi"),
                doc.getString("rdv_dimanche"),
 */             doc.getString("telephone"),
                doc.getDate("date_ouverture"), 
                doc.getDate("date_fermeture"));
                    
		return centrevaccination;	
		
	}

	@Override
	public ArrayList<CentreVaccination> findAll() {
		// TODO Auto-generated method stub
		FindIterable<Document> documents = collection.find();
		MongoCursor<Document> cursor = documents.iterator();
		ArrayList<CentreVaccination> centrevaccination = new ArrayList<CentreVaccination>();
		try
		{	
			//String nom, String adr_num, String adr_voie, String com_cp, Long lat, Long lon, String date_ferm, String date_ouvt
		while(cursor.hasNext()) {
		Document doc = cursor.next();
		CentreVaccination c = new CentreVaccination(doc.getInteger("id_centre_formation"),
				doc.getString("nom"),
                doc.getString("numero"),
                doc.getString("voie"),
                doc.getString("codePostal"),
                doc.getString("ville"),
 /*                doc.getDouble("latitude"),
                doc.getDouble("longitude"),
               doc.getBoolean("rdv"),
                doc.getString("rdv_site_web"),
                doc.getString("rdv_tel2"),
                doc.getString("rdv_modalite"),
                doc.getString("rdv_prevaccination"),
                doc.getString("rdv_lundi"),
                doc.getString("rdv_mardi"),
                doc.getString("rdv_mercredi"),
                doc.getString("rdv_jeudi"),
                doc.getString("rdv_vendredi"),
                doc.getString("rdv_samedi"),
                doc.getString("rdv_dimanche"),
 */             doc.getString("telephone"),
                doc.getDate("date_ouverture"), 
                doc.getDate("date_fermeture"));                                 
	    centrevaccination.add(c);
	    		
		}
		}
		finally {
			cursor.close();
		}
		return centrevaccination;	
	}


	@Override
	public CentreVaccination findparnom(String nom) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
