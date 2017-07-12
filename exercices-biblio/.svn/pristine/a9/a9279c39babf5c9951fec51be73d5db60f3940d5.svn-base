package be.steformations.java_data.biblio.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.biblio.interfaces.Auteur;
import be.steformations.java_data.biblio.interfaces.GestionnaireBibliotheque;

public class _01_TestGetAuteurByPrenomEtNom {

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
	public void testGetAuteurByPrenomAndNomOk() {
		try {
			ok( manager.getAuteurByPrenomAndNom("Mark", "Lutz"), "Mark", "Lutz" );
			ok( manager.getAuteurByPrenomAndNom("Mark", "Pilgrim"), "Mark", "Pilgrim" );
			ok( manager.getAuteurByPrenomAndNom("Alex", "Martelli"), "Alex", "Martelli" );
			ok( manager.getAuteurByPrenomAndNom("David", "Ascher"), "David", "Ascher" );
			ok( manager.getAuteurByPrenomAndNom("david", "ascher"), "David", "Ascher" );
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	@Test
	public void testGetAuteurByPrenomAndNomKo() {
		try {
			ko( manager.getAuteurByPrenomAndNom("Mark", ""), "Mark", "" );
			ko( manager.getAuteurByPrenomAndNom("", "Lutz"), "", "Lutz" );
			ko( manager.getAuteurByPrenomAndNom("Mark", null), "Mark", null );
			ko( manager.getAuteurByPrenomAndNom(null, "Lutz"), null, "Lutz" );
			ko( manager.getAuteurByPrenomAndNom("", ""), "", "" );
			ko( manager.getAuteurByPrenomAndNom(null, null), null, null );
			ko( manager.getAuteurByPrenomAndNom("Betty", "Boop"), "Betty", "Boop" );
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}

	private void ok(Auteur received, String expectedFirstname, String expectedLastname) {
		String msg = String.format("GestionnaireBibliotheque.getAuteurByPrenomAndNom(\"%s\", \"%s\")", 
									expectedFirstname, expectedLastname);
		assertNotNull(msg + " != null", received);
		assertNotNull(msg + ".getId() != null", received.getId());
		assertNotNull(msg + ".getPrenom() != null", received.getPrenom());
		assertNotNull(msg + ".getNom() != null", received.getNom());
		assertEquals(msg + ".getPrenom()", expectedFirstname.toLowerCase(), received.getPrenom().toLowerCase());
		assertEquals(msg + ".getNom()", expectedLastname.toLowerCase(), received.getNom().toLowerCase());
	}
	
	private void ko(Auteur received, String expectedFirstname, String expectedLastname) {
		String msg = String.format("GestionnaireBibliotheque.getAuteurByPrenomAndNom(\"%s\", \"%s\")", 
				expectedFirstname, expectedLastname);
		assertNull(msg, received);
	}
}
