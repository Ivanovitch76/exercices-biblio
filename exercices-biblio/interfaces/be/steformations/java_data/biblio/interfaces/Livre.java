package be.steformations.java_data.biblio.interfaces;

public interface Livre extends java.io.Serializable {

	String getCode();
	String getTitre();
	short getNombreDePages();
	short getNumeroEdition();
	java.util.Date getDateDeParution();
	Collection getCollection();
	java.util.List<? extends Auteur> getAuteurs();

}
