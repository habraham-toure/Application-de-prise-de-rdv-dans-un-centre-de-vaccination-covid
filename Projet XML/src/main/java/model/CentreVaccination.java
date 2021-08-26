package model;

import java.util.Date;

public class CentreVaccination {

private int id_centre_formation;
private String nom;
private String numero;
private String voie;
private String codePostal;
private String ville;
private double longitude;	
private double latitude;
private String telephone;
private Date date_ouverture;
private Date date_fermeture;

//double latitude, double longitude,

public CentreVaccination(int id_centre_formation, String nom, String numero, String voie, String codePostal, String ville, 
		String telephone, Date date_ouverture, Date date_fermeture) {
	super();
	this.id_centre_formation=id_centre_formation;
	this.nom = nom;
	this.numero = numero;
	this.voie = voie;
	this.codePostal = codePostal;
	this.ville = ville;
	this.telephone = telephone;
/*	this.longitude = longitude;
	this.latitude = latitude;
	
	this.rdv = rdv;
	this.rdv_site_web = rdv_site_web;
	this.rdv_tel2 = rdv_tel2;
	this.rdv_modalite = rdv_modalite;
	this.rdv_prevaccination = rdv_prevaccination;
	this.rdv_lundi = rdv_lundi;
	this.rdv_mardi = rdv_mardi;
	this.rdv_mercredi = rdv_mercredi;
	this.rdv_jeudi = rdv_jeudi;
	this.rdv_vendredi = rdv_vendredi;
	this.rdv_samedi = rdv_samedi;
	this.rdv_dimanche = rdv_dimanche;
*/
	
	this.date_ouverture = date_ouverture;
	this.date_fermeture = date_fermeture;
}

/*public CentreVaccination(String nom, int numero, String voie, String codePostal, String ville, double longitude,
		double latitude, String telephone, boolean rdv, String rdv_site_web, String rdv_tel2, String rdv_modalite,
		String rdv_prevaccination, String rdv_lundi, String rdv_mardi, String rdv_mercredi, String rdv_jeudi,
		String rdv_vendredi, String rdv_samedi, String rdv_dimanche, Date date_ouverture, Date date_fermeture) {
	super();
	
	this.nom = nom;
	this.numero = numero;
	this.voie = voie;
	this.codePostal = codePostal;
	this.ville = ville;
	this.longitude = longitude;
	this.latitude = latitude;
	this.telephone = telephone;
	this.rdv = rdv;
	this.rdv_site_web = rdv_site_web;
	this.rdv_tel2 = rdv_tel2;
	this.rdv_modalite = rdv_modalite;
	this.rdv_prevaccination = rdv_prevaccination;
	this.rdv_lundi = rdv_lundi;
	this.rdv_mardi = rdv_mardi;
	this.rdv_mercredi = rdv_mercredi;
	this.rdv_jeudi = rdv_jeudi;
	this.rdv_vendredi = rdv_vendredi;
	this.rdv_samedi = rdv_samedi;
	this.rdv_dimanche = rdv_dimanche;
	this.date_ouverture = date_ouverture;
	this.date_fermeture = date_fermeture;
}


public CentreVaccination(Integer integer, String string, Integer integer2, String string2, String string3,
		String string4, Double double1, Double double2, String string5, Boolean boolean1, String string6,
		String string7, String string8, String string9, String string10, String string11, String string12,
		String string13, String string14, String string15, String string16, java.util.Date date, java.util.Date date2) {
	// TODO Auto-generated constructor stub
}
*/
public int getId_centre_formation() {
	return id_centre_formation;
}
public void setId_centre_formation(int id_centre_formation) {
	this.id_centre_formation = id_centre_formation;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getNumero() {
	return numero;
}
public void setNumero(String numero) {
	this.numero = numero;
}
public String getVoie() {
	return voie;
}
public void setVoie(String voie) {
	this.voie = voie;
}
public String getCodePostal() {
	return codePostal;
}
public void setCodePostal(String codePostal) {
	this.codePostal = codePostal;
}
public String getVille() {
	return ville;
}
public void setVille(String ville) {
	this.ville = ville;
}
public double getLongitude() {
	return longitude;
}
public void setLongitude(double longitude) {
	this.longitude = longitude;
}
public double getLatitude() {
	return latitude;
}
public void setLatitude(double latitude) {
	this.latitude = latitude;
}
public String getTelephone() {
	return telephone;
}
public void setTelephone(String telephone) {
	this.telephone = telephone;
}
/*
public boolean getRdv() {
	return rdv;
}
public void setRdv(boolean rdv) {
	this.rdv = rdv;
}
public String getRdv_site_web() {
	return rdv_site_web;
}
public void setRdv_site_web(String rdv_site_web) {
	this.rdv_site_web = rdv_site_web;
}
public String getRdv_tel2() {
	return rdv_tel2;
}
public void setRdv_tel2(String rdv_tel2) {
	this.rdv_tel2 = rdv_tel2;
}
public String getRdv_modalite() {
	return rdv_modalite;
}
public void setRdv_modalite(String rdv_modalite) {
	this.rdv_modalite = rdv_modalite;
}
public String getRdv_prevaccination() {
	return rdv_prevaccination;
}
public void setRdv_prevaccination(String rdv_prevaccination) {
	this.rdv_prevaccination = rdv_prevaccination;
}
public String getRdv_lundi() {
	return rdv_lundi;
}
public void setRdv_lundi(String rdv_lundi) {
	this.rdv_lundi = rdv_lundi;
}
public String getRdv_mardi() {
	return rdv_mardi;
}
public void setRdv_mardi(String rdv_mardi) {
	this.rdv_mardi = rdv_mardi;
}
public String getRdv_mercredi() {
	return rdv_mercredi;
}
public void setRdv_mercredi(String rdv_mercredi) {
	this.rdv_mercredi = rdv_mercredi;
}
public String getRdv_jeudi() {
	return rdv_jeudi;
}
public void setRdv_jeudi(String rdv_jeudi) {
	this.rdv_jeudi = rdv_jeudi;
}
public String getRdv_vendredi() {
	return rdv_vendredi;
}
public void setRdv_vendredi(String rdv_vendredi) {
	this.rdv_vendredi = rdv_vendredi;
}
public String getRdv_samedi() {
	return rdv_samedi;
}
public void setRdv_samedi(String rdv_samedi) {
	this.rdv_samedi = rdv_samedi;
}
public String getRdv_dimanche() {
	return rdv_dimanche;
}
public void setRdv_dimanche(String rdv_dimanche) {
	this.rdv_dimanche = rdv_dimanche;
}*/
public Date getDate_ouverture() {
	return date_ouverture;
}

public void setDate_ouverture(Date date_ouverture) {
	this.date_ouverture = date_ouverture;
}
public Date getDate_fermeture() {
	return date_fermeture;
}
public void setDate_fermeture(Date date_fermeture) {
	this.date_fermeture = date_fermeture;
}



@Override
public String toString() {
	return "CentreVaccination [id_centre_formation=" + id_centre_formation + ", nom=" + nom + ", numero=" + numero
			+ ", voie=" + voie + ", codePostal=" + codePostal + ", ville=" + ville + ", longitude=" + longitude
			+ ", latitude=" + latitude + ", telephone=" + telephone + ", date_ouverture=" + date_ouverture + ", date_fermeture="
			+ date_fermeture + "]";
}
	
}



