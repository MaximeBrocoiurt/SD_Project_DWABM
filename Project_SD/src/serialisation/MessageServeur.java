package serialisation;

import java.io.Serializable;

import enchere.PlaceMarchande;

public class MessageServeur implements Serializable{
	
	String reponse;
	PlaceMarchande placeMarchande;
	
	public MessageServeur(String rep, PlaceMarchande place) {
		reponse=rep;
		placeMarchande=place;
	}
	
	public String toString() {
		String res = "reponse du serveur:"+reponse;
		return res;
	}
	
	public PlaceMarchande getPlaceMarchande() {return placeMarchande;}

}
