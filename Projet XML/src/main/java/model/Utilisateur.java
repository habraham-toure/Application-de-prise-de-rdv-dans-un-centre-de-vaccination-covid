package model;
import javax.xml.bind.annotation.*;

@XmlRootElement(name="Utilisateur")
@XmlType(propOrder={"nom","prenom","civil","date de naissance","numero de securite social", "telephone","numero d'adresse","voie","ville","code postal"})

public class Utilisateur {

	// numéro de sécurité social, nom, prénom, civilité, date de naissance, adresse,
	// numéro de téléphone,
//	private int id;	
	private String nom;
	private String prenom;
	private String civil;
	private String naissance;
	private String social;
	private String tel;
	private String numero;
	private String voie;
	private String ville;
	private String codePostal;

	public Utilisateur(String nom, String prenom, String civil, String naissance, String social, String tel,
			String numero, String voie, String codePostal, String ville) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.civil = civil;
		this.naissance = naissance;
		this.social = social;
		this.tel = tel;
		this.social = social;
		this.numero = numero;
		this.voie = voie;
		this.ville = ville;
		this.codePostal = codePostal;
	}
	
	@XmlElement(name="numero de securite social")
	public String getSocial() {
		return social;
	}

	public void setSocial(String social) {
		this.social = social;
	}

	@XmlElement(name="nom")
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@XmlElement(name="prenom")
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	@XmlElement(name="civil")
	public String getCivil() {
		return civil;
	}

	public void setCivil(String civil) {
		this.civil = civil;
	}

	@XmlElement(name="date de naissance")
	public String getNaissance() {
		return naissance;
	}

	public void setNaissance(String naissance) {
		this.naissance = naissance;
	}

	@XmlElement(name="telephone")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@XmlElement(name="numero d'adresse")
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@XmlElement(name="voie")
	public String getVoie() {
		return voie;
	}

	public void setVoie(String voie) {
		this.voie = voie;
	}

	@XmlElement(name="ville")
	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@XmlElement(name="code postal")
	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	@Override
	public String toString() {
		return "Utilisateur [nom=" + nom + ", prénom=" + prenom + ", civil=" + civil + ", naissance=" + naissance
				+ ", numéro de sécurité social=" + social + ", tel=" + tel + ", numero=" + numero + ", voie=" + voie
				+ ", ville=" + ville + ", code Postal=" + codePostal + "]";
	}

}
