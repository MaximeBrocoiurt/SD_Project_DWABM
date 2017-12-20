package seveur;

import seveur.Traitement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import enchere.ObjetEnVente;

public class ServiceClient implements Runnable{
	
	private static final CharSequence finish = "finish";
	private static final CharSequence add = "add";
	private static final CharSequence select = "select";
	Socket socket;
	int port;
	ObjetEnVente objetEnVente;
	

	public ServiceClient(Socket socket, int port) {
		this.socket=socket;
		this.port=port;
	}

	public void setObjetEnVente(ObjetEnVente obj) {objetEnVente=obj;}
	
	@Override
	public void run() {
		try {
			Service_Client(socket);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	  private Boolean Service_Client(Socket la_connection) throws IOException {
          /* On Associe une file d'entrée a la connection */
          InputStreamReader isr = new InputStreamReader(la_connection.getInputStream());
          /* On transforme cette file en file avec tampon */
          BufferedReader flux_entrant = new BufferedReader(isr);
          System.out.println("Tampon entree attache ");
          PrintWriter ma_sortie = new PrintWriter(la_connection.getOutputStream(), true);
          System.out.println("Sortie attachée");
          System.out.println("Prêt à servir le Client : "+ la_connection.getRemoteSocketAddress());

          String clientName = la_connection.getRemoteSocketAddress().toString();
          String message_lu = new String();
          int line_num = 0;

          /*
           * On lit le flux_entrant ligne à ligne ATTENTION : La fonction readline
           * est Bloquante readline retourne null si il y a souci avec la
           * connexion On s arrete aussisi connexion_non_terminee est vraie
           */
          ma_sortie.format("Bonjour %s j attends tes donnees  \n \r",clientName);
          while  (  (message_lu = flux_entrant.readLine()) != null)  {
        	  	System.out.println("user :");
                  System.out.format("%d: ->  [%s]\n", line_num, message_lu);
                  line_num++;
                  /* si on recoit Finish on clot et annonce cette terminaison */
                  if (message_lu.contains(finish)) {
                          System.out.println("Reception de  " + finish
                                          + " -> Transmission finie");
                          // On ferme la connection
                          System.out.format("Je clos la connection  %s :\n",clientName);
                          terminer(la_connection);
                          return (true);
                  }
                  if (message_lu.contains(add)) {
                	  boolean operation=false;
                	 // while(!operation) {
                		 // String chaine=Traitement.oneSpace(message_lu.toCharArray(),0,message_lu.length()).toString();
                		  // String[] parts=chaine.split(" ");
                	  	  String[] parts=message_lu.split(" ");
                		  String identifiant = parts[0];
                		  String command = parts[1];
                		  String value = parts[2]; 
                		  float valueFormated = Float.parseFloat(value);
                		  if(objetEnVente.add(valueFormated, identifiant)) {
                			  //System.out.format("%d: ->  [%s]\n", line_num, message_lu);
                			  System.out.println(identifiant+" a rencheri a " + value );
                			  ma_sortie.format("\n\r Vous avez rencheri de %s \n\r",value);
                			  //operation=true;
                		  }else {
                			  ma_sortie.format("\n\r Vous ne pouvez pas rencherir %s \n\r",value);
                		  }
                		  
                	 // }
     
                  	}
                  if(message_lu.contains(select)) {
                	  String[] parts=message_lu.split(" ");
            		  String identifiant = parts[0];
            		  String command = parts[1];
            		  String value = parts[2]; 
                  }
                  
              if(Thread.currentThread().isInterrupted()) {
  				terminer(la_connection);
  				System.out.println("Je ferme la socket");
  			}
          }
          
          return false;

	  	}
	  
	  private  void terminer(Socket ma_connection){
		  System.out.format("Socket fermee \n"); 
		    if (ma_connection != null)       	
		    {
			    try 	{
				ma_connection.close(); 
				System.out.format("Socket fermee \n"); 
			    }
			    catch ( IOException e )   { System.out.println("weird, nawak .... \n ");}     // do nothiing } 
			}
		  }
	  
}