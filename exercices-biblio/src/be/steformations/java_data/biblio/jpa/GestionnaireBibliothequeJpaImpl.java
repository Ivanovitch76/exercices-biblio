package be.steformations.java_data.biblio.jpa;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import be.steformations.java_data.biblio.interfaces.Auteur;
import be.steformations.java_data.biblio.interfaces.Collection;
import be.steformations.java_data.biblio.interfaces.Emprunteur;
import be.steformations.java_data.biblio.interfaces.GestionnaireBibliotheque;
import be.steformations.java_data.biblio.interfaces.Livre;
import be.steformations.java_data.biblio.interfaces.Reservation;


public class GestionnaireBibliothequeJpaImpl implements GestionnaireBibliotheque {

	private javax.persistence.EntityManager entityManager;

	
	
	public GestionnaireBibliothequeJpaImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public Auteur getAuteurByPrenomAndNom(String prenom, String nom) {
		AuteurImpl auteur = null;
		
		String jpql = "select t from AuteurImpl t where lower(t.prenom) = lower(?1) and upper(t.nom) = upper(?2)";
		javax.persistence.TypedQuery<AuteurImpl> query
			= this.entityManager.createQuery(jpql, AuteurImpl.class);
		query.setParameter(1, prenom);
		query.setParameter(2, nom);
		
		try{
			auteur = query.getSingleResult(); // recherche d'une seule instance
		} catch(javax.persistence.NoResultException e){
			// pas de correspondance dans la db
		}
		
		return auteur;
	}

	@Override
	public AuteurImpl getAuteurByCode(int code) {
		AuteurImpl auteur = null;
		//uniquement les recherches sur l'Id de l'entité
		auteur = this.entityManager.find(AuteurImpl.class, code);
		
		return auteur;
	}

	@Override
	public List<? extends Auteur> getAllAuteurs() {
		java.util.List<AuteurImpl> list = null;
		String jpql = "select t from AuteurImpl t";
		javax.persistence.TypedQuery<AuteurImpl> query
			= this.entityManager.createQuery(jpql, AuteurImpl.class);
		list = query.getResultList();
		
		return list;
	}

	@Override
	public Auteur addAuteur(String prenom, String nom) {
		AuteurImpl aa = null;
		if (prenom != null && nom != null && this.getAuteurByPrenomAndNom(prenom, nom) == null) { 
				aa = new AuteurImpl();
				aa.setPrenom(prenom);
				aa.setNom(nom);

				this.entityManager.getTransaction().begin();
				this.entityManager.persist(aa);
				this.entityManager.getTransaction().commit();
			}
		
		return aa;	
	}

	@Override
	public Auteur removeAuteur(int code) {
		Auteur a = this.getAuteurByCode(code);
		if (a != null){
			this.entityManager.getTransaction().begin();
			this.entityManager.remove(a);
			this.entityManager.getTransaction().commit();
		}
		return a;

	}

	@Override
	public Collection getCollectionByNom(String nom) {
		Collection collection = null;
		
		String jpql = "select c from CollectionImpl c where upper(c.nom) = upper(?1)";
		javax.persistence.TypedQuery<CollectionImpl> query
			= this.entityManager.createQuery(jpql, CollectionImpl.class);
		query.setParameter(1, nom);
		
		try{
			collection = query.getSingleResult(); // recherche d'une seule instance
		} catch(javax.persistence.NoResultException e){
			// pas de correspondance dans la db
		}
		
		return collection;
	}

	@Override
	public Collection getCollectionByCode(int code) {
		Collection collection = null;
		//uniquement les recherches sur l'Id de l'entité
		collection = this.entityManager.find(CollectionImpl.class, code);
		
		return collection;
	}

	@Override
	public List<? extends Collection> getAllCollections() {
		java.util.List<CollectionImpl> list = null;
		String jpql = "select t from CollectionImpl t";
		javax.persistence.TypedQuery<CollectionImpl> query
			= this.entityManager.createQuery(jpql, CollectionImpl.class);
		list = query.getResultList();
		
		return list;
	}

	@Override
	public Collection addCollection(String nom) {
		CollectionImpl ac = null;
		if (nom != null && this.getCollectionByNom(nom) == null) { 
				ac = new CollectionImpl();
				ac.setNom(nom);

				this.entityManager.getTransaction().begin();
				this.entityManager.persist(ac);
				this.entityManager.getTransaction().commit();
			}
		
		return ac;	
	}

	@Override
	public void removeCollection(int code) {
		Collection c = this.getCollectionByCode(code);
		if (c != null){
			this.entityManager.getTransaction().begin();
			this.entityManager.remove(c);
			this.entityManager.getTransaction().commit();
		}

	}

