package be.steformations.java_data.biblio.jpa;
import java.util.Date;

import be.steformations.java_data.biblio.interfaces.Emprunteur;
import be.steformations.java_data.biblio.interfaces.Livre;
import be.steformations.java_data.biblio.interfaces.Reservation;

public class ReservationImpl implements Reservation {

	protected int id;
	protected Emprunteur emprunteur;
	protected Livre livre;
	protected Date ddr;
	
	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public Emprunteur getEmprunteur() {
		return emprunteur;
	}

	@Override
	public Livre getLivre() {
		return livre;
	}

	@Override
	public Date getDateDeReservation() {
		return ddr;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEmprunteur(Emprunteur emprunteur) {
		this.emprunteur = emprunteur;
	}

	public void setLivre(Livre livre) {
		this.livre = livre;
	}

	public void setDdr(Date ddr) {
		this.ddr = ddr;
	}

	
	
}
