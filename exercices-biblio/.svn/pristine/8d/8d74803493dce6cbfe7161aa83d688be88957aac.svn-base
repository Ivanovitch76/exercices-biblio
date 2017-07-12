package be.steformations.java_data.biblio.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.biblio.interfaces.Collection;
import be.steformations.java_data.biblio.interfaces.GestionnaireBibliotheque;

public class _10_TestRemoveCollection {

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
	public void testAddEtRemoveCollectionOk() {
		long key = System.currentTimeMillis();
		String name = "Dunod-" + key;
		try {
			Collection received = manager.addCollection(name);
			ok(received, name);
			Integer code = received.getId();
			received = manager.getCollectionByCode(code);
			ok(received, name);
			manager.removeCollection(code);
			assertNull(String.format("Gestionnaire.getCollectionByCode(%s) == null", code), 
					manager.getCollectionByCode(code) );
			assertNull(String.format("Gestionnaire.getCollectionByNom(\"%s\") == null", name), 
					manager.getCollectionByNom(name) );
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}

	@Test
	public void testRemoveCollectionInexistante() {
		for (Integer code : new Integer[] {-1, 0, Integer.MAX_VALUE}) {
			try {
				manager.removeCollection(code);
			} catch(Exception e) {
				e.printStackTrace();
				fail(String.format("GestionnaireBibliotheque.removeCollection(%s) ne lance pas d'exception", code));
			}
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
}
