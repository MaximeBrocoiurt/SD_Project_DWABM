package seveur;

/* On  importe les  classes  Reseau, Entrees Sorties, Utilitaires */
import java.net.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import enchere.ObjetEnVente;

import java.io.*;


public class Serveur {
		
	ServerSocket mon_connecteur;  // serveur de socket du serveur amélioré
	
	/* Port d'écoute */
	private int port;
	final String Finish = "" + (char) 4;  //Signal de fin de connection aussi nommé EOT  ctrl-d
	
	ArrayList<Thread> listeThread= new ArrayList<Thread>();

	public Serveur(int cport) throws IOException {
		port = cport;
		this.mon_connecteur = new ServerSocket(port); 		//Creation du gestionnaire de socket 
		System.out.format("Serveur lancé sur le  port %d\n", port);
	}
	
	public void run() {
		int compteur=0;
		Socket ma_connection = null;  		// file instanciée pour commmuniquer avec le client
		
		ObjetEnVente objet = new ObjetEnVente("Lambo", 10000, "");
		
		while (listeThread.size()<5) {
			// // /* Attente bloquante connexion */
			try {
				ma_connection = this.mon_connecteur.accept();
			} catch (IOException e) {
				System.out.println("Impossible de détacher une socket  : " + e);
				System.exit(-1);
			}
			ServiceClient serviceClient = new ServiceClient(ma_connection, ma_connection.getPort(), objet);
			Thread tache = new Thread(serviceClient);
			tache.start();
			if(tache!=null) {
				listeThread.add(tache);
				compteur++;
			}
			
			int c_port = ma_connection.getPort();
			String c_ip = ma_connection.getInetAddress().toString();
			System.out.format("Un client est arrivé avec IP : %s sur le port %d\n", c_ip, c_port);
			
			
			try {
				tache.sleep(5000);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			/* On traite le client que l'on a associé */
			/*try {
				//Service_Client(ma_connection);
		      
			} catch (IOException e) {
				System.out.println("Erreur de Service Client  : " + e);
				System.exit(-1);	}*/
			
			
		}
		interrompreServer();
	}
		
	public static void main(String[] args) throws IOException {
		/* On crée puis lance le serveur */
		Serveur Mon_serveur = null;
		Mon_serveur = new Serveur(12000);
		Mon_serveur.run();
	}
	
	
	public void interrompreServer() {
		for (Thread th : listeThread) {
			 th.interrupt();
			System.out.println("Thread "+ th.getName() +" interrompu !");
		}
		try {
			mon_connecteur.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}