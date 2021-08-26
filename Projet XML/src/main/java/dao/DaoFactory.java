package dao;

import model.Adresse;
import model.CentreVaccination;
import model.Rdv;
import model.Utilisateur;

public class DaoFactory {
/*
	public static Dao<Adresse> getAdresseDAO(){
		return new AdresseDao();
	}
*/	
	public static Dao<Utilisateur> getUtilisateurDAO(){
		return new UtilisateurDao();
	}
	
	public static Dao<Rdv> getRdvDAO(){
		return new RdvDao();
	}
	
	public static Dao<CentreVaccination> getCentreVaccinationDAO(){
		return new CentreVaccinationDao();
	}
	
}
