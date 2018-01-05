package enchere;

import java.util.ArrayList;

public class PlaceMarchande {
	private ArrayList<ObjetEnVente> listeObjetsEnVente = new ArrayList<ObjetEnVente>(); 
	
	
	public PlaceMarchande(ArrayList<ObjetEnVente> list) {
		listeObjetsEnVente=list;
	}
	
	public ArrayList<ObjetEnVente> getListObjetEnVente() {
		return listeObjetsEnVente;
	}

	public void setListObjetEnVente(ArrayList<ObjetEnVente> tableau) {
		this.listeObjetsEnVente = tableau;
	}
	
	public ObjetEnVente getWithName(String name) {
		ArrayList<ObjetEnVente> list=getListObjetEnVente();
		ObjetEnVente obj=null;
		for(int i=0; i<list.size();i++) {
			if(list.get(i).getName().equals(name))
				obj=list.get(i);
		}
		return obj;
	}
	
	public String afficherInfoAll() {
		ArrayList<ObjetEnVente> list=getListObjetEnVente();
		ObjetEnVente obj=null;
		String res="";
		res=res+"-------------------\n\r";
		for(int i=0; i<list.size();i++) {
			res=res+list.get(i).afficherInfo();
			res=res+"-------------------\n\r";
		}
		return res;
	}
}
