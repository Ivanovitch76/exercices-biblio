package be.steformations.java_data.biblio.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.biblio.interfaces.Auteur;
import be.steformations.java_data.biblio.interfaces.GestionnaireBibliotheque;

public class _04_TestAddAuteur {

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
	public void testAddNouvelAuteurOk() {
		long key = System.currentTimeMillis();
		String firstname = "David-" + key;
		String name = "Beazley-" + key;
		try {
			Auteur auteur = manager.addAuteur(firstname, name);
			ok( auteur, firstname, name );
			auteur = manager.getAuteurByCode(auteur.getId());
			ok( auteur, firstname, name );
			auteur = manager.getAuteurByPrenomAndNom(firstname, name);
			ok( auteur, firstname, name );
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}

	@Test
	public void testAddAuteurSansPrenom() {
		long key = System.currentTimeMillis();
		String firstname = "David-" + key;
		String name = null;
		try {
			ko( manager.addAuteur(firstname, name), firstname, name );
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	@Test
	public void testAddAuteurSansNom() {
		long key = System.currentTimeMillis();
		String firstname = null;
		String name = "Beazley-" + key;
		try {
			ko( manager.addAuteur(firstname, name), firstname, name );
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	@Test
	public void testAddAuteurDuplique() {
		long key = System.currentTimeMillis();
		String firstname = "David-" + key;
		String name = "Beazley-" + key;
		try {
			ok( manager.addAuteur(firstname, name), firstname, name );
			ko( manager.addAuteur(firstname, name), firstname, name );
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
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
	
	private void ko(Auteur received, String expectedFirstname, String expectedLastname) {
		String msg = String.format("GestionnaireBibliotheque.addAuteur(\"%s\", \"%s\")", 
				expectedFirstname, expectedLastname);
		assertNull(msg, received);
	}
}
