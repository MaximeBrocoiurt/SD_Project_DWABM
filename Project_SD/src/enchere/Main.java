package enchere;

import java.io.IOException;
import java.util.ArrayList;

import seveur.Serveur;

public class Main {
	public static void main(String[] args) {
		/* On crée puis lance le serveur */
		Serveur Mon_serveur = null;
		try {
			Mon_serveur = new Serveur(12000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mon_serveur.run();
		
		ArrayList<ObjetEnVente> tableau = new ArrayList<ObjetEnVente>(); 
		ObjetEnVente objet = new ObjetEnVente("lambo", 0, "");
		ObjetEnVente objet2 = new ObjetEnVente("velo", 0, "");
		ObjetEnVente objet3 = new ObjetEnVente("pc", 0, "");
		tableau.add(objet);
		tableau.add(objet2);
		tableau.add(objet3);
	}
	
}
