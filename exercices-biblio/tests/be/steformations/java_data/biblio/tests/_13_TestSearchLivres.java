package be.steformations.java_data.biblio.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.biblio.interfaces.Auteur;
import be.steformations.java_data.biblio.interfaces.GestionnaireBibliotheque;
import be.steformations.java_data.biblio.interfaces.Livre;
import be.steformations.java_data.biblio.tests.TestsUtils.SearchCriteria;

public class _13_TestSearchLivres {

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
	public void testSearchLivresPython() {
		String[] codes = new String[] {"A108", "A123", "A124", "A256", "B150", "B155"};
		SearchCriteria criteria = new SearchCriteria(null, null, "Python");
		try {
			ok(manager.searchLivres(null, null, "Python"), criteria, codes);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSearchLivresJava() {
		String[] codes = new String[] {};
		SearchCriteria criteria = new SearchCriteria(null, null, "Java");
		try {
			ok(manager.searchLivres(null, null, "Java"), criteria, codes);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSearchLivresAuteurInexistant() {
		String[] codes = new String[] {};
		SearchCriteria criteria = new SearchCriteria(Integer.MAX_VALUE, 2, "Python");
		try {
			ok(manager.searchLivres(Integer.MAX_VALUE, 2, "Python"), criteria, codes);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSearchLivresCollectionInexistante() {
		String[] codes = new String[] {};
		SearchCriteria criteria = new SearchCriteria(1, Integer.MAX_VALUE, "Python");
		try {
			ok(manager.searchLivres(1, Integer.MAX_VALUE, "Python"), criteria, codes);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSearchLivresOreilly() {
		String[] codes = new String[] {"A108", "A123", "A124", "A256", "B150"};
		SearchCriteria criteria = new SearchCriteria(null, 2, null);
		try {
			ok(manager.searchLivres(null, 2, null), criteria, codes);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSearchLivresApress() {
		String[] codes = new String[] {"B155"};
		SearchCriteria criteria = new SearchCriteria(null, 1, null);
		try {
			ok(manager.searchLivres(null, 1, null), criteria, codes);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSearchLivresMarkLutz() {
		String[] codes = new String[] {"A108", "A123", "A124"};
		SearchCriteria criteria = new SearchCriteria(1, null, null);
		try {
			ok(manager.searchLivres(1, null, null), criteria, codes);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSearchLivresMarkPilgrim() {
		String[] codes = new String[] {"B155"};
		SearchCriteria criteria = new SearchCriteria(2, null, null);
		try {
			ok(manager.searchLivres(2, null, null), criteria, codes);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSearchLivresMarkLutzChezOreillyAvecProgrammation() {
		String[] codes = new String[] {"A123"};
		SearchCriteria criteria = new SearchCriteria(1, 2, "Programmation");
		try {
			ok(manager.searchLivres(1, 2, "Programmation"), criteria, codes);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}	
	
	private void ok(java.util.List<? extends Livre> received, SearchCriteria criteria, String[] expectedCodes) {
		String msg = String.format("GestionnaireBibliotheque.searchLivres(%s, %s, %s)", 
									criteria.author, criteria.editor, criteria.word);
		assertNotNull(msg + " != null", received);
		for (String code : expectedCodes) {
			Livre expected = manager.getLivreByCode(code);
			assertNotNull("GestionnaireBibliotheque.getLivreByCode(\""+code+"\") != null", expected);
			
			int index = received.indexOf(expected);
			assertTrue(msg + ".indexOf("+code+") > -1", index > -1);
			
			Livre found = received.get(index);
			
			assertEquals(msg + ".get("+code+").getCode()", expected.getCode(), found.getCode());
			assertEquals(msg + ".get("+code+").getTitre()", expected.getTitre(), found.getTitre());
			assertEquals(msg + ".get("+code+").getDateDeParution()", expected.getDateDeParution(), found.getDateDeParution());
			assertEquals(msg + ".get("+code+").getCollection()", expected.getCollection(), found.getCollection());
			assertEquals(msg + ".get("+code+").getNumeroEdition()", expected.getNumeroEdition(), found.getNumeroEdition());
			assertEquals(msg + ".get("+code+").getNombreDePages()", expected.getNombreDePages(), found.getNombreDePages());
			
			for (Auteur author : expected.getAuteurs()) {
				assertTrue(msg + ".get("+code+").getAuteurs().contains("+author.getId()+")", found.getAuteurs().contains(author));
			}
		}
	}
}
