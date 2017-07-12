package be.steformations.java_data.biblio.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import be.steformations.java_data.biblio.interfaces.Emprunteur;
import be.steformations.java_data.biblio.interfaces.GestionnaireBibliotheque;
import be.steformations.java_data.biblio.interfaces.Livre;
import be.steformations.java_data.biblio.interfaces.Reservation;

public class _18_TestGetReservationsByEmprunteurCode {

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
			Livre a108 = manager.getLivreByCode("A108");
			assertNotNull("GestionnaireBibliotheque.getLivreByCode(\"A108\") != null", a108);
			
			int userCode = bettyBoop.getId();
			java.util.List<? extends Reservation> bookings = manager.getReservationsByEmprunteurCode(userCode);
			
			String msg = "GestionnaireBibliotheque.getReservationsByEmprunteurCode("+userCode+")";
			assertNotNull(msg + " != null", bookings);
			assertEquals( msg + ".size()", 2, bookings.size());
			
			for (Reservation booking : bookings) {
				assertNotNull(msg + ".iterator().next().getId() != null", booking.getId());
				assertNotNull(msg + ".iterator().next().getEmprunteur() != null", booking.getEmprunteur());
				assertNotNull(msg + ".iterator().next().getLivre() != null", booking.getLivre());
				assertNotNull(msg + ".iterator().next().getDateDeReservation() != null", booking.getDateDeReservation());
				assertEquals(msg + ".iterator().next().getEmprunteur()", bettyBoop, booking.getEmprunteur());
				assertNotNull(msg + ".iterator().next().getLivre().getCode() != null", booking.getLivre().getCode()); 
				assertTrue(msg + ".iterator().next().getLivre().getCode() in [ A123, A108 ]", 
						booking.getLivre().getCode().equals(a108.getCode()) 
						|| booking.getLivre().getCode().equals(a123.getCode()));
				assertNotNull(msg + ".iterator().next().getLivre().getAuteurs() != null", booking.getLivre().getAuteurs());
				assertEquals(msg + ".iterator().next().getLivre().getAuteurs().size()", 1, booking.getLivre().getAuteurs().size());
				assertEquals(msg + ".iterator().next().getLivre().getAuteurs().get(0).getId()", new Integer(1), booking.getLivre().getAuteurs().get(0).getId());
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	@Test
	public void testGetReservationsByEmprunteurCodeKo() {
		try {
			ko( manager.getReservationsByEmprunteurCode(-1), -1 );
			ko( manager.getReservationsByEmprunteurCode(0), 0 );
			ko( manager.getReservationsByEmprunteurCode(Integer.MAX_VALUE), Integer.MAX_VALUE );
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}	
	}
	
	private void ko(java.util.List<? extends Reservation> received, int code) {
		String msg = "GestionnaireBibliotheque.getReservationsByEmprunteurCode("+code+")";
		assertNotNull(msg + " != null", received);
		assertEquals(msg + ".size()", 0, received.size());
	}
}
