package serialisation;

import java.io.IOException;
import java.util.ArrayList;

import enchere.ObjetEnVente;
import enchere.PlaceMarchande;
import serveur.Serveur;

public class MainSerial {
	public static void main(String[] args) throws InterruptedException {
		ArrayList<ObjetEnVente> listObjetEnVente = new ArrayList<ObjetEnVente>(); 
		ObjetEnVente objet = new ObjetEnVente("lambo", 0, "");
		ObjetEnVente objet2 = new ObjetEnVente("velo", 0, "");
		ObjetEnVente objet3 = new ObjetEnVente("pc", 0, "");
		listObjetEnVente.add(objet);
		listObjetEnVente.add(objet2);
		listObjetEnVente.add(objet3);
		
		ServeurSerial Mon_serveur = null;
		try {
			Mon_serveur = new ServeurSerial(12000, new PlaceMarchande(listObjetEnVente));
			Mon_serveur.run();
			
			
		
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	
	}
}
