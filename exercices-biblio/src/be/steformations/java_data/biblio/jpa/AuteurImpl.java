package be.steformations.java_data.biblio.jpa;
import be.steformations.java_data.biblio.interfaces.Auteur;

public class AuteurImpl implements Auteur {

	protected int id;
	protected String nom;
	protected String prenom;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public String getPrenom() {
		return prenom;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
}
