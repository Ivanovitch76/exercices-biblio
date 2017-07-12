package be.steformations.java_data.biblio.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.biblio.interfaces.Collection;
import be.steformations.java_data.biblio.interfaces.GestionnaireBibliotheque;

public class _08_TestGetAllCollections {

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
	public void testGetAllCollections() {
		try {
			java.util.List<? extends Collection> all = manager.getAllCollections();
			assertNotNull("GestionnaireBibliotheque.getAllCollections() != null", all);
			for (String name : new String[] {"Apress", "O'Reilly"}) {
				String msg = String.format("GestionnaireBibliotheque.getCollectionByNom(\"%s\")", name);
				Collection received = manager.getCollectionByNom(name);
				assertNotNull(msg + " != null", received);
				assertNotNull(msg + ".getId() != null", received.getId());
				assertNotNull(msg + ".getTitre() != null", received.getNom());
				assertEquals(msg + ".getTitre()", name.toLowerCase(), received.getNom().toLowerCase());
				assertTrue(
					String.format("GestionnaireBibliotheque.getAllCollections().contains(\"%s\")", received.getNom()), 
					all.contains(received));
			}

		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
}
