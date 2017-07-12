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

public class _15_TestRemoveLivre {

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
	public void testAddRemoveNouveauLivreOk() {
		long key = System.currentTimeMillis();
		String title = "Python Cookbook - " + key;
		short pages = 706;
		short edition = 3;
		String sdate = "2013-05-23";
		try {
			Auteur davidBeazley = manager.addAuteur("David-" + key, "Beazley-" + key);
			assertNotNull("GestionnaireBibliotheque.addAuteur(\"David-\"" + key + ", \"Beazley-\"" + key +") != null", davidBeazley);
			Auteur brianJones = manager.addAuteur("Brian-" + key, "Jones-" + key);
			assertNotNull("GestionnaireBibliotheque.addAuteur(\"Brian-\"" + key + ", \"Jones-\"" + key +") != null", brianJones);
			Integer[] authors = new Integer[] {davidBeazley.getId(), brianJones.getId()};
			
			TestBook expected = new TestBook(null, title, 2, authors, edition, pages, sdate);
			Livre received = manager.addLivre(expected.title, expected.pages, expected.date, 
										expected.collection, expected.edition, 
										java.util.Arrays.asList(expected.authors));
			ok(received, expected);
			received = manager.getLivreByCode(received.getCode());
			ok(received, expected);
			String code = received.getCode();
			manager.removeLivre(code);
			received = manager.getLivreByCode(code);
			assertNull("GestionnaireBibliotheque.getLivreByCode("+code+")", received);
			java.util.List<? extends Livre> list = manager.searchLivres(null, null, title);
			assertNotNull("GestionnaireBibliotheque.searchLivres(null, null, \""+title+"\")", list);
			assertEquals("GestionnaireBibliotheque.searchLivres(null, null, \""+title+"\").size()", 0, list.size());
			
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
}
