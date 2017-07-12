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

public class _17_TestAddEmprunteur {

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
	public void testAddEmprunteurOk() {
		long key = System.currentTimeMillis();
		String firstname = "Clark-" + key;
		String name = "Kent-" + key;
		String email = "clark.kent." + key + "@gotham.com";
		String birthdate = "1938-06-01";
		String phone = "043669050";
		try {
			TestBorrower expected = new TestBorrower(firstname, name, email, phone, birthdate);
			ok(manager.addEmprunteur(expected.firstname, expected.lastname, expected.email, expected.ddn, expected.phone), expected);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	@Test
	public void testAddEmprunteurSansPrenom() {
		long key = System.currentTimeMillis();
		String firstname = null;
		String name = "Kent-" + key;
		String email = "clark.kent." + key + "@gotham.com";
		String birthdate = "1938-06-01";
		String phone = "043669050";
		try {
			TestBorrower expected = new TestBorrower(firstname, name, email, phone, birthdate);
			ko(manager.addEmprunteur(expected.firstname, expected.lastname, expected.email, expected.ddn, expected.phone), expected);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}

	@Test
	public void testAddEmprunteurSansNom() {
		long key = System.currentTimeMillis();
		String firstname = "Clark-" + key;
		String name = null;
		String email = "clark.kent." + key + "@gotham.com";
		String birthdate = "1938-06-01";
		String phone = "043669050";
		try {
			TestBorrower expected = new TestBorrower(firstname, name, email, phone, birthdate);
			ko(manager.addEmprunteur(expected.firstname, expected.lastname, expected.email, expected.ddn, expected.phone), expected);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	@Test
	public void testAddEmprunteurSansEmail() {
		long key = System.currentTimeMillis();
		String firstname = "Clark-" + key;
		String name = "Kent-" + key;
		String email = null;
		String birthdate = "1938-06-01";
		String phone = "043669050";
		try {
			TestBorrower expected = new TestBorrower(firstname, name, email, phone, birthdate);
			ko(manager.addEmprunteur(expected.firstname, expected.lastname, expected.email, expected.ddn, expected.phone), expected);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	@Test
	public void testAddEmprunteurPrenomNomDdnDuplique() {
		long key = System.currentTimeMillis();
		String firstname = "Clark-" + key;
		String name = "Kent-" + key;
		String email = "clark.kent." + key + "@gotham.com";
		String birthdate = "1938-06-01";
		String phone = "043669050";
		try {
			TestBorrower expected = new TestBorrower(firstname, name, email, phone, birthdate);
			ok(manager.addEmprunteur(expected.firstname, expected.lastname, expected.email, expected.ddn, expected.phone), expected);
			ko(manager.addEmprunteur(expected.firstname, expected.lastname, expected.email, expected.ddn, expected.phone), expected);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	@Test
	public void testAddEmprunteurEmailDuplique() {
		long key = System.currentTimeMillis();
		String firstname = "Clark-" + key;
		String name = "Kent-" + key;
		String email = "betty.boop@hollywood.com";
		String birthdate = "1938-06-01";
		String phone = "043669050";
		try {
			TestBorrower expected = new TestBorrower(firstname, name, email, phone, birthdate);
			ko(manager.addEmprunteur(expected.firstname, expected.lastname, expected.email, expected.ddn, expected.phone), expected);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	private void ok(Emprunteur received, TestBorrower expected) {
		String msg = String.format(
				"GestionnaireBibliotheque.addEmprunteur(\"%s\", \"%s\", \"%s\", \"%s\", \"%s\")", 
				expected.firstname, expected.lastname, expected.email, expected.ddn, expected.phone);
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
	
	private void ko(Emprunteur received, TestBorrower expected) {
		String msg = String.format(
				"GestionnaireBibliotheque.addEmprunteur(\"%s\", \"%s\", \"%s\", \"%s\", \"%s\")", 
				expected.firstname, expected.lastname, expected.email, expected.ddn, expected.phone);
		assertNull(msg, received);
	}
}
