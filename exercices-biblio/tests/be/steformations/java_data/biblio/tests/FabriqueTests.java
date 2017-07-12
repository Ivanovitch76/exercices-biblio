package be.steformations.java_data.biblio.tests;

import be.steformations.java_data.biblio.interfaces.GestionnaireBibliotheque;

public class FabriqueTests {

	public static GestionnaireBibliotheque getGestionnaireBibliotheque() {
		String persistenceUnit = "postgresql_eclipselink";
		javax.persistence.EntityManager em 
		 	= javax.persistence.Persistence.createEntityManagerFactory("postgresql_eclipselink")
		 		.createEntityManager();
		return new be.steformations.java_data.biblio.jpa.GestionnaireBibliothequeJpaImpl(em);
	}
}
