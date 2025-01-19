package src;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.Comparator;

public class Stock {

	final int TAILLE_STOCK = 100; // Taille initiale du stock, ajustez selon les besoins
	Produits[] stock = new Produits[TAILLE_STOCK];
	int tailleStock = 0;
	Queue<Produits> produitsPerissables = new LinkedList<>();
	Stack<Produits> produitsNonPerissables = new Stack<>();
	private List<Vente> listeVentes = new ArrayList<>(); // Déplacer ici la déclaration et l'initialisation
	List<Produits> produitsAjoutes = new ArrayList<>(); // Liste pour stocker tous les produits ajoutés

	// Fonction pour agrandir la taille du tableau du stock si nécessaire

	public void initialiserStock() {
		Produits pomme = new Produits("Pomme", 50, 2.5, "2024-04-30", true);
		Produits banane = new Produits("Banane", 10, 800.0, null, false);

		// Ajouter les produits au stock en fonction de leur type
		if (pomme.estPerissable()) {
			produitsPerissables.offer(pomme);
		} else {
			produitsNonPerissables.push(pomme);
		}

		if (banane.estPerissable()) {
			produitsPerissables.offer(banane);
		} else {
			produitsNonPerissables.push(banane);
		}
	}

	public void ajouterProduit() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		do {
			// Demander à l'utilisateur de saisir les informations du produit
			System.out.print("Nom du produit : ");
			String nom = scanner.next();

			System.out.print("Quantité : ");
			// Ajoutez une vérification pour vous assurer que l'entrée est un entier
			while (!scanner.hasNextInt()) {
				System.out.println("Veuillez entrer une quantité valide (nombre entier).");
				System.out.print("Quantité : ");
				scanner.next(); // Consommer l'entrée incorrecte pour éviter une boucle infinie
			}
			int quantite = scanner.nextInt();

			// Demander à l'utilisateur de saisir le prix
			double prix = 0.0;

			while (true) {
				System.out.print("Prix : ");
				String prixInput = scanner.next();

				// Remplacer la virgule par un point si elle est présente
				prixInput = prixInput.replace(',', '.');

				try {
					prix = Double.parseDouble(prixInput);
					// Si la conversion réussit, sortir de la boucle
					break;
				} catch (NumberFormatException e) {
					System.out.println("Veuillez entrer un prix valide (nombre décimal).");
				}
			}

			String dateExpiration;

			// Demander à l'utilisateur de saisir la date d'expiration
			System.out.print("Date d'expiration «21-04-2030» (ou 'null' si non applicable) : ");
			dateExpiration = scanner.next();

			// Demander à l'utilisateur de choisir le type de produit (périssable ou non)
			boolean estPerissable;
			do {
				System.out.print("Le produit est-il périssable ? (Oui/Non) : ");
				String reponsePerissable = scanner.next().toLowerCase();

				if (reponsePerissable.equals("oui")) {
					estPerissable = true;
					break;
				} else if (reponsePerissable.equals("non")) {
					estPerissable = false;
					break;
				} else {
					System.out.println("Veuillez choisir entre 'Oui' et 'Non'.");
				}
			} while (true);

			// Créer le produit en fonction des informations saisies
			Produits produit = new Produits(nom, quantite, prix, dateExpiration, estPerissable);

			// Ajouter le produit à la structure de données appropriée
			if (estPerissable) {
				produitsPerissables.offer(produit);
			} else {
				produitsNonPerissables.push(produit);
			}

			// Ajouter le produit à la liste principale
			produitsAjoutes.add(produit);
			// Demander à l'utilisateur s'il veut ajouter un autre produit
			System.out.print("Voulez-vous ajouter un autre produit ? (Oui/Non) : ");
			String reponse = scanner.next().trim().toLowerCase();

			while (!reponse.equals("oui") && !reponse.equals("non")) {
				System.out.println("Veuillez répondre avec 'Oui' ou 'Non'.");
				System.out.print("Voulez-vous ajouter un autre produit ? (Oui/Non) : ");

				reponse = scanner.next().trim().toLowerCase();

			}
			System.out.println();

			if (reponse.equals("non")) {
				// L'utilisateur a dit "Non", sortir de la boucle
				break;
			}

			// Consommer la nouvelle ligne
			scanner.nextLine();

		} while (true);

