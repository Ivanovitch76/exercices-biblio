package be.steformations.java_data.biblio.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.biblio.interfaces.GestionnaireBibliotheque;
import be.steformations.java_data.biblio.interfaces.Livre;

public class _12_TestGetAllLivres {

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
	public void testGetAllLivres() {
		try {
			java.util.List<? extends Livre> books = manager.getAllLivres();
			assertNotNull("GestionnaireBibliotheque.getAllLivres() != null", books);
			for (String code : new String[] { "A108", "A123", "A124", "A256", "B150", "B155" }) {
				Livre received = manager.getLivreByCode(code);
				assertNotNull("GestionnaireBibliotheque.getCollectionByCode("+code+") != null", received);
				assertTrue("GestionnaireBibliotheque.getAllLivres().contains(\""+code+"\")", books.contains(received));
			}
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
}
