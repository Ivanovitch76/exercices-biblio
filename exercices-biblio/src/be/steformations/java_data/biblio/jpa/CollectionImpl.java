package be.steformations.java_data.biblio.jpa;
import be.steformations.java_data.biblio.interfaces.Collection;

public class CollectionImpl implements Collection {

	protected int id;
	protected String nom;
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getNom() {
		return nom;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	
}
