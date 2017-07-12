package be.steformations.java_data.biblio.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.biblio.interfaces.Auteur;
import be.steformations.java_data.biblio.interfaces.GestionnaireBibliotheque;

public class _05_TestRemoveAuteur {

	private GestionnaireBibliotheque manager = null;
	
	@Before
	public void setUp() throws Exception {
		try {
			manager = FabriqueTests.getGestionnaireBibliotheque();
			assertNotNull("FabriqueTests.getGestionnaireBibliotheque()", manager);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}		
	}

	@Test
	public void testAddEtRemoveAuteurOk() {
		long key = System.currentTimeMillis();
		String firstname = "David-" + key;
		String name = "Beazley-" + key;
		try {
			Auteur auteur = manager.addAuteur(firstname, name);
			ok(auteur, firstname, name);
			Integer code = auteur.getId();
			auteur = manager.getAuteurByCode(code);
			ok(auteur, firstname, name);
			manager.removeAuteur(code);
			assertNull( String.format("Gestionnaire.getAuteurByCode(%s)", code), 
					manager.getAuteurByCode(code) );
			assertNull( String.format("Gestionnaire.getAuteurByPrenomAndNom(\"%s\", \"%s\")", firstname, name), 
					manager.getAuteurByPrenomAndNom(firstname, name) );
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}

	@Test
	public void testRemoveAuteurInexistant() {
		for (Integer code : new Integer[] {-1, 0, Integer.MAX_VALUE}) {
			try {
				manager.removeAuteur(code);
			} catch(Exception e) {
				e.printStackTrace();
				fail(String.format("GestionnaireBibliotheque.removeAuteur(%s) ne lance pas d'exception", code));
			}
		}
	
	}
	
	private void ok(Auteur received, String expectedFirstname, String expectedLastname) {
		String msg = String.format("GestionnaireBibliotheque.addAuteur(\"%s\", \"%s\")", 
									expectedFirstname, expectedLastname);
		assertNotNull(msg + " != null", received);
		assertNotNull(msg + ".getId() != null", received.getId());
		assertNotNull(msg + ".getPrenom() != null", received.getPrenom());
		assertNotNull(msg + ".getNom() != null", received.getNom());
		assertEquals(msg + ".getPrenom()", expectedFirstname.toLowerCase(), received.getPrenom().toLowerCase());
		assertEquals(msg + ".getNom()", expectedLastname.toLowerCase(), received.getNom().toLowerCase());
	}
}
