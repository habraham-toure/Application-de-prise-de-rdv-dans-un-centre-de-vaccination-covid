package model;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="Rdv")
@XmlType(propOrder={"numero de securite social","nom","date"})
public class Rdv {

	String social;
	String nom;
//	int day;
//	int month;
//	int year;
	Date date;
//	int id;

	public Rdv(String social, String nom, Date date) {
		super();
		this.social = social;
		this.nom = nom;
		this.date = date;
//		this.id=id;
	}
	/*
	 * public int getId() { return id; }
	 * 
	 * public void setId(int id) { this.id = id; }
	 */
	
	@XmlElement(name="nom")
	public String getNom() {
		return nom;
	}

	@XmlElement(name="numero de securite social")
	public String getSocial() {
		return social;
	}

	public void setSocial(String social) {
		this.social = social;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@XmlElement(name="date")
	public Date getDate() {
		return date;
	}
/*
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
*/
	

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");   
 //       String str = format.format(date);
		return "Rdv [numéro de sécurité social=" + social + ", nom=" + nom + ", date=" + date + "]";
	}


}
