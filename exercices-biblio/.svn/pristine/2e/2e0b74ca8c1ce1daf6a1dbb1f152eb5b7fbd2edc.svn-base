package be.steformations.java_data.biblio.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.biblio.interfaces.Auteur;
import be.steformations.java_data.biblio.interfaces.GestionnaireBibliotheque;

public class _03_TestGetAllAuteurs {

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
	public void testGetAllAuteurs() {
		@SuppressWarnings("serial")
		java.util.Map<String, String> fullnames 
			= new java.util.HashMap<String, String>() {{
				put("Mark", "Lutz");
				put("Mark", "Pilgrim");
				put("Alex", "Martelli");
				put("David", "Ascher");
			}};
		try {
			java.util.List<? extends Auteur> authors = manager.getAllAuteurs();
			assertNotNull("GestionnaireBibliotheque.getAllAuteurs() != null", authors);
			for (java.util.Map.Entry<String, String> fullname : fullnames.entrySet()) {
				String firstname = fullname.getKey();
				String lastname = fullname.getValue();
				Auteur a = manager.getAuteurByPrenomAndNom(firstname, lastname);
				assertNotNull("GestionnaireBibliotheque.getAuteurByPrenomAndNom(\"%s\", \"%s\") != null", a);
				assertTrue("GestionnaireBibliotheque.getAllAuteurs().contains(\"%s\", \"%s\")", authors.contains(a));
			}
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
}
