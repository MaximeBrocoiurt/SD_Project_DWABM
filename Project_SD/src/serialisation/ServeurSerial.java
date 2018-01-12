package serialisation;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import enchere.PlaceMarchande;
import serveur.ServiceClient;

public class ServeurSerial {
	PlaceMarchande placeMarchande;
	ServerSocket mon_connecteur; 	
	private int port;


	final String Finish = "" + (char) 4;
	
	ArrayList<Thread> listeThread= new ArrayList<Thread>();

	public ServeurSerial(int cport, PlaceMarchande place) throws IOException {
		port = cport;
		this.mon_connecteur = new ServerSocket(port);
		placeMarchande=place;
		
		System.out.format("ServeurSerial lancé sur le  port %d\n", port);
		

	}
	

	
	public void run() {
		int compteur=0;
		Socket ma_connection = null;  
			
		while (listeThread.size()<500) {
			// // /* Attente bloquante connexion */
			try {
				ma_connection = this.mon_connecteur.accept();
			} catch (IOException e) {
				System.out.println("Impossible de détacher une socket  : " + e);
				System.exit(-1);
			}
			ServiceClientSerial serviceClient;
			try {
				serviceClient = new ServiceClientSerial(ma_connection, ma_connection.getPort(), placeMarchande);
				Thread tache = new Thread(serviceClient);
				 System.out.println("Service_client attaché");
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
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
		
	/*public static void main(String[] args) throws IOException {
		Serveur Mon_serveur = null;
		Mon_serveur = new Serveur(12000);
		Mon_serveur.run();
	}*/
	
	
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
