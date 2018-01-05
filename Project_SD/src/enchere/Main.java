package enchere;

import java.io.IOException;
import java.util.ArrayList;

import seveur.Serveur;

public class Main {
	public static void main(String[] args) {
		ArrayList<ObjetEnVente> listObjetEnVente = new ArrayList<ObjetEnVente>(); 
		ObjetEnVente objet = new ObjetEnVente("lambo", 0, "");
		ObjetEnVente objet2 = new ObjetEnVente("velo", 0, "");
		ObjetEnVente objet3 = new ObjetEnVente("pc", 0, "");
		listObjetEnVente.add(objet);
		listObjetEnVente.add(objet2);
		listObjetEnVente.add(objet3);
		
		Serveur Mon_serveur = null;
		try {
			Mon_serveur = new Serveur(12000, new PlaceMarchande(listObjetEnVente));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Mon_serveur.run();
		
	
	}
	
}
