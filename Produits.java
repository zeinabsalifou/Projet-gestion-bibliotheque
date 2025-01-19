package src;

public class Produits {

	String nom;
	int quantite;
	double prix;
	String dateExpiration;
	int quantiteVendue;
	boolean estPerissable;
	int ventes;
	// Ajouter un attribut pour indiquer si le produit est périssable

	// Constructeur
	public Produits(String nom, int quantite, double prix, String dateExpiration, boolean estPerissable) {
		this.nom = nom;
		this.quantite = quantite;
		this.prix = prix;
		this.dateExpiration = dateExpiration;
		this.quantiteVendue = 0;
		this.estPerissable = estPerissable;
	}

	// Méthodes d'accès aux propriétés
	public String getNom() {
		return nom;
	}

	public int getQuantite() {
		return quantite;
	}

	public double getPrix() {
		return prix;
	}

	public String getDateExpiration() {
		return dateExpiration != null ? dateExpiration : "N/A";
	}

	// Nouvelle méthode pour obtenir la quantité vendue
	public int getQuantiteVendue() {
		return quantiteVendue;
	}

	public int getVentes() {
		return ventes;
	}

	public void setQuantiteVendue(int quantiteVendue) {
		this.quantiteVendue = quantiteVendue;
	}

	public void mettreAJourQuantiteVendue(int quantiteVendue) {
		this.quantiteVendue += quantiteVendue;
		this.quantite -= quantiteVendue; // Déduire la quantité vendue du stock
		this.ventes += quantiteVendue; // Incrémenter le nombre de ventes
	}

	// Méthode pour vérifier si le produit est périssable
	public boolean estPerissable() {
		return estPerissable;
	}

	public void setQuantite(int i) {

	}

	public void afficherDetails() {
		System.out.println("Nom: " + nom);
		System.out.println("Quantité: " + quantite);
		System.out.println("Prix: " + prix);

		// Vérifier si la date d'expiration est disponible avant de l'afficher
		if (dateExpiration != null && !dateExpiration.trim().isEmpty()) {
			System.out.println("Date d'expiration : " + dateExpiration);
		}

		// Ajoutez d'autres détails selon vos besoins
	}

}
