package be.steformations.java_data.biblio.jpa;
import java.util.Date;

import be.steformations.java_data.biblio.interfaces.Emprunteur;

public class EmprunteurImpl implements Emprunteur {

	protected int id;
	protected String nom;
	protected String prenom;
	protected Date ddn;
	protected String telephone;
	protected String email;
	
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getPrenom() {
		return prenom;
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public Date getDateDeNaissance() {
		return ddn;
	}

	@Override
	public String getTelephone() {
		return telephone;
	}

	@Override
	public String getEmail() {
		return email;
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

	public void setDdn(Date ddn) {
		this.ddn = ddn;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
