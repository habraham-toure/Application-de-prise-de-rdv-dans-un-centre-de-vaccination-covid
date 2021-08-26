package fr.uco.ima.mongoproject;

import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.bson.Document;
import org.w3c.dom.Element;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.model.InsertOneModel;

import dao.CentreVaccinationDao;
import dao.Dao;
import dao.DaoFactory;
import dao.RdvDao;
import dao.UtilisateurDao;
import model.Adresse;
import model.CentreVaccination;
import model.CollectionModel;
import model.Rdv;
import model.Utilisateur;
import model.Propertie;

/**
 * main class
 */
public class App {

	public static void main(String[] args) throws IOException {

		/*
		 * Dao<Adresse> adresseDao = DaoFactory.getAdresseDAO(); Adresse ad =
		 * adresseDao.find(1); System.out.println( ad);
		 * 
		 * Adresse ad2 = new Adresse(87,"rue saint jacques","Angers", 49090);
		 * adresseDao.create(ad2);
		 * 
		 * adresseDao.delete(ad2);
		 * 
		 * ad.setVille("Nantes"); adresseDao.update(ad);
		 * 
		 * ad = adresseDao.find(1); System.out.println(ad);
		 */

		
		List<InsertOneModel<Document>> docs = new ArrayList();

		try {
			Dao<CentreVaccination> CentreVaccinationDao= DaoFactory.getCentreVaccinationDAO();
			
			BufferedReader br=new BufferedReader(new FileReader("./centresvaccination.json"));
			
			String line;
			while((line=br.readLine())!=null) {
				docs.add(new InsertOneModel<Document>(Document.parse(line)));
				
				Document d_oc= docs.get(0).getDocument();
				
				ArrayList<Document> features = new ArrayList<Document>();
				
				features=(ArrayList<Document>) d_oc.get("features");
				
				Iterator<Document> cursor=features.iterator();
				
				while(cursor.hasNext()) {
					Document doc=cursor.next();
					Document properties=(Document) doc.get("properties");
					
					int gid=(Integer) properties.get("gid");
					
					CentreVaccination c= new CentreVaccination(gid,
							(String)properties.getString("nom"),
							(String)properties.getString("numero"),
							(String)properties.getString("voie"),
							(String)properties.getString("codePostal"),
							(String)properties.getString("ville"),
	 /* 					(Double)properties.getDouble("latitude"),
							(Double)properties.getDouble("longitude"),
			              (String)properties.getBoolean("rdv"),
			                (String)properties.getString("rdv_site_web"),
			                (String)properties.getString("rdv_tel2"),
			                (String)properties.getString("rdv_modalite"),
			                (String)properties.getString("rdv_prevaccination"),
			                (String)properties.getString("rdv_lundi"),
			                (String)properties.getString("rdv_mardi"),
			                (String)properties.getString("rdv_mercredi"),
			                (String)properties.getString("rdv_jeudi"),
			                (String)properties.getString("rdv_vendredi"),
			                (String)properties.getString("rdv_samedi"),
			                (String)properties.getString("rdv_dimanche"),
			 */             (String)properties.getString("telephone"),
			 (Date)properties.getDate("date_ouverture"), 
			 (Date)properties.getDate("date_fermeture"));
					
					CentreVaccinationDao.create(c);
					
					
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("Importer le Json succes");
		
		// surface
		System.out.println("---------------------- BIENVENUE ---------------------- \n");
	
		int quite = 0;
		do {
			quite = 0;
			Scanner sc = new Scanner(System.in);
			System.out.println("---------------------- VOULEZ VOUS ---------------------- ");
			System.out.println("---------------------- 1 - QUITTER ----------------------");
			System.out.println("---------------------- 0 - COMMENCER ----------------------");
			quite = sc.nextInt();
			if (quite == 0)
				Choix();
			break;
		} while (quite == 0);
		System.out.println("---------------------- AU REVOIR ET A BIENTOT ----------------------");
	}


	private static void Choix() throws IOException {
		// TODO Auto-generated method stub
		Scanner sc1 = new Scanner(System.in);
		int choix;
		
		do {
			System.out.println("---------------------- Veuillez faire un choix ? ----------------------");
			System.out.println("-----------------------------------------------------------------------");
			System.out.println(" 1 - Afficher les informations du centre (Can't use)");
			System.out.println(" 2 - Afficher les Rendez-Vous");
			System.out.println(" 3 - Prendre un Rendez-Vous");
			System.out.println(" 4 - Modifier ou Supprimer un Rendez-Vous");
			System.out.println(" 5 - Exporter au format XML");
			System.out.println(" 6 - Exporter au format HTML");
			choix = sc1.nextInt();
		}while (choix < 1 && choix > 6);
		if (choix == 1)
			AffichierCentre();
		if (choix == 2)
			AffichierRDV();
		if (choix == 3)
			CreateRDV();
		if (choix == 4)
			ModifierRDV();
		if (choix == 5)
			exportXML();
		if (choix == 6)
			exportHTML();
		sc1.close();

	}


/*
	private static void JsonTranser() throws IOException {
		 String filePath = "C:\\Users\\33658\\Documents\\eclipse-workspace\\mongoproject\\centresvaccination.json";
	        ObjectMapper objectMapper = new ObjectMapper();
	        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	        File jsonFile = new File(filePath);
	        
	  
	        CollectionModel collectionModel = objectMapper.readValue(jsonFile, CollectionModel.class);

		
		
		
		
	        
	}

*/
	private static void AffichierCentre() {
		// TODO Auto-generated method stub
		CentreVaccinationDao cdao = new CentreVaccinationDao();

		ArrayList<CentreVaccination> listCentreVaccination = new ArrayList<CentreVaccination>();
		listCentreVaccination = cdao.findAll();

		for (int i = 0; i < listCentreVaccination.size(); i++) {

			System.out.println(listCentreVaccination.toString());

		} 
		
	
	}

	private static void AffichierRDV() {
		// TODO Auto-generated method stub

		RdvDao rdao = new RdvDao();
		Scanner sc2 = new Scanner(System.in);
		int choix;

		do {
			System.out.println("---------------------- Veuillez faire un choix ? ----------------------");
			System.out.println("-----------------------------------------------------------------------");
			System.out.println(" 1 - Afficher tous les rdv des tous les centres");
			System.out.println(" 2 - Afficher tous les rdv du centre que vous avez choisi");

			choix = sc2.nextInt();
		}

		while (choix < 1 && choix > 2);
		if (choix == 1) {
			List<Rdv> listRdv = new ArrayList<Rdv>();
			listRdv = rdao.findAll();

			for (int i = 0; i < listRdv.size(); i++) {

				System.out.println(listRdv.toString());

			}
		}
		if (choix == 2) {
			System.out.println("---------------------- CHOISIR LE NOM DU CENTRE  ----------------------");
			AfficherNomCentre();
			System.out.println("-----------------------------------------------------------------------");

			String choixnom = sc2.next();

			ArrayList<Rdv> listRdv = new ArrayList<Rdv>();
			listRdv.add(rdao.findparnom(choixnom));

			for (int i = 0; i < listRdv.size(); i++) {

				System.out.println(listRdv.toString());

			}

		}

		sc2.close();

	}

	private static void CreateRDV() {
		// TODO Auto-generated method stub
		Scanner sc3 = new Scanner(System.in);
		System.out.println("---------------------- VOUS ALLEZ PRENDRE UN RENDEZ-VOUS ----------------------");

		System.out.println("-----------------------VOTRE NUMERO DE SECURITE SOCIAL-------------------------");

		UtilisateurDao udao = new UtilisateurDao();
		RdvDao rdao = new RdvDao();
		System.out.println("Votre numéro de sécurité social: ");
		String social = sc3.next();

		// verifier s'il existe cet utilisateur

		if (udao.find(social) == null) {
			System.out.println("---------------------- NOUS N'AVONS PAS VOTRE COMPTE ----------------------");
			System.out.println("-----------------------COMPLETER VOS INFORMATIONS-------------------------");

			System.out.println("Votre nom: ");
			String nom = sc3.next();
			System.out.println("Votre prénom: ");
			String prenom = sc3.next();
			System.out.println("Votre civilité: ");
			String civil = sc3.next();
			System.out.println("Votre date de naissance: ");
			String naissance = sc3.next();
			System.out.println("Votre numéro de téléphone: ");
			String tel = sc3.next();
			System.out.println("-----------------------COMPLETER VOTRE ADRESSE-------------------------");
			System.out.println("Votre numero: ");
			String numero = sc3.next();
			System.out.println("Votre voie SANS ESPACE: ");
			String voie = sc3.next();
			System.out.println("Votre ville: ");
			String ville = sc3.next();
			System.out.println("Votre code postal: ");
			String codePostal = sc3.next();

			Utilisateur u = new Utilisateur(nom, prenom, civil, naissance, social, tel, numero, voie, codePostal,
					ville);

			udao.create(u);

		} else {

			System.out.println("-----------------------COMPTE EXISTE--------------------------------------");
		}

		System.out.println("---------------------- VOUS ALLEZ PRENDRE UN RENDEZ-VOUS ----------------------");
//		System.out.println("---------------------- CHOISIR VOTRE CENTRE ----------------------");
		System.out.println("------------------------------------------------------------------");

//		AfficherNomCentre();

		// RdvDao rDao=new RdvDao();
		boolean reussi;

		// prendre un rdv

		do {
			reussi = false;
			System.out.println("Votre choix(Le nom du centre SANS ESPACE): ");
			String centre_utilisateur = sc3.next();

			System.out.println("Votre date(dd/mm/yyyy): ");
			System.out.println("jour: dd ");
			int j = sc3.nextInt();
			System.out.println("mois: mm");
			int m = sc3.nextInt();
			System.out.println("année: yyyy");
			int an = sc3.nextInt();
			Date date_utilisateur = new Date(an-1900, m-1, j);

			Date now = new Date(System.currentTimeMillis());
			
			// si cet utilisateur n'a pas de rdv dans le meme jour dans le meme centre
			if (RdvExiste(rdao, social, centre_utilisateur, date_utilisateur) == false) {
				if(date_utilisateur.compareTo(now)>0) {
		
			Rdv r = new Rdv(social, centre_utilisateur, date_utilisateur);

			rdao.create(r);

			System.out.println("---------------------- REUSSI! ----------------------");
			reussi = true;
			break;
			 }
			  } else { 
				  System.out.println("---------------------- VOUS AVEZ UN RDV LE JOUR MEME----------------------");
				  System.out.println("---------------------- CHOISIR UNE AUTRE DATE OU CENTRE ----------------------");
			  }
			
		} while (reussi = false);

		sc3.close();
	}

	private static boolean RdvExiste(RdvDao rDao, String social, String centre_utilisateur, Date date_utilisateur) {
		// TODO Auto-generated method stub
		Rdv rf = rDao.find(social);

		if (rf != null) {
			String centre = rf.getNom();
			Date date = rf.getDate();
			System.out.println(centre+date+centre_utilisateur+date_utilisateur);
			if (centre.equals(centre_utilisateur) && date.equals(date_utilisateur))
				return true;
			else
				return false;
		} else
			return false;

	}

	private static void AfficherNomCentre() {
		// TODO Auto-generated method stub
		CentreVaccinationDao cdao = new CentreVaccinationDao();

		ArrayList<CentreVaccination> listCentreVaccination = new ArrayList<CentreVaccination>();
		
		listCentreVaccination = cdao.findAll();

		for (int i = 0; i < listCentreVaccination.size(); i++) {
	
			String nom=listCentreVaccination.get(i).getNom().toString();
	

		} 

	}

	private static void ModifierRDV() {
		// TODO Auto-generated method stub
		Scanner sc4 = new Scanner(System.in);
		int choix = 0;
		RdvDao rdao = new RdvDao();

		System.out.println("Entrer le numero de securite social : ");

		String social = sc4.next();
		
		Rdv r=rdao.find(social);
		// comparer la date: we can only modifier les rdv apres today

		Date now = new Date(System.currentTimeMillis());

		System.out.println("Aujourd'hui est :" + now);
		System.out.println("Votre Rdv est :" + r.toString());
		System.out.println("Vous pouvez modifier ou supprimer les rdv apres ce jour. ");

		if (r.getDate().compareTo(now) > 0) {

			do {
				System.out.println("Pour modifier ce rdv tapez 1: ");
				System.out.println("Pour supprimer ce rdv tapez 2: ");
				choix = sc4.nextInt();
			} while (choix < 1 && choix > 2);
			
			if (choix == 1) {

				AfficherNomCentre();

				boolean reussi = false;

				do {
					System.out.println("Votre choix: ");
					String centre_utilisateur = sc4.next();

					System.out.println("Votre date(jour/mois/annee avec pause): ");
					System.out.println("jour: ");
					int j = sc4.nextInt();
					System.out.println("mois: ");
					int m = sc4.nextInt();
					System.out.println("année: ");
					int an = sc4.nextInt();

					Date date_utilisateur = new Date(an-1900, m-1, j);

					if (RdvExiste(rdao, social, centre_utilisateur, date_utilisateur) == false) {
						Rdv n=new Rdv(social,centre_utilisateur,date_utilisateur);
						rdao.update(n);
						reussi = true;
						System.out.println("---------------------- REUSSI! ----------------------");
						break;
					} else {
						System.out.println("---------------------- VOUS AVEZ UN RDV LE JOUR MEME----------------------");
						System.out.println("---------------------- CHOISIR UNE AUTRE DATE ----------------------");
					}

				} while (reussi = false);

			}
			if (choix == 2) {
				rdao.delete(r);
				System.out.println("---------------------- REUSSI! ----------------------");
			}
		} else {
			System.out.println("VOUS N'AVEZ PAS DE RDV QUI PEUT MODIFIER!");
		}
		sc4.close();
	}

	private static void exportXML() {
		Scanner sc5 = new Scanner(System.in);
		int choix = 0;
		do {
			System.out.println(" ---------------------- VOUS ALLEZ EXPORTER LES ELEMENTS EN FORMAT XML ----------------------");
			System.out.println("----------------------                           ----------------------");
			System.out.println("---------------------- Veuillez faire un choix ? ----------------------");
			System.out.println("----------------------                           ----------------------");
			System.out.println(" 1 - La liste des utilisateurs ");
			System.out.println(" 2 - La liste des rdv ");

			choix = sc5.nextInt();
		} while (choix != 1 && choix != 2);

		if (choix == 1) {

			try {

				Dao<Utilisateur> UtilisateurDao = new UtilisateurDao();
				ArrayList<Utilisateur> listUtilisateur = UtilisateurDao.findAll();
				
				
				DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder=dbFactory.newDocumentBuilder();
				
				org.w3c.dom.Document docu=docBuilder.newDocument();
				
				Element util=docu.createElement("Utilisateur");
				docu.appendChild(util);
				
				for (int i = 0; i < listUtilisateur.size(); i++) {
					
					Element u=docu.createElement("Utilisateur");
					util.appendChild(u);
					
					Element nom=docu.createElement("nom");
					nom.appendChild(docu.createTextNode(listUtilisateur.get(i).getNom()));
					u.appendChild(nom);
					
					Element prenom=docu.createElement("prenom");
					prenom.appendChild(docu.createTextNode(listUtilisateur.get(i).getPrenom()));
					u.appendChild(prenom);
					
					Element civil=docu.createElement("civil");
					civil.appendChild(docu.createTextNode(listUtilisateur.get(i).getCivil()));
					u.appendChild(civil);
					
					Element nai=docu.createElement("date_de_naissance");
					nai.appendChild(docu.createTextNode(listUtilisateur.get(i).getNaissance()));
					u.appendChild(nai);
					
					Element soc=docu.createElement("numero_de_securite_social");
					soc.appendChild(docu.createTextNode(listUtilisateur.get(i).getSocial()));
					u.appendChild(soc);
					
					Element tel=docu.createElement("telephone");
					tel.appendChild(docu.createTextNode(listUtilisateur.get(i).getTel()));
					u.appendChild(tel);
					
					Element num=docu.createElement("numero_d_adresse");
					num.appendChild(docu.createTextNode(listUtilisateur.get(i).getNumero()));
					u.appendChild(num);
					
					Element voie=docu.createElement("voie");
					voie.appendChild(docu.createTextNode(listUtilisateur.get(i).getVoie()));
					u.appendChild(voie);
					
					Element ville=docu.createElement("ville");
					ville.appendChild(docu.createTextNode(listUtilisateur.get(i).getVille()));
					u.appendChild(ville);
				
					Element cd=docu.createElement("code_postal");
					cd.appendChild(docu.createTextNode(listUtilisateur.get(i).getCodePostal()));
					u.appendChild(cd);
					
				}
				
				TransformerFactory transformerFactory=TransformerFactory.newInstance();
				Transformer transformer=transformerFactory.newTransformer();
				DOMSource source=new DOMSource(docu);
				
				StreamResult resultat=new StreamResult(new File("./Utilisateur.xml"));
				transformer.transform(source,resultat);
				
				System.out.println("Done.");
			}catch(ParserConfigurationException pce) {
				pce.printStackTrace();
			}catch(TransformerException tfe) {
				tfe.printStackTrace();
			}	 
				
/*				FileOutputStream fos = new FileOutputStream(new File("./Utilisateurs.xml"));
				XMLEncoder encoder = new XMLEncoder(fos);
				for (int i = 0; i < listUtilisateur.size(); i++) {

					encoder.writeObject(listUtilisateur.get(i));

				}
				encoder.close();
*/				sc5.close();

	

		}
		if (choix == 2) {

			try {
				Dao<Rdv> RdvDao = new RdvDao();
				ArrayList<Rdv> listRdv = RdvDao.findAll();
				
				
				DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder=dbFactory.newDocumentBuilder();
				
				org.w3c.dom.Document docu=docBuilder.newDocument();
				
				Element rdv=docu.createElement("Rdv");
				docu.appendChild(rdv);
				
				for (int i = 0; i < listRdv.size(); i++) {
					
					Element r=docu.createElement("Rdv");
					rdv.appendChild(r);
			
				
					Element soc=docu.createElement("numero_de_securite_social");
					soc.appendChild(docu.createTextNode(listRdv.get(i).getSocial()));
					r.appendChild(soc);
					
					Element nom=docu.createElement("nom");
					nom.appendChild(docu.createTextNode(listRdv.get(i).getNom()));
					r.appendChild(nom);
					
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd");   
					String str = format.format(listRdv.get(i).getDate());
					Element cd=docu.createElement("date");
					cd.appendChild(docu.createTextNode(str));
					r.appendChild(cd);
					
				}
				
				TransformerFactory transformerFactory=TransformerFactory.newInstance();
				Transformer transformer=transformerFactory.newTransformer();
				DOMSource source=new DOMSource(docu);
				
				StreamResult resultat=new StreamResult(new File("./Rdv.xml"));
				transformer.transform(source,resultat);
				
				System.out.println("Done.");
			}catch(ParserConfigurationException pce) {
				pce.printStackTrace();
			}catch(TransformerException tfe) {
				tfe.printStackTrace();
			}	 
			}

/*				Dao<Rdv> RdvDao = new RdvDao();
				List<Rdv> listRdv = new ArrayList<Rdv>();
				listRdv = RdvDao.findAll();
				FileOutputStream fos = new FileOutputStream(new File("./Rdv.xml"));
				XMLEncoder encoder = new XMLEncoder(fos);
				for (int i = 0; i < listRdv.size(); i++) {

					encoder.writeObject(listRdv.get(i));

				}
				encoder.close();
				fos.close();

			} catch (Exception e) {
				System.out.println("" + e.getMessage());
			}

		}

*/		sc5.close();
	}
	
	private static void exportHTML() throws FileNotFoundException {
		Scanner sc6 = new Scanner(System.in);
		int choix = 0;
		do {
			System.out.println(" ---------------------- VOUS ALLEZ EXPORTER LES ELEMENTS EN FORMAT HTML ----------------------");
			System.out.println("----------------------                           ----------------------");
			System.out.println("---------------------- Veuillez faire un choix ? ----------------------");
			System.out.println("----------------------                           ----------------------");
			System.out.println(" 1 - La liste des utilisateurs ");
			System.out.println(" 2 - La liste des rdv ");

			choix = sc6.nextInt();
		} while (choix != 1 && choix != 2);

		if (choix == 1) {

			try {

				Dao<Utilisateur> UtilisateurDao = new UtilisateurDao();
				ArrayList<Utilisateur> listUtilisateur = UtilisateurDao.findAll();
				
				
				DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder=dbFactory.newDocumentBuilder();
				
				org.w3c.dom.Document docu=docBuilder.newDocument();
				
				Element util=docu.createElement("Utilisateur");
				docu.appendChild(util);
				
				for (int i = 0; i < listUtilisateur.size(); i++) {
					
					Element u=docu.createElement("Utilisateur");
					util.appendChild(u);
					
					Element nom=docu.createElement("nom");
					nom.appendChild(docu.createTextNode(listUtilisateur.get(i).getNom()));
					u.appendChild(nom);
					
					Element prenom=docu.createElement("prenom");
					prenom.appendChild(docu.createTextNode(listUtilisateur.get(i).getPrenom()));
					u.appendChild(prenom);
					
					Element civil=docu.createElement("civil");
					civil.appendChild(docu.createTextNode(listUtilisateur.get(i).getCivil()));
					u.appendChild(civil);
					
					Element nai=docu.createElement("date_de_naissance");
					nai.appendChild(docu.createTextNode(listUtilisateur.get(i).getNaissance()));
					u.appendChild(nai);
					
					Element soc=docu.createElement("numero_de_securite_social");
					soc.appendChild(docu.createTextNode(listUtilisateur.get(i).getSocial()));
					u.appendChild(soc);
					
					Element tel=docu.createElement("telephone");
					tel.appendChild(docu.createTextNode(listUtilisateur.get(i).getTel()));
					u.appendChild(tel);
					
					Element num=docu.createElement("numero_d_adresse");
					num.appendChild(docu.createTextNode(listUtilisateur.get(i).getNumero()));
					u.appendChild(num);
					
					Element voie=docu.createElement("voie");
					voie.appendChild(docu.createTextNode(listUtilisateur.get(i).getVoie()));
					u.appendChild(voie);
					
					Element ville=docu.createElement("ville");
					ville.appendChild(docu.createTextNode(listUtilisateur.get(i).getVille()));
					u.appendChild(ville);
				
					Element cd=docu.createElement("code_postal");
					cd.appendChild(docu.createTextNode(listUtilisateur.get(i).getCodePostal()));
					u.appendChild(cd);
					
				}
				
				TransformerFactory transformerFactory=TransformerFactory.newInstance();
				Transformer transformer=transformerFactory.newTransformer(new javax.xml.transform.stream.StreamSource
			            ("./utilisateur.xsl"));
				DOMSource source=new DOMSource(docu);
				
				StreamResult resultat=new StreamResult(new FileOutputStream("./Utilisateur.html"));
				transformer.transform(source,resultat);
				
				System.out.println("Done.");
			}catch(ParserConfigurationException pce) {
				pce.printStackTrace();
			}catch(TransformerException tfe) {
				tfe.printStackTrace();
			}	 
				
/*				FileOutputStream fos = new FileOutputStream(new File("./Utilisateurs.xml"));
				XMLEncoder encoder = new XMLEncoder(fos);
				for (int i = 0; i < listUtilisateur.size(); i++) {

					encoder.writeObject(listUtilisateur.get(i));

				}
				encoder.close();
*/				sc6.close();

	

		}
		if (choix == 2) {

			try {
				Dao<Rdv> RdvDao = new RdvDao();
				ArrayList<Rdv> listRdv = RdvDao.findAll();
				
				
				DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder=dbFactory.newDocumentBuilder();
				
				org.w3c.dom.Document docu=docBuilder.newDocument();
				docu.normalize();
				
				Element rdv=docu.createElement("Rdv");
				docu.appendChild(rdv);
				
				for (int i = 0; i < listRdv.size(); i++) {
					
					Element r=docu.createElement("Rdv");
					rdv.appendChild(r);
			
				
					Element soc=docu.createElement("numero_de_securite_social");
					soc.appendChild(docu.createTextNode(listRdv.get(i).getSocial()));
					r.appendChild(soc);
					
					Element nom=docu.createElement("nom");
					nom.appendChild(docu.createTextNode(listRdv.get(i).getNom()));
					r.appendChild(nom);
					
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd");   
					String str = format.format(listRdv.get(i).getDate());
					Element cd=docu.createElement("date");
					cd.appendChild(docu.createTextNode(str));
					r.appendChild(cd);
					
				}
				
				TransformerFactory transformerFactory=TransformerFactory.newInstance();
				Transformer transformer=transformerFactory.newTransformer(new javax.xml.transform.stream.StreamSource
			            ("./rdv.xsl"));
				DOMSource source=new DOMSource(docu);
				
				StreamResult resultat=new StreamResult(new FileOutputStream("./Rdv.html"));
				transformer.transform(source,resultat);
				
				System.out.println("Done.");
			}catch(ParserConfigurationException pce) {
				pce.printStackTrace();
			}catch(TransformerException tfe) {
				tfe.printStackTrace();
			}	 
			}

/*				Dao<Rdv> RdvDao = new RdvDao();
				List<Rdv> listRdv = new ArrayList<Rdv>();
				listRdv = RdvDao.findAll();
				FileOutputStream fos = new FileOutputStream(new File("./Rdv.xml"));
				XMLEncoder encoder = new XMLEncoder(fos);
				for (int i = 0; i < listRdv.size(); i++) {

					encoder.writeObject(listRdv.get(i));

				}
				encoder.close();
				fos.close();

			} catch (Exception e) {
				System.out.println("" + e.getMessage());
			}

		}

*/		sc6.close();
	}

}
