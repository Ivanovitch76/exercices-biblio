package be.steformations.java_data.biblio.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.biblio.interfaces.Auteur;
import be.steformations.java_data.biblio.interfaces.Collection;
import be.steformations.java_data.biblio.interfaces.GestionnaireBibliotheque;
import be.steformations.java_data.biblio.interfaces.Livre;
import be.steformations.java_data.biblio.tests.TestsUtils.TestBook;

public class _11_TestGetLivreByCode {

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
	public void testGetLivreByCode() {
		java.util.List<TestBook> books = TestsUtils.getAllBooks();
		try {
			for (TestBook book : books) {
				String code = book.code;
				Livre received = manager.getLivreByCode(code);
				ok(received, code, book);
			}
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	@Test
	public void testGetLivreByCodeKo() {
		try {
			ko( manager.getLivreByCode(null), null );
			ko( manager.getLivreByCode(""), "" );
			ko( manager.getLivreByCode("XXXX"), "XXXX" );
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}

	private void ok(Livre received, String code, TestBook expected) {
		Collection collection = manager.getCollectionByCode(expected.collection);
		assertNotNull("GestionnaireBibliotheque.getCollectionByCode("+expected.collection+") != null", collection);
		
		java.util.List<Auteur> authors = new java.util.ArrayList<Auteur>();
		for (Integer id : expected.authors) {
			Auteur a = manager.getAuteurByCode(id);
			assertNotNull("GestionnaireBibliotheque.getAuteurByCode("+id+") != null", a);
		}
		
		String msg = String.format("GestionnaireBibliotheque.getLivreByCode(\"%s\")", code);
		assertNotNull(msg + " != null", received);
		assertNotNull(msg + ".getCode() != null", received.getCode());
		assertNotNull(msg + ".getTitre() != null", received.getTitre());
		assertNotNull(msg + ".getDateDeParution() != null", received.getDateDeParution());
		assertNotNull(msg + ".getAuteurs() != null", received.getAuteurs());

		assertEquals(msg + ".getCode()", code, received.getCode());
		assertEquals(msg + ".getTitre()", expected.title, received.getTitre());
		assertEquals(msg + ".getDateDeParution()", expected.date, received.getDateDeParution());
		assertEquals(msg + ".getCollection()", collection, received.getCollection());
		assertEquals(msg + ".getNumeroEdition()", expected.edition, received.getNumeroEdition());
		assertEquals(msg + ".getNombreDePages()", expected.pages, received.getNombreDePages());
		
		for (Auteur author : authors) {
			assertTrue(msg + ".getAuteurs().contains("+author.getId()+")", received.getAuteurs().contains(author));
		}

	}
	
	private void ko(Livre received, String code) {
		String msg = String.format("GestionnaireBibliotheque.getLivreCode(%s)", code);
		assertNull(msg, received);
	}
}
