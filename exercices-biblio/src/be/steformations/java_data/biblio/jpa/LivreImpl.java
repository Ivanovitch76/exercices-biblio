package be.steformations.java_data.biblio.jpa;
import java.util.Date;
import java.util.List;

import be.steformations.java_data.biblio.interfaces.Auteur;
import be.steformations.java_data.biblio.interfaces.Collection;
import be.steformations.java_data.biblio.interfaces.Livre;

public class LivreImpl implements Livre {

	protected String code;
	protected String titre;
	protected short nbPages;
	protected short numero;
	protected Date ddp;
	protected Collection collection;
	protected java.util.List<AuteurImpl> auteurs;
	
	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getTitre() {
		return titre;
	}

	@Override
	public short getNombreDePages() {
		return nbPages;
	}

	@Override
	public short getNumeroEdition() {
		return numero;
	}

	@Override
	public Date getDateDeParution() {
		return ddp;
	}

	@Override
	public Collection getCollection() {
		return collection;
	}

	@Override
	public List<? extends Auteur> getAuteurs() {
		return auteurs;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void setNbPages(short nbPages) {
		this.nbPages = nbPages;
	}

	public void setNumero(short numero) {
		this.numero = numero;
	}

	public void setDdp(Date ddp) {
		this.ddp = ddp;
	}

	public void setCollection(Collection collection) {
		this.collection = collection;
	}

	public void setAuteurs(java.util.List<AuteurImpl> auteurs) {
		this.auteurs = auteurs;
	}

	
	
}
