package enchere;

public class ObjetEnVente implements Runnable{
	
	public String nom;
	public float prixDeDepart;
	public float prixCourant=prixDeDepart;
	public String proprietaire;
	public String idMeneur;
	
	public ObjetEnVente(String nom, float prix, String proprio) {
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
			prixCourant=valeur;
			this.idMeneur=idMeneur;
			valeurModifiee=true;
		}
		return valeurModifiee;
	}

}