		// Fermer le scanner lorsque vous avez terminé de l'utiliser
	}

	// Fonction pour modifier un produit dans le stock
	public void modifierProduit(String nom, int nouvelleQuantite, double nouveauPrix, String nouvelleDateExpiration) {
	    // Recherchez le produit parmi les produits périssables
	    for (Produits produit : produitsPerissables) {
	        if (produit.nom.equalsIgnoreCase(nom)) {
	            // Modifier le produit
	            produit.quantite = nouvelleQuantite;
	            produit.prix = nouveauPrix;
	            produit.dateExpiration = nouvelleDateExpiration;

	            System.out.println("Le produit a été modifié avec succès.");
	            return;
	        }
	    }

	    // Recherchez le produit parmi les produits non périssables
	    for (Produits produit : produitsNonPerissables) {
	        if (produit.nom.equalsIgnoreCase(nom)) {
	            // Modifier le produit
	            produit.quantite = nouvelleQuantite;
	            produit.prix = nouveauPrix;

	            System.out.println("Le produit a été modifié avec succès.");
	            return;
	        }
	    }

	    System.out.println("Produit non trouvé.");
	}

	

	public void vente(String name, int quantiteVendue) {
		// Recherche dans la file produitsPerissables
		for (Produits produit : produitsPerissables) {
			if (produit.getNom().equalsIgnoreCase(name)) {
				vendreProduit(produit, quantiteVendue);
				return;
			}
		}

		// Recherche dans la pile produitsNonPerissables
		for (Produits produit : produitsNonPerissables) {
			if (produit.getNom().equalsIgnoreCase(name)) {
				vendreProduit(produit, quantiteVendue);
				return;
			}
		}

		System.out.println("Le produit n'a pas été trouvé.");
	}

	private void vendreProduit(Produits produit, int quantiteVendue) {
		if (produit.getQuantite() >= quantiteVendue) {
			produit.setQuantite(produit.getQuantite() - quantiteVendue);

			// Mettez à jour la quantité vendue dans la classe Produits
			produit.mettreAJourQuantiteVendue(quantiteVendue);

			// Enregistrez les détails de la vente dans la liste
			Vente vente = new Vente(produit.getNom(), quantiteVendue, produit.getPrix());
			listeVentes.add(vente);

			System.out.println("Vente enregistrée avec succès.");
		} else {
			System.out.println("La quantité de ce produit est insuffisante.");
		}
	}

	// Ajoutez une liste temporaire pour les produits supprimés

	// Fonction pour supprimer un produit du stock
	public void supprimerProduits(String nomProduit, int quantite) {
		Produits produitTrouve = null;

		// Recherche du produit dans les produits périssables
		for (Produits produit : produitsPerissables) {
			if (produit.nom.equalsIgnoreCase(nomProduit)) {
				produitTrouve = produit;
				break;
			}
		}

		// Si le produit n'est pas trouvé parmi les produits périssables, recherchez
		// parmi les produits non périssables
		if (produitTrouve == null) {
			for (Produits produit : produitsNonPerissables) {
				if (produit.nom.equalsIgnoreCase(nomProduit)) {
					produitTrouve = produit;
					break;
				}
			}
		}

		// Si le produit est trouvé, mettez à jour la quantité et affichez un message de
		// succès
		if (produitTrouve != null) {
			produitTrouve.quantite -= quantite;
			System.out.println("Produit supprimé avec succès!");
		} else {
			System.out.println("Produit non trouvé.");
		}
	}

	private Queue<Commande> rangCommande = new LinkedList<>();
	private Scanner scanner = new Scanner(System.in);

	// Fonction pour passer une commande
	public void passerCommande() {
		System.out.print("Nom du produit à commander : ");
		String nomProduit = scanner.next();

		System.out.print("Quantité à commander : ");
		int quantiteCommandee = scanner.nextInt();

		// Créer une instance de la classe Commande
		Commande commande = new Commande(nomProduit, quantiteCommandee);

		// Ajouter la commande à la file d'attente
		rangCommande.add(commande);

		System.out.println("Commande passée avec succès.");
	}

	// ... (autres membres et méthodes de la classe StockManager)

	// Fonction pour afficher les commandes en attente
	public void voirRangCommande() {

		// Logique pour afficher les commandes en attente

		if (!rangCommande.isEmpty()) {
			System.out.println("Liste des commandes en attente:");
			for (Commande commande : rangCommande) {
				System.out.println("Produit : " + commande.nomDeProduit + ", Quantite : " + commande.nombre);
			}
		} else {
			System.out.println("Aucune commande en attente.");
		}
	}

	// Fonction pour afficher la liste de stock en précisant les produits
	// périssables et non périssables
	// La méthode afficherStock utilise la méthode afficherProduitsRecursive pour
	// traiter récursivement les produits périssables et non périssables. Cela rend
	// le code plus modulaire et élégant.

	public void afficherStock() {
		System.out.println("Liste de stock :");

		// Afficher les produits périssables dans la filePerissable
		System.out.println("Produits Perissables :");
		afficherProduitsRecursive(produitsPerissables);

		// Afficher les produits non périssables dans la pileNonPerissable
		System.out.println("\nProduits Non Périssables :");
		afficherProduitsRecursive(produitsNonPerissables);

		// Affichez les détails des commandes en attente ici
	}

	private void afficherProduitsRecursive(Collection<Produits> produits) {
		for (Produits produit : produits) {
			System.out.println("Nom: " + produit.nom + ", Quantite: " + produit.quantite + ", Prix: $ " + produit.prix
					+ ", Date d'expiration: " + produit.dateExpiration);
		}
	}

	// Affichez les détails des commandes en attente ici

	// Fonction pour générer un rapport sur les ventes
	public void generateSalesReport() {
		System.out.println("*** Rapport des Ventes ***");
		double montantTotalVentes = 0.0;

		for (Vente vente : listeVentes) {
			double prixTotalVente = vente.getQuantiteVendue() * vente.getPrixUnitaire();
			montantTotalVentes += prixTotalVente;

			System.out.println("Produit : " + vente.getNomProduit() + ", Quantité vendue : " + vente.getQuantiteVendue()
					+ ", Prix unitaire : " + vente.getPrixUnitaire() + ", Prix total de vente : " + prixTotalVente);
		}

		System.out.println("Montant total des ventes : " + montantTotalVentes);
	}

