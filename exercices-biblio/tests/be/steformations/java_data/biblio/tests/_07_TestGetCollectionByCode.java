package be.steformations.java_data.biblio.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.biblio.interfaces.Collection;
import be.steformations.java_data.biblio.interfaces.GestionnaireBibliotheque;

public class _07_TestGetCollectionByCode {

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
	public void testGetCollectionCodeOk() {
		try {
			for (String name : new String[] {"Apress", "O'Reilly"}) {
				String msg = String.format("GestionnaireBibliotheque.getCollectionByNom(\"%s\")", name);
				Collection received = manager.getCollectionByNom(name);
				assertNotNull(msg + " != null", received);
				assertNotNull(msg + ".getId() != null", received.getId());
				assertNotNull(msg + ".getNom() != null", received.getNom());
				assertEquals(msg + ".getNom()", name.toLowerCase(), received.getNom().toLowerCase());
				ok( manager.getCollectionByCode(received.getId()), received.getId(), name );
			}

		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	@Test
	public void testGetCollectionByCodeKo() {
		try {
			ko( manager.getCollectionByCode(-1), -1);
			ko( manager.getCollectionByCode(0), 0);
			ko( manager.getCollectionByCode(Integer.MAX_VALUE), Integer.MAX_VALUE);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}

	private void ok(Collection received, Integer code, String expected) {
		String msg = String.format("GestionnaireBibliotheque.getCollectionByCode(%s, \"%s\")", 
									code, expected);
		assertNotNull(msg + " != null", received);
		assertNotNull(msg + ".getId() != null", received.getId());
		assertEquals(msg + ".getId()", code, received.getId());
		assertNotNull(msg + ".getTitre() != null", received.getNom());
		assertEquals(msg + ".getTitre()", expected.toLowerCase(), received.getNom().toLowerCase());
	}
	
	private void ko(Collection received, int code) {
		String msg = String.format("GestionnaireBibliotheque.getCollectionByCode(%s)", code);
		assertNull(msg, received);
	}
}
