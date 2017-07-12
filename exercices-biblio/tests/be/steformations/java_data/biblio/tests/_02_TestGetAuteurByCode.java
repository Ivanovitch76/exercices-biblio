package be.steformations.java_data.biblio.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.biblio.interfaces.Auteur;
import be.steformations.java_data.biblio.interfaces.GestionnaireBibliotheque;

public class _02_TestGetAuteurByCode {

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
	public void testGetAuteurByCode() {
		@SuppressWarnings("serial")
		java.util.Map<String, String> fullnames 
			= new java.util.HashMap<String, String>() {{
				put("Mark", "Lutz");
				put("Mark", "Pilgrim");
				put("Alex", "Martelli");
				put("David", "Ascher");
			}};
		try {
			for (java.util.Map.Entry<String, String> fullname : fullnames.entrySet()) {
				String firstname = fullname.getKey();
				String lastname = fullname.getValue();
				Auteur a = manager.getAuteurByPrenomAndNom(firstname, lastname);
				assertNotNull("GestionnaireBibliotheque.getAuteurByPrenomAndNom(\""+firstname+"\", \""+lastname+"\") != null", a);
				assertNotNull("GestionnaireBibliotheque.getAuteurByPrenomAndNom(\""+firstname+"\", \""+lastname+"\").getId() != null", a.getId());
				ok( manager.getAuteurByCode(a.getId()), a.getId(), firstname, lastname );
			}
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	@Test
	public void testGetAuteurByCodeKo() {
		try {
			ko( manager.getAuteurByCode(-1), -1 );
			ko( manager.getAuteurByCode(0), 0 );
			ko( manager.getAuteurByCode(Integer.MAX_VALUE), Integer.MAX_VALUE );
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}

	private void ok(Auteur received, Integer code, String expectedFirstname, String expectedLastname) {
		String msg = String.format("GestionnaireBibliotheque.getAuteurByCode(%s)", code);
		assertNotNull(msg + " != null", received);
		assertNotNull(msg + ".getId() != null", received.getId());
		assertNotNull(msg + ".getPrenom() != null", received.getPrenom());
		assertNotNull(msg + ".getNom() != null", received.getNom());
		assertEquals(msg + ".getId()", code, received.getId());
		assertEquals(msg + ".getPrenom()", expectedFirstname.toLowerCase(), received.getPrenom().toLowerCase());
		assertEquals(msg + ".getNom()", expectedLastname.toLowerCase(), received.getNom().toLowerCase());
	}
	
	private void ko(Auteur received, Integer code) {
		String msg = String.format("GestionnaireBibliotheque.getAuteurByCode(%s)", code);
		assertNull(msg, received);
	}
}
