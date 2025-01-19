package src;

public class Vente {
	private String nomProduit;
	private int quantiteVendue;
	private double prixUnitaire;

	public Vente(String nomProduit, int quantiteVendue, double prixUnitaire) {
		this.nomProduit = nomProduit;
		this.quantiteVendue = quantiteVendue;
		this.prixUnitaire = prixUnitaire;
	}

	// Ajoutez des getters si nécessaire
	public String getNomProduit() {
		return nomProduit;
	}

	public int getQuantiteVendue() {
		return quantiteVendue;
	}

	public double getPrixUnitaire() {
		return prixUnitaire;
	}

	// Méthode pour obtenir le montant total de la vente
	public double getMontantTotal() {
		return quantiteVendue * prixUnitaire;
	}

}
