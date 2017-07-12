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

public class _21_TestAddReservation {

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
	public void testAddReservationOk() {
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

			msg = "GestionnaireBibliotheque.getReservationsByLivreCode(\""+pythoncookbook.getCode()+"\")";
			java.util.List<? extends Reservation> bookings = manager.getReservationsByLivreCode(pythoncookbook.getCode());
			
			assertNotNull(msg + " != null", bookings);
			assertEquals( msg + ".size()", 1, bookings.size());
			Reservation booking = bookings.get(0);
			assertNotNull(msg + ".get(0) != null", booking);
			assertNotNull(msg + ".get(0).getId() != null", booking.getId());
			assertNotNull(msg + ".get(0).getEmprunteur() != null", booking.getEmprunteur());
			assertNotNull(msg + ".get(0).getLivre() != null", booking.getLivre());
			assertNotNull(msg + ".get(0).getDateDeReservation() != null", booking.getDateDeReservation());
			assertEquals(msg + ".get(0).getEmprunteur()", clarkkent, booking.getEmprunteur());
			assertNotNull(msg + ".get(0).getLivre().getCode() != null", booking.getLivre().getCode()); 
			assertEquals(msg + ".get(0).getLivre().getCode()", pythoncookbook.getCode(), booking.getLivre().getCode());
			assertNotNull(msg + ".get(0).getLivre().getAuteurs() != null", booking.getLivre().getAuteurs());
			assertEquals(msg + ".get(0).getLivre().getAuteurs().size()", 1, booking.getLivre().getAuteurs().size());
			assertEquals(msg + ".get(0).getLivre().getAuteurs().get(0).getId()", davidBeazley.getId(), booking.getLivre().getAuteurs().get(0).getId());

			msg = "GestionnaireBibliotheque.getReservationsByEmprunteurCode("+clarkkent.getId()+")";
			bookings = manager.getReservationsByEmprunteurCode(clarkkent.getId());
			
			assertNotNull(msg + " != null", bookings);
			assertEquals( msg + ".size()", 1, bookings.size());
			booking = bookings.get(0);
			assertNotNull(msg + ".get(0) != null", booking);
			assertNotNull(msg + ".get(0).getId() != null", booking.getId());
			assertNotNull(msg + ".get(0).getEmprunteur() != null", booking.getEmprunteur());
			assertNotNull(msg + ".get(0).getLivre() != null", booking.getLivre());
			assertNotNull(msg + ".get(0).getDateDeReservation() != null", booking.getDateDeReservation());
			assertEquals(msg + ".get(0).getEmprunteur()", clarkkent, booking.getEmprunteur());
			assertNotNull(msg + ".get(0).getLivre().getCode() != null", booking.getLivre().getCode()); 
			assertEquals(msg + ".get(0).getLivre().getCode()", pythoncookbook.getCode(), booking.getLivre().getCode());
			assertNotNull(msg + ".get(0).getLivre().getAuteurs() != null", booking.getLivre().getAuteurs());
			assertEquals(msg + ".get(0).getLivre().getAuteurs().size()", 1, booking.getLivre().getAuteurs().size());
			assertEquals(msg + ".get(0).getLivre().getAuteurs().get(0).getId()", davidBeazley.getId(), booking.getLivre().getAuteurs().get(0).getId());

			
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	@Test
	public void testGetReservationsByEmprunteurCodeKo() {
		try {
			ko(manager.addReservation(0, "B155"), 0, "B155");
			ko(manager.addReservation(Integer.MAX_VALUE, "B155"), Integer.MAX_VALUE, "B155");
			ko(manager.addReservation(Integer.MAX_VALUE, "B155"), Integer.MAX_VALUE, "B155");
			ko(manager.addReservation(1, "XXXX"), 1, "XXXX");
			ko(manager.addReservation(1, null), 1, null);
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	private void ko(Reservation received, int emprunteur, String livre) {
		String msg = "GestionnaireBibliotheque.addReservation("+emprunteur+", \""+livre+"\")";
		assertNull(msg, received);
	}
}
