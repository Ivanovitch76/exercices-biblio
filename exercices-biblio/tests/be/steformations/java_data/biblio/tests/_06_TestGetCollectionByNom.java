package be.steformations.java_data.biblio.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.biblio.interfaces.Collection;
import be.steformations.java_data.biblio.interfaces.GestionnaireBibliotheque;

public class _06_TestGetCollectionByNom {

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
	public void testGetCollectionByNomOk() {
		try {
			ok( manager.getCollectionByNom("Apress"), "Apress" );
			ok( manager.getCollectionByNom("O'Reilly"), "O'Reilly" );
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	@Test
	public void testGetCollectionByKo() {
		try {
			ko(manager.getCollectionByNom(""), "");
			ko(manager.getCollectionByNom(null), null);
			ko(manager.getCollectionByNom("Dunod"), "Dunod");
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	private void ok(Collection received, String expected) {
		String msg = String.format("GestionnaireBibliotheque.getCollectionByNom(\"%s\")", 
									expected);
		assertNotNull(msg + " != null", received);
		assertNotNull(msg + ".getId() != null", received.getId());
		assertNotNull(msg + ".getTitre() != null", received.getNom());
		assertEquals(msg + ".getTitre()", expected.toLowerCase(), received.getNom().toLowerCase());
	}
	
	private void ko(Collection received, String expected) {
		String msg = String.format("GestionnaireBibliotheque.getCollectionByNom(\"%s\")", 
				expected);
		assertNull(msg, received);
	}
}
