package src;

import java.util.Scanner;

public class Gestionstock {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Stock stockManager = new Stock();
		// Ajouter des produits préexistants au stock
		stockManager.initialiserStock();

		// Exemple d'initialisation du tableau stock avec des produits préexistants
		stockManager.stock[0] = new Produits("Pomme", 50, 2.5, "20-04-2030", true);
		stockManager.stock[1] = new Produits("Banane", 10, 800.0, null, false);
		stockManager.tailleStock = 2; // Mettez à jour la tailleStock en conséquence

		// Ajouter les produits au stock

		// Menu principal
		int choix;
		do {
			System.out.println();
			System.out.println("      *** MENU ***");
			System.out.println();
			System.out.println("1. Ajouter un produit");
			System.out.println("2. Supprimer un produit");
			System.out.println("3. Modifier un produit");
			System.out.println("4. Rechercher un produit");
			System.out.println("5. Afficher le stock des produits");
			System.out.println("6. Enregistrer une vente");
			System.out.println("7. Passer une commande");
			System.out.println("8. Voir les commandes en attente");
			System.out.println("9. Generer un rapport");

			System.out.println("0. Quitter");
			System.out.print("Choisir une option : ");
			// Ajoutez une validation pour vous assurer que l'entrée est un entier
			while (!scanner.hasNextInt()) {
				System.out.println("Option non valide. Veuillez choisir une option valide.");
				System.out.print("Choisir une option : ");
				scanner.next(); // Consommer l'entrée incorrecte pour éviter une boucle infinie
			}

			// Maintenant, vous pouvez lire l'entier en toute sécurité
			choix = scanner.nextInt();

			System.out.println();
			switch (choix) {
			case 1:

				stockManager.ajouterProduit();
				break;

			case 2:
				System.out.print("Nom du produit a supprimer : ");
				String nomPro = scanner.next();

				System.out.print("Quantite a supprimer: ");
				int quantiteASupprimer = scanner.nextInt();
				stockManager.supprimerProduits(nomPro, quantiteASupprimer);

				break;

			case 3:
				
			    // Demander à l'utilisateur de saisir le nom du produit à modifier
			    System.out.print("Nom du produit à modifier : ");
			    String nomProduit = scanner.next();

			    // Demander à l'utilisateur de saisir la nouvelle quantité
			    System.out.print("Nouvelle quantité : ");
			    int nouvelleQuantite = scanner.nextInt();

			    // Demander à l'utilisateur de saisir le nouveau prix
			    System.out.print("Nouveau prix : ");
			    String prixInput = scanner.next();

			    // Remplacer la virgule par un point si elle est présente
			    prixInput = prixInput.replace(',', '.');

			    try {
			        // Convertir la chaîne modifiée en double
			        double nouveauPrix = Double.parseDouble(prixInput);

			        // Demander à l'utilisateur de saisir la nouvelle date d'expiration
			        System.out.print("Nouvelle date d'expiration : ");
			        String nouvelleDateExpiration = scanner.next();

			        // Appeler la méthode modifierProduit avec les nouvelles informations
			        stockManager.modifierProduit(nomProduit, nouvelleQuantite, nouveauPrix, nouvelleDateExpiration);

			    } catch (NumberFormatException e) {
			        System.out.println("Veuillez entrer un prix valide (nombre décimal).");
			    }

			   
				break;

			case 4:
				// Lire le nom du produit à rechercher depuis la console
				System.out.print("Entrez le nom du produit à rechercher : ");
				String nomProduits = scanner.next();

				// Rechercher le produit
				Produits produitTrouve = stockManager.rechercherProduit(nomProduits);

				// Afficher les détails du produit trouvé
				if (produitTrouve != null) {
					// Utiliser la méthode afficherProduit de la classe Produits
					produitTrouve.afficherDetails();
				} else {
					System.out.println("Produit non trouvé.");
				}
				break;

			case 5:

				stockManager.afficherStock();

				break;

			case 6:

				System.out.print("Nom du produit a vendre : ");
				String nomProdui = scanner.next();

				System.out.print("Quantite a vendre : ");
				int quantiteVendue = scanner.nextInt();

				stockManager.vente(nomProdui, quantiteVendue);

				break;

			case 7:
				stockManager.passerCommande();

				break;
			case 8:

				stockManager.voirRangCommande();

				break;
			case 9:
				stockManager.generateSalesReport();
				System.out.println();
				stockManager.produitsPlusVendus();
				System.out.println();
				stockManager.generateOutOfStockReport();

				break;

			case 0:
				System.out.println("Programme termine.");
				break;

			default:
				System.out.println("Option non valide. Veuillez choisir une option valide.");
			}

		} while (choix != 0);
		scanner.close(); // Fermer le scanner lorsque vous avez terminé de l'utiliser
	}

}
