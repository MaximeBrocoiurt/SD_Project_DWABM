package serialisation;

import java.net.*;

import enchere.ObjetEnVente;

import java.io.*;

class Message implements Serializable {
	float value ;
	String command;
	String id;
	ObjetEnVente objetEnVente;
	
	public  Message( String s , String command, float v, ObjetEnVente objetEnVente){
		this.value=v;
		this.id=s;
		this.command=command;
		this.objetEnVente=objetEnVente;
	}
	
	public String toString() {
		String res="id : "+id+"\n value : "+value+"\n commande : "+command;
		return res;
	}
	
	public float getValue() {return value;}
	public String getId() {return id;}
	public String getCommand() {return command;}
	public ObjetEnVente getObjetEnVente() {return objetEnVente;}
}