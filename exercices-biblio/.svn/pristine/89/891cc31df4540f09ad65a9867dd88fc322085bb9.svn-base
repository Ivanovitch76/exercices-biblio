package be.steformations.java_data.biblio.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import be.steformations.java_data.biblio.interfaces.GestionnaireBibliotheque;

public class _00_TestGetGestionnaireBibliotheque {

	@Test
	public void testGetGestionnaireBibliotheque() {
		try {
			GestionnaireBibliotheque manager = FabriqueTests.getGestionnaireBibliotheque();
			assertNotNull("FabriqueTests.getGestionnaireBibliotheque() != null", manager);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
