package enchere;

import java.io.Serializable;

public class ObjetEnVenteSerial implements Runnable, Serializable{
	
	public String nom;
	public float prixDeDepart;
	public float prixCourant=prixDeDepart;
	public String proprietaire;
	public String idMeneur;
	
	public ObjetEnVenteSerial(String nom, float prix, String proprio) {
		this.nom=nom;
		prixDeDepart=prix;
		proprietaire=proprio;
	}

	@Override
	public void run() {
		boolean tansactionFini=false;
		while(tansactionFini) {
			
		}
	}
	
	
	public boolean add(float valeur, String idMeneur) {
		boolean valeurModifiee=false;
		if(prixCourant<valeur) {
			this.prixCourant=valeur;
			this.idMeneur=idMeneur;
			valeurModifiee=true;
		}
		return valeurModifiee;
	}
	
	public String getName() {
		return nom;
	}
	
	public String afficherInfo() {
		String info= "Nom : "+nom+"\n\r"+"Prix de depart : "+prixDeDepart+"\n\r"+"Prix courant : "+prixCourant+"\n\r"+"Proprietaire : "+proprietaire+"\n\r"+"id du meneur : "+idMeneur+"\n\r";
		return info;
	}
}