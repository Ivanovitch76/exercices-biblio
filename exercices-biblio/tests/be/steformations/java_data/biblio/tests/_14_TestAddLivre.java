package be.steformations.java_data.biblio.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.biblio.interfaces.Auteur;
import be.steformations.java_data.biblio.interfaces.Collection;
import be.steformations.java_data.biblio.interfaces.GestionnaireBibliotheque;
import be.steformations.java_data.biblio.interfaces.Livre;
import be.steformations.java_data.biblio.tests.TestsUtils.TestBook;

public class _14_TestAddLivre {

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
	public void testAddNouveauLivreOk() {
		long key = System.currentTimeMillis();
		String title = "Python Cookbook - " + key;
		short pages = 706;
		short edition = 3;
		String sdate = "2013-05-23";
		try {
			Auteur davidBeazley = manager.addAuteur("David-" + key, "Beazley-" + key);
			assertNotNull("GestionnaireBibliotheque.addAuteur(\"David-\"" + key + ", \"Beazley-\"" + key +") != null", davidBeazley);
			assertNotNull("GestionnaireBibliotheque.addAuteur(\"David-\"" + key + ", \"Beazley-\"" + key +").getId() != null", davidBeazley.getId());
			Auteur brianJones = manager.addAuteur("Brian-" + key, "Jones-" + key);
			assertNotNull("GestionnaireBibliotheque.addAuteur(\"Brian-\"" + key + ", \"Jones-\"" + key +") != null", brianJones);
			assertNotNull("GestionnaireBibliotheque.addAuteur(\"Brian-\"" + key + ", \"Jones-\"" + key +").getId() != null", brianJones.getId());
			Integer[] authors = new Integer[] {davidBeazley.getId(), brianJones.getId()};
			
			TestBook expected = new TestBook(null, title, 2, authors, edition, pages, sdate);
			Livre received = manager.addLivre(expected.title, expected.pages, expected.date, 
										expected.collection, expected.edition, 
										java.util.Arrays.asList(expected.authors));
			ok(received, expected);
			received = manager.getLivreByCode(received.getCode());
			ok(received, expected);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}

	@Test
	public void testAddLivreSansTitre() {
		short pages = 706;
		short edition = 3;
		String sdate = "2013-05-23";
		try {
			Integer[] authors = new Integer[] {1, 2};
			TestBook expected = new TestBook(null, null, 2, authors, edition, pages, sdate);
			Livre received = manager.addLivre(expected.title, expected.pages, expected.date, 
										expected.collection, expected.edition, 
										java.util.Arrays.asList(expected.authors));
			ko(received, expected);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	@Test
	public void testAddLivreAvecCollectionInexistante() {
		try {
			long key = System.currentTimeMillis();
			String title = "Python Cookbook - " + key;
			short pages = 706;
			short edition = 3;
			String sdate = "2013-05-23";
			Integer[] authors = new Integer[] {1, 2};
			
			TestBook expected = new TestBook(null, title, Integer.MAX_VALUE, authors, edition, pages, sdate);
			Livre received = manager.addLivre(expected.title, expected.pages, expected.date, 
										expected.collection, expected.edition, 
										java.util.Arrays.asList(expected.authors));
			ko(received, expected);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}	
	
	@Test
	public void testAddLivreAvecAuteurInconnu() {
		try {
			long key = System.currentTimeMillis();
			String title = "Python Cookbook - " + key;
			short pages = 706;
			short edition = 3;
			String sdate = "2013-05-23";
			Integer[] authors = new Integer[] {1, Integer.MAX_VALUE};
			
			TestBook expected = new TestBook(null, title, 2, authors, edition, pages, sdate);
			Livre received = manager.addLivre(expected.title, expected.pages, expected.date, 
										expected.collection, expected.edition, 
										java.util.Arrays.asList(expected.authors));
			ko(received, expected);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}	
	
	@Test
	public void testAddLivreDuplique() {
		long key = System.currentTimeMillis();
		String title = "Python Cookbook - " + key;
		short pages = 706;
		short edition = 3;
		String sdate = "2013-05-23";
		try {
			Auteur davidBeazley = manager.addAuteur("David-" + key, "Beazley-" + key);
			assertNotNull("GestionnaireBibliotheque.addAuteur(\"David-\"" + key + ", \"Beazley-\"" + key +") != null", davidBeazley);
			assertNotNull("GestionnaireBibliotheque.addAuteur(\"David-\"" + key + ", \"Beazley-\"" + key +").getId() != null", davidBeazley.getId());
			Auteur brianJones = manager.addAuteur("Brian-" + key, "Jones-" + key);
			assertNotNull("GestionnaireBibliotheque.addAuteur(\"Brian-\"" + key + ", \"Jones-\"" + key +") != null", brianJones);
			assertNotNull("GestionnaireBibliotheque.addAuteur(\"Brian-\"" + key + ", \"Jones-\"" + key +").getId() != null", brianJones.getId());
			Integer[] authors = new Integer[] {davidBeazley.getId(), brianJones.getId()};
			
			TestBook expected = new TestBook(null, title, 2, authors, edition, pages, sdate);
			Livre received = manager.addLivre(expected.title, expected.pages, expected.date, 
										expected.collection, expected.edition, 
										java.util.Arrays.asList(expected.authors));
			ok(received, expected);
			received = manager.getLivreByCode(received.getCode());
			ok(received, expected);
			assertNull(String.format("GestionnaireBibliotheque.addLivre(\"%s\", %s, %s, %s, %s, %s)", 
									expected.title, expected.pages, expected.date, 
									expected.collection, expected.edition, expected.authors),
					   manager.addLivre(expected.title, expected.pages, expected.date, 
											expected.collection, expected.edition, 
											java.util.Arrays.asList(expected.authors)));
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}	
	
	private void ok(Livre received, TestBook expected) {
		Collection collection = manager.getCollectionByCode(expected.collection);
		assertNotNull("GestionnaireBibliotheque.getCollectionByCode("+expected.collection+") != null", collection);
		
		java.util.List<Auteur> authors = new java.util.ArrayList<Auteur>();
		for (Integer id : expected.authors) {
			Auteur a = manager.getAuteurByCode(id);
			assertNotNull("GestionnaireBibliotheque.getAuteurByCode("+id+") != null", a);
		}
		
		String msg = String.format("GestionnaireBibliotheque.addLivre(\"%s\", %s, %s, %s, %s, %s)", 
									expected.title, expected.pages, expected.date, 
									expected.collection, expected.edition, expected.authors);
		
		assertNotNull(msg + " != null", received);
		assertEquals(msg + ".getTitre()", expected.title, received.getTitre());
		assertEquals(msg + ".getDateDeParution()", expected.date, received.getDateDeParution());
		assertEquals(msg + ".getCollection()", collection, received.getCollection());
		assertEquals(msg + ".getNumeroEdition()", expected.edition, received.getNumeroEdition());
		assertEquals(msg + ".getNombreDePages()", expected.pages, received.getNombreDePages());
		
		for (Auteur author : authors) {
			assertTrue(msg + ".getAuteurs().contains("+author.getId()+")", received.getAuteurs().contains(author));
		}
	}
	
	private void ko(Livre received, TestBook expected) {
		String msg = String.format("GestionnaireBibliotheque.addLivre(\"%s\", %s, %s, %s, %s, %s)", 
									expected.title, expected.pages, expected.date, 
									expected.collection, expected.edition, expected.authors);
		assertNull(msg, received);
	}
}