///Fonction pour générer le rapport des produits en rupture de stock
//Fonction pour générer le rapport des produits en rupture de stock
	public void generateOutOfStockReport() {
		System.out.println("*** Rapport des Produits en Rupture de Stock ***");

		boolean produitsEnRuptureDeStock = false;

		// Vérifier s'il y a des produits périssables en rupture de stock
		for (Produits produit : produitsPerissables) {
			if (produit.getQuantite() == 0) {
				System.out.println("Produit : " + produit.getNom());
				produitsEnRuptureDeStock = true;
			}
		}

		// Vérifier s'il y a des produits non périssables en rupture de stock
		for (Produits produit : produitsNonPerissables) {
			if (produit.getQuantite() == 0) {
				System.out.println("Produit : " + produit.getNom());
				produitsEnRuptureDeStock = true;
			}
		}

		// Afficher un message si aucun produit n'est en rupture de stock
		if (!produitsEnRuptureDeStock) {
			System.out.println("Aucun produit en rupture de stock.");
		}
	}

	public Produits rechercherProduit(String nom) {
		// Recherche dans le tableau de produits
		for (Produits produit : stock) {
			if (produit != null && produit.nom.equalsIgnoreCase(nom)) {
				return produit;
			}
		}

		// Recherche dans la liste des produits périssables
		for (Produits produit : produitsPerissables) {
			if (produit.nom.equalsIgnoreCase(nom)) {
				return produit;
			}
		}

		// Recherche dans la liste des produits non périssables
		for (Produits produit : produitsNonPerissables) {
			if (produit.nom.equalsIgnoreCase(nom)) {
				return produit;
			}
		}

		// Recherche dans la liste des produits ajoutés à la console
		for (Produits produit : produitsAjoutes) {
			if (produit.nom.equalsIgnoreCase(nom)) {
				return produit;
			}
		}

		// Aucun produit trouvé avec le nom spécifié
		return null;
	}

	// Méthode pour afficher les produits les plus vendus
	public void produitsPlusVendus() {
		System.out.println("*** Produits les plus vendus ***");

		// Fusionner les produits périssables et non périssables dans une liste
		List<Produits> tousLesProduits = new ArrayList<>();
		tousLesProduits.addAll(produitsPerissables);
		tousLesProduits.addAll(produitsNonPerissables);

		// Filtrer les produits non nuls
		List<Produits> produitsNonNuls = tousLesProduits.stream().filter(Objects::nonNull).collect(Collectors.toList());

		// Tri des produits les plus vendus dans l'ordre décroissant
		Collections.sort(produitsNonNuls, Comparator.comparingInt(Produits::getVentes).reversed());

		// Affichage des produits les plus vendus
		if (produitsNonNuls.isEmpty()) {
			System.out.println("Aucun produit disponible.");
		} else {
			for (Produits produit : produitsNonNuls) {
				System.out.println("Nom : " + produit.getNom() + " - Nombre de ventes : " + produit.getVentes());
			}
		}
	}

}
