package be.steformations.java_data.biblio.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.biblio.interfaces.Auteur;
import be.steformations.java_data.biblio.interfaces.Emprunteur;
import be.steformations.java_data.biblio.interfaces.GestionnaireBibliotheque;
import be.steformations.java_data.biblio.interfaces.Livre;
import be.steformations.java_data.biblio.interfaces.Reservation;
import be.steformations.java_data.biblio.tests.TestsUtils.TestBook;
import be.steformations.java_data.biblio.tests.TestsUtils.TestBorrower;

public class _22_TestRemoveReservation {

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
	public void testRemoveReservationOk() {
		try {
			long key = System.currentTimeMillis();
			String firstname = "Clark-" + key;
			String name = "Kent-" + key;
			String email = "clark.kent." + key + "@gotham.com";
			String birthdate = "1938-06-01";
			String phone = "043669050";
			TestBorrower borrower = new TestBorrower(firstname, name, email, phone, birthdate);
			Emprunteur clarkkent = manager.addEmprunteur(borrower.firstname, borrower.lastname, borrower.email, borrower.ddn, borrower.phone);
			
			String title = "Python Cookbook - " + key;
			short pages = 706;
			short edition = 3;
			String sdate = "2013-05-23";

			Auteur davidBeazley = manager.addAuteur("David-" + key, "Beazley-" + key);
			assertNotNull("GestionnaireBibliotheque.addAuteur(\"David-\"" + key + ", \"Beazley-\"" + key +") != null", davidBeazley);
			
			TestBook book = new TestBook(null, title, 2, new Integer[] {davidBeazley.getId()}, edition, pages, sdate);
			Livre pythoncookbook = manager.addLivre(book.title, book.pages, book.date, 
										book.collection, book.edition, 
										java.util.Arrays.asList(book.authors));
			
			String msg = "GestionnaireBibliotheque.addReservation("+clarkkent.getId()+", \""+pythoncookbook.getCode()+"\")";
			Reservation received = manager.addReservation(clarkkent.getId(), pythoncookbook.getCode());

			assertNotNull(msg + " != null", received);
			assertNotNull(msg + ".getId() != null", received.getId());
			assertNotNull(msg + ".getEmprunteur() != null", received.getEmprunteur());
			assertNotNull(msg + ".getLivre() != null", received.getLivre());
			assertNotNull(msg + ".getDateDeReservation() != null", received.getDateDeReservation());
			assertEquals(msg + ".getEmprunteur()", clarkkent, received.getEmprunteur());
			assertNotNull(msg + ".getLivre().getCode() != null", received.getLivre().getCode()); 
			assertEquals(msg + ".getLivre().getCode()", pythoncookbook.getCode(), received.getLivre().getCode());
			assertNotNull(msg + ".getLivre().getAuteurs() != null", received.getLivre().getAuteurs());
			assertEquals(msg + ".getLivre().getAuteurs().size()", 1, received.getLivre().getAuteurs().size());
			assertEquals(msg + ".getLivre().getAuteurs().get(0).getId()", davidBeazley.getId(), received.getLivre().getAuteurs().get(0).getId());

			manager.removeReservation(received.getId());
			
			msg = "GestionnaireBibliotheque.getReservationByCode("+received.getId()+"\")";
			received = manager.getReservationByCode(received.getId());
			assertNull(msg, received);
			
			msg = "GestionnaireBibliotheque.getReservationsByLivreCode(\""+pythoncookbook.getCode()+"\")";
			java.util.List<? extends Reservation> bookings = manager.getReservationsByLivreCode(pythoncookbook.getCode());
			assertNotNull(msg + " != null", bookings);
			assertEquals( msg + ".size()", 0, bookings.size());
			
			msg = "GestionnaireBibliotheque.getReservationsByEmprunteurCode("+clarkkent.getId()+")";
			bookings = manager.getReservationsByEmprunteurCode(clarkkent.getId());
			assertNotNull(msg + " != null", bookings);
			assertEquals( msg + ".size()", 0, bookings.size());
			
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	@Test
	public void testRemoveReservationsKo() {
		try {

		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
}
