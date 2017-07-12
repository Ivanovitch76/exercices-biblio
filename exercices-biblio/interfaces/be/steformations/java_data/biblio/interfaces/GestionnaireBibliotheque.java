package be.steformations.java_data.biblio.interfaces;

public interface GestionnaireBibliotheque {

	/**
	 * Recherche un Auteur selon le prenom et le nom
	 * @param prenom le prenom de l'Auteur
	 * @param nom le nom de l'Auteur
	 * @return l'Auteur correspondant aux critères ou null si il n'existe pas
	 */
	Auteur getAuteurByPrenomAndNom(String prenom, String nom);
	
	/**
	 * Recherche un Auteur selon son id
	 * @param code l'id de l'Auteur
	 * @return l'Auteur correspondant aux critères ou null si il n'existe pas
	 */
	Auteur getAuteurByCode(int code);
	/**
	 * Recherche tous les Auteur existants
	 * @return java.util.List contenant tous les Auteur existants
	 */
	java.util.List<? extends Auteur> getAllAuteurs();
	
	/**
	 * Ajoute un nouvel Auteur
	 * @param prenom prenom de l'Auteur
	 * @param nom nom de l'Auteur
	 * @return le nouvel Auteur ou null en cas de duplication ou de données invalides
	 */
	Auteur addAuteur(String prenom, String nom);
	
	/**
	 * Supprime un Auteur
	 * @param code id de l'Auteur à supprimer
	 * @return 
	 */
	Auteur removeAuteur(int code);
	
	/**
	 * Recherche une Collection
	 * @param nom nom de la Collection recherchée
	 * @return la Collection correspondante ou nulle si elle n'existe pas
	 */
	Collection getCollectionByNom(String nom);
	
	/**
	 * Recherche une Collection
	 * @param code id de la Collection recherchée
	 * @return la Collection correspondante ou nulle si elle n'existe pas
	 */
	Collection getCollectionByCode(int code);
	
	/**
	 * Recherche toutes les Collection existantes
	 * @return java.util.List contenant toutes les Collection existantes
	 */
	java.util.List<? extends Collection> getAllCollections();
	
	/**
	 * Ajoute une nouvelle Collection
	 * @param nom nom de la Collection
	 * @return le Collection créée ou null en cas de duplication ou de données invalides
	 */
	Collection addCollection(String nom);
	
	/**
	 * Supprime une Collection
	 * @param code id de la Collection à supprimer
	 */
	void removeCollection(int code);
	
	
	/**
	 * Recherche un Livre
	 * @param code code du Livre
	 * @return le Livre correspondant ou null si il n'existe pas
	 */
	Livre getLivreByCode(String code);
	
	/**
	 * Recherche tous les Livre existants
	 * @return java.util.List contenant tous les Livre
	 */
	java.util.List<? extends Livre> getAllLivres();
	
	/**
	 * Recherche les Livre correspondants aux critères.
	 * En cas de critère null, tous les livres correspondent.
	 * Si le critère de titre est une chaîne de caractères "vide" tous les titres correspondent
	 * @param auteur id de l'Auteur du Livre ou null pour n'importe quel Auteur
	 * @param collection  id de la Collection du Livre ou null pour n'importe quelle Collection
	 * @param partieDuTitre une sous-chaîne présente dans le Titre du Livre ou null/chaîne "vide" pour n'importe quel titre
	 * @return java.util.List (éventuellement vide) contenant les Livre correspondants
	 */
	java.util.List<? extends Livre> searchLivres(Integer auteur, Integer collection, String partieDuTitre);
	
	/**
	 * Ajoute un nouveau Livre
	 * @param titre le titre du Livre
	 * @param nombreDePages le nombre de pages du Livre
	 * @param dateDeParution la date de parution du Livre
	 * @param idCollection l'id de la Collection du Livre
	 * @param numeroEdition le numéro d'édition du Livre
	 * @param auteurs les id (éventuellement aucun) des Auteur du Livre
	 * @return le Livre créé ou null en cas de duplication titre/Collection ou de valeurs invalides
	 */
	Livre addLivre(String titre, short nombreDePages, java.util.Date dateDeParution, 
			int idCollection, short numeroEdition, java.util.List<Integer> auteurs);
	
	/**
	 * Supprime un Livre
	 * @param code le code du Livre
	 */
	void removeLivre(String code);
	
	/**
	 * Recherche un Emprunteur
	 * @param email l'email de l'Emprunteur recherché
	 * @return l'Emprunteur correspondant ou null si il n'existe pas
	 */
	Emprunteur getEmprunteurByEmail(String email);
	
	/**
	 * Ajoute un Emprunteur
	 * @param prenom le prénom de l'Emprunteur
	 * @param nom le nom de l'Emprunteur
	 * @param email l'email de  l'Emprunteur
	 * @param ddn la date de naissance de l'Emprunteur
	 * @param telephone le numéro de téléphone de l'Emprunteur
	 * @return l'Emprunteur créé ou null en de de duplication sur le couple prenom/nom ou sur l'email ou de données invalides
	 */
	Emprunteur addEmprunteur(String prenom, String nom, String email, java.util.Date ddn, String telephone);
	
	/**
	 * Recherche les Reservation d'un Emprunteur
	 * @param code l'id de l'Emprunteur
	 * @return java.util.List(éventuellement vide) des Reservation de cet Emprunteur
	 */
	java.util.List<? extends Reservation> getReservationsByEmprunteurCode(int code);
	
	/**
	 * Recherche les Reservation d'un Livre
	 * @param code code du Livre
	 * @return java.util.List(éventuellement vide) des Reservation de ce Livre
	 */
	java.util.List<? extends Reservation> getReservationsByLivreCode(String code);
	
	/**
	 * Recherche une Reservation
	 * @param code id de la Reservation
	 * @return la Reservation correspondante ou null si elle n'existe pas
	 */
	Reservation getReservationByCode(int code);
	
	/**
	 * Ajoute une Reservation
	 * @param emprunteur l'id de l'Emprunteur réservant
	 * @param livre le code du Livre réservé
	 * @return la Reservation créée ou null en cas de duplication sur le couple Livre/date de réservation ou de données invalides
	 */
	Reservation addReservation(int emprunteur, String livre);

	/**
	 * Supprime une Reservation
	 * @param reservation l'id de la Reservation
	 */
	void removeReservation(int reservation);
		
}