	@Override
	public Livre getLivreByCode(String code) {
//		Livre livre = null;
//		//uniquement les recherches sur l'Id de l'entité
//		if(code != null){
//			try{
//			livre = this.entityManager.find(LivreImpl.class, code);
//			} catch(javax.persistence.NoResultException e){
//				// pas de correspondance dans la db
//			} 
//		}
//		return livre;
		
		Livre livre = null;

		if (code != null) {
			String jpql = "select l from LivreImpl l where l.code = ?1";
			javax.persistence.TypedQuery<LivreImpl> query = this.entityManager.createQuery(jpql, LivreImpl.class);
			query.setParameter(1, code);

			try {
				livre = query.getSingleResult(); // recherche d'une seule
													// instance
			} catch (javax.persistence.NoResultException e) {
				// pas de correspondance dans la db
			}
		}
		
		return livre;
	}

	@Override
	public List<? extends Livre> getAllLivres() {
		java.util.List<LivreImpl> list = null;
		String jpql = "select l from LivreImpl l";
		javax.persistence.TypedQuery<LivreImpl> query
			= this.entityManager.createQuery(jpql, LivreImpl.class);
		list = query.getResultList();
		
		return list;
	}

	@Override
	public List<? extends Livre> searchLivres(Integer auteur, Integer collection, String partieDuTitre) {
		java.util.List<LivreImpl> list = null;
		String jpql = "select l from LivreImpl l ";
		String mod = "";
		boolean a = false;
		boolean c = false;
		boolean pt = false;
		Auteur aut = null;

		
		if (auteur != null){
			aut = getAuteurByCode(auteur);
			if (aut != null){		
				a = true;
				mod = "?1 member of l.auteurs ";			
			}
		}
		if (collection != null){
			Collection coll = getCollectionByCode(collection);
			if (coll != null){
				c = true;
				if (a){
					mod = mod + "and "; 	
				} 
				mod = mod + "l.collection.id = ?2 ";	
			}
		}	
		if (partieDuTitre != null){
			pt = true;
			if (a || c){
				mod = mod + "and ";
			} 
			mod = mod + "locate(?3,l.titre) > 0";
			
		}
						
		if (a || c || pt){
			jpql = jpql + "where " + mod;
		}
		javax.persistence.TypedQuery<LivreImpl> query = this.entityManager.createQuery(jpql, LivreImpl.class);
		if (a){
			query.setParameter(1, aut);
		}
		if (c){
			query.setParameter(2, collection);
		}
		if (pt){
			query.setParameter(3, partieDuTitre);
		}

		list = query.getResultList();
		
		return list;
		
	}

	@Override
	public Livre addLivre(String titre, short nombreDePages, Date dateDeParution, int idCollection, short numeroEdition,
			List<Integer> auteurs) {
		List<AuteurImpl> listAuteur = null;
		String codeL = null;
	    String newVersion = null;
		LivreImpl l = null;
		boolean autConnu = true;
		if (titre != null && this.getCollectionByCode(idCollection) != null && numeroEdition >= 0 &&
				auteurs != null && this.getLivreByTitre(titre, numeroEdition) == null){
//			l.setTitre(titre);
//			l.setNbPages(nombreDePages);
//			l.setDdp(dateDeParution);
//			l.setCollection((CollectionImpl)this.getCollectionByCode(idCollection));
//			l.setNumero(numeroEdition);
			if (auteurs != null){
				listAuteur = new ArrayList<>();
				for (Integer code:auteurs){
					if (autConnu){
						listAuteur.add(this.getAuteurByCode(code));
						if (this.getAuteurByCode(code) == null){
							autConnu = false;
						}
					}
				}					
			}
			if (listAuteur != null && autConnu){
				codeL = this.getLivreIdMax();
				newVersion = "C" + (Integer.parseInt(codeL.substring(1,codeL.length()))+1);
				l = new LivreImpl();
				l.setCode(newVersion);
				l.setTitre(titre);
				l.setNbPages(nombreDePages);
				l.setDdp(dateDeParution);
				l.setCollection((CollectionImpl)this.getCollectionByCode(idCollection));
				l.setNumero(numeroEdition);
				l.setAuteurs(listAuteur);
				
				this.entityManager.getTransaction().begin();
				this.entityManager.persist(l);
				this.entityManager.getTransaction().commit();
			} 
			

		}
		return l;
	}


	@Override
	public void removeLivre(String code) {
		if (code != null) {
			Livre l = this.getLivreByCode(code);
			if (l != null) {
				this.entityManager.getTransaction().begin();
				this.entityManager.remove(l);
				this.entityManager.getTransaction().commit();
			}
		}

	}

