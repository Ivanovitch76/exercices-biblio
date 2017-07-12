package be.steformations.java_data.biblio.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.biblio.interfaces.Collection;
import be.steformations.java_data.biblio.interfaces.GestionnaireBibliotheque;

public class _09_TestAddCollection {

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
	public void testAddNouvelleCollectionOk() {
		long key = System.currentTimeMillis();
		String name = "Dunod-" + key;
		try {
			Collection received = manager.addCollection(name);
			ok(received, name);
			received = manager.getCollectionByCode(received.getId());
			ok(received, name);
			received = manager.getCollectionByNom(name);
			ok(received, name);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}

	@Test
	public void testAddCollectionSansNom() {
		try {
			ko( manager.addCollection(null), null);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	@Test
	public void testAddCollectionDupliquee() {
		long key = System.currentTimeMillis();
		String name = "Dunod-" + key;
		try {
			ok(manager.addCollection(name), name);
			ko(manager.addCollection(name), name);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}	
	
	private void ok(Collection received, String expected) {
		String msg = String.format("GestionnaireBibliotheque.addCollection(\"%s\")", 
									expected);
		assertNotNull(msg + " != null", received);
		assertNotNull(msg + ".getId() != null", received.getId());
		assertNotNull(msg + ".getTitre() != null", received.getNom());
		assertEquals(msg + ".getTitre()", expected.toLowerCase(), received.getNom().toLowerCase());
	}
	
	private void ko(Collection received, String expected) {
		String msg = String.format("GestionnaireBibliotheque.addCollection(\"%s\")", 
				expected);
		assertNull(msg, received);
	}
}
