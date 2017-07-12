package be.steformations.java_data.biblio.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.biblio.interfaces.Emprunteur;
import be.steformations.java_data.biblio.interfaces.GestionnaireBibliotheque;
import be.steformations.java_data.biblio.interfaces.Livre;
import be.steformations.java_data.biblio.interfaces.Reservation;

public class _19_TestGetReservationsByLivreCode {

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
	public void testGetReservationsByEmprunteurCodeOk() {
		try {
			String email = "betty.boop@hollywood.com";
			Emprunteur bettyBoop = manager.getEmprunteurByEmail(email);
			assertNotNull("GestionnaireBibliotheque.getEmprunteurByEmail(\""+email+"\") != null", bettyBoop);
			
			Livre a123 = manager.getLivreByCode("A123");
			assertNotNull("GestionnaireBibliotheque.getLivreByCode(\"A123\") != null", a123);
			
			String msg = "GestionnaireBibliotheque.getReservationsByLivreCode("+a123.getCode()+")";
			
			java.util.List<? extends Reservation> bookings = manager.getReservationsByLivreCode(a123.getCode());
			
			assertNotNull(msg + " != null", bookings);
			assertEquals( msg + ".size()", 1, bookings.size());
			Reservation booking = bookings.get(0);
			assertNotNull(msg + ".get(0) != null", booking);
			assertNotNull(msg + ".get(0).getId() != null", booking.getId());
			assertNotNull(msg + ".get(0).getEmprunteur() != null", booking.getEmprunteur());
			assertNotNull(msg + ".get(0).getLivre() != null", booking.getLivre());
			assertNotNull(msg + ".get(0).getDateDeReservation() != null", booking.getDateDeReservation());
			assertEquals(msg + ".get(0).getEmprunteur()", bettyBoop, booking.getEmprunteur());
			assertNotNull(msg + ".get(0).getLivre().getCode() != null", booking.getLivre().getCode()); 
			assertEquals(msg + ".get(0).getLivre().getCode()", a123.getCode(), booking.getLivre().getCode());
			assertNotNull(msg + ".get(0).getLivre().getAuteurs() != null", booking.getLivre().getAuteurs());
			assertEquals(msg + ".get(0).getLivre().getAuteurs().size()", 1, booking.getLivre().getAuteurs().size());
			assertEquals(msg + ".get(0).getLivre().getAuteurs().get(0).getId()", new Integer(1), booking.getLivre().getAuteurs().get(0).getId());
			
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	@Test
	public void testGetReservationsByEmprunteurCodeKo() {
		try {
			ko( manager.getReservationsByLivreCode(null), null );
			ko( manager.getReservationsByLivreCode(""), "" );
			ko( manager.getReservationsByLivreCode("XXXX"), "XXXX" );
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	private void ko(java.util.List<? extends Reservation> received, String code) {
		String msg = "GestionnaireBibliotheque.getReservationsByLivreCode("+code+")";
		assertNotNull(msg + " != null", received);
		assertEquals(msg + ".size()", 0, received.size());
	}
}