	@Override
	public Emprunteur getEmprunteurByEmail(String email) {
		EmprunteurImpl emp = null;
		if (email != null){
			
//			String jpql = "select e from EmprunteurImpl e where e.email = ?1";
			javax.persistence.TypedQuery<EmprunteurImpl> query
//				= this.entityManager.createQuery(jpql, EmprunteurImpl.class);
				= this.entityManager.createNamedQuery("getEmprunteurByEmail", EmprunteurImpl.class);
			query.setParameter(1, email);
			
			try{
				emp = query.getSingleResult();
			} catch(javax.persistence.NoResultException e){
				
			}
			
		}
		return emp;
	}

	@Override
	public Emprunteur addEmprunteur(String prenom, String nom, String email, Date ddn, String telephone) {
		EmprunteurImpl emp = null;
		if (prenom != null && nom != null && email != null && ddn != null && telephone != null &&
				this.getEmprunteurByEmail(email) == null){
			emp = new EmprunteurImpl();
			emp.setPrenom(prenom);
			emp.setNom(nom);
			emp.setEmail(email);
			emp.setDdn(ddn);
			emp.setTelephone(telephone);
			
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(emp);
			this.entityManager.getTransaction().commit();
		}
		return emp;
	}

	@Override
	public List<? extends Reservation> getReservationsByEmprunteurCode(int code) {
		Emprunteur emprunteur = getEmprunteurByCode(code);
		java.util.List<ReservationImpl> list = null;
		String jpql = "select r from ReservationImpl r where r.emprunteur = ?1";
		javax.persistence.TypedQuery<ReservationImpl> query
			= this.entityManager.createQuery(jpql, ReservationImpl.class);
		query.setParameter(1, emprunteur);
		list = query.getResultList();
		
		return list;
	}

	@Override
	public List<? extends Reservation> getReservationsByLivreCode(String code) {
		Livre livre = getLivreByCode(code);
		java.util.List<ReservationImpl> list = null;
		String jpql = "select r from ReservationImpl r where r.livre = ?1";
		javax.persistence.TypedQuery<ReservationImpl> query
			= this.entityManager.createQuery(jpql, ReservationImpl.class);
		query.setParameter(1, livre);
		list = query.getResultList();
		
		return list;
	}

	@Override
	public Reservation getReservationByCode(int code) {
		Reservation reservation = null;

		String jpql = "select r from ReservationImpl r where r.id = ?1";
		javax.persistence.TypedQuery<ReservationImpl> query = this.entityManager.createQuery(jpql,
				ReservationImpl.class);
		query.setParameter(1, code);

		try {
			reservation = query.getSingleResult(); // recherche d'une seule
													// instance
		} catch (javax.persistence.NoResultException e) {
			// pas de correspondance dans la db
		}
		
		return reservation;
	}

	@Override
	public Reservation addReservation(int emprunteur, String livre) {
		ReservationImpl res = null;
		if (livre != null && this.getEmprunteurByCode(emprunteur) != null && this.getLivreByCode(livre) != null ){
			res = new ReservationImpl();
			res.setEmprunteur((EmprunteurImpl)this.getEmprunteurByCode(emprunteur));;
			res.setLivre((LivreImpl)this.getLivreByCode(livre));
			res.setDdr(new java.sql.Date(System.currentTimeMillis()));
			
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(res);
			this.entityManager.getTransaction().commit();
		}
		return res;
	}

	@Override
	public void removeReservation(int reservation) {

		Reservation r = this.getReservationByCode(reservation);
		if (r != null) {
			this.entityManager.getTransaction().begin();
			this.entityManager.remove(r);
			this.entityManager.getTransaction().commit();
		}

	}
	
	private EmprunteurImpl getEmprunteurByCode(int emprunteur) {
		EmprunteurImpl emp = null;
		//uniquement les recherches sur l'Id de l'entité
		emp = this.entityManager.find(EmprunteurImpl.class, emprunteur);
		
		return emp;
	}

	private LivreImpl getLivreByTitre(String titre, short numeroEdition) {
		LivreImpl livre = null;
		if (titre != null){
			String jpql = "select l from LivreImpl l where lower(l.titre) = lower(?1) and l.numero = ?2";
			javax.persistence.TypedQuery<LivreImpl> query
				= this.entityManager.createQuery(jpql, LivreImpl.class);
			query.setParameter(1, titre);
			query.setParameter(2, numeroEdition);
			
			try{
				livre = query.getSingleResult(); // recherche d'une seule instance
			} catch(javax.persistence.NoResultException e){
				// pas de correspondance dans la db
			}
		}
		return livre;
	}
	
	private String getLivreIdMax(){
		String max = null;
		String jpql = "select max(l.code) from LivreImpl l";
		javax.persistence.TypedQuery<String> query
			= this.entityManager.createQuery(jpql, String.class);
		max = query.getSingleResult();
		return max;
		
	}
	
}
