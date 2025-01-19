package src;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Commande {

	Queue<Commande> rangCommande = new LinkedList<>();
	Scanner scanner = new Scanner(System.in); // Ajout du scanner pour lire les entrées utilisateur

	// Classe pour représenter une commande

	// Classe pour représenter une commande

	String nomDeProduit;
	int nombre;

	public Commande(String nomDeProduit, int nombre) {
		this.nomDeProduit = nomDeProduit;
		this.nombre = nombre;
	}

	// Fonction pour générer des rapports
	public void generateReport() {
		// Logique pour générer des rapports (ventes, produits les plus vendus, etc.)

		System.out.println("Rapport généré avec succès.");
	}
}
