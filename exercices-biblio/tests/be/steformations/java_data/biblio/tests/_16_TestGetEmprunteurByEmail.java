package be.steformations.java_data.biblio.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.biblio.interfaces.Emprunteur;
import be.steformations.java_data.biblio.interfaces.GestionnaireBibliotheque;
import be.steformations.java_data.biblio.tests.TestsUtils.TestBorrower;

public class _16_TestGetEmprunteurByEmail {

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
	public void testGetEmprunteurByEmailOk() {
		try {
			TestBorrower expected = new TestBorrower("Betty", "Boop", "betty.boop@hollywood.com", "043669050", "1930-08-09");
			ok( manager.getEmprunteurByEmail(expected.email), expected);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	@Test
	public void testGetEmprunteurByEmailKo() {
		try {
			ko(manager.getEmprunteurByEmail(""), "");
			ko(manager.getEmprunteurByEmail(null), null);
			ko(manager.getEmprunteurByEmail("batman@gotham.com"), "batman@gotham.com");
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}

	private void ok(Emprunteur received, TestBorrower expected) {
		String msg = String.format("GestionnaireBibliotheque.getEmprunteurByEmail(\"%s\")", expected.email);
		assertNotNull(msg + " != null", received);
		assertNotNull(msg + ".getId() != null", received.getId());
		assertNotNull(msg + ".getEmail() != null", received.getEmail());
		assertNotNull(msg + ".getNom() != null", received.getNom());
		assertNotNull(msg + ".getPrenom() != null", received.getPrenom());
		assertNotNull(msg + ".getTelephone() != null", received.getTelephone());
		assertNotNull(msg + ".getDateDeNaissance() != null", received.getDateDeNaissance());
		assertEquals(msg + ".getEmail()", expected.email.toLowerCase(), received.getEmail().toLowerCase());
		assertEquals(msg + ".getPrenom()", expected.firstname.toLowerCase(), received.getPrenom().toLowerCase());
		assertEquals(msg + ".getNom()", expected.lastname.toLowerCase(), received.getNom().toLowerCase());
		assertEquals(msg + ".getTelephone()", expected.phone, received.getTelephone());
		assertEquals(msg + ".getDateDeNaissance()", expected.ddn, received.getDateDeNaissance());
	}
	
	private void ko(Emprunteur received, String email) {
		String msg = String.format("GestionnaireBibliotheque.getEmprunteurByEmail(\"%s\")", email);
		assertNull(msg, received);
	}
}
