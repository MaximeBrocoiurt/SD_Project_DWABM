package seveur;

import seveur.Traitement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import enchere.ObjetEnVente;
import enchere.PlaceMarchande;

public class ServiceClient implements Runnable{
	
	private static final CharSequence finish = "finish";
	private static final CharSequence add = "add";
	private static final CharSequence select = "select";
	private static final CharSequence info = "info";
	private static final CharSequence allInfo = "allInfo";
	Socket socket;
	int port;
	private PlaceMarchande placeMarchande;
	private ObjetEnVente objetEnVente;
	

	public ServiceClient(Socket socket, int port, PlaceMarchande placeMarchande) {
		this.socket=socket;
		this.port=port;
		this.placeMarchande=placeMarchande;
	}

	public ObjetEnVente getObjetEnVente() {return objetEnVente;}
	public void setObjetEnVente(ObjetEnVente obj) {objetEnVente=obj;}
	
	public PlaceMarchande getPlaceMarchande() { return placeMarchande;}
	
	@Override
	public void run() {
		try {
			Service_Client(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	  private Boolean Service_Client(Socket la_connection) throws IOException {
          InputStreamReader isr = new InputStreamReader(la_connection.getInputStream());
          BufferedReader flux_entrant = new BufferedReader(isr);
          System.out.println("Tampon entree attache ");
          PrintWriter ma_sortie = new PrintWriter(la_connection.getOutputStream(), true);
          System.out.println("Sortie attachée");
          System.out.println("Prêt à servir le Client : "+ la_connection.getRemoteSocketAddress());

          String clientName = la_connection.getRemoteSocketAddress().toString();
          String message_lu = new String();
          int line_num = 0;

          ma_sortie.format("Bonjour %s j attends tes donnees  \n \r",clientName);
          while  (  (message_lu = flux_entrant.readLine()) != null)  {
        	  	System.out.println("user :");
                  System.out.format("%d: ->  [%s]\n", line_num, message_lu);
                  line_num++;
                  
        		  // String[] parts=chaine.split(" ");
        	  	  String[] parts=message_lu.split(" ");
        		  String identifiant = parts[0];
        		  String command = parts[1];
        		  String value = "";
        		  String value2 = "";
        		  if(parts.length>2)
        			  value =parts[2];
        		  if(parts.length==4)
        			   value2= parts[3];
                  
                  if (command.equals(finish)) {
                          System.out.println("Reception de  " + finish+ " -> Transmission finie");
                          System.out.format("Je clos la connection  %s :\n",clientName);
                          terminer(la_connection);
                          return (true);
                  }
                  if (command.equals(add)) {
                		 // String chaine=Traitement.oneSpace(message_lu.toCharArray(),0,message_lu.length()).toString();
                	  ObjetEnVente obj= getPlaceMarchande().getWithName(value);
                	  //this.setObjetEnVente(obj);
                		  float valueFormated = Float.parseFloat(value2);
                		  if(obj.add(valueFormated, identifiant)) {
                			  System.out.println(identifiant+" a rencheri a " + value2 +" pour l'objet "+value);
                			  ma_sortie.format("\n\r Vous avez rencheri de %s pour l'objet %s \n\r",value2, value);
                		  }else {
                			  ma_sortie.format("\n\r Vous ne pouvez pas rencherir %s \n\r",value);
                		  }
                  }
                  
                  if(command.equals(select)) {

            		  ObjetEnVente obj= getPlaceMarchande().getWithName(value);
            		//  this.setObjetEnVente(obj);
            		  ma_sortie.format("\n\r Vous avez choisi d'encherir sur :  %s \n\r",obj.getName());
            		  System.out.println(identifiant+ " a choisi d'encherir sur : "+ obj.getName());
                  }
                  if(command.equals(info)) {
                	  
                	  ObjetEnVente obj= getPlaceMarchande().getWithName(value);
                	  String info= obj.afficherInfo();
                	  ma_sortie.format("\n\r Voici les informations pour le produit cherche :  %s \n\r",info);
                	  System.out.println(identifiant+ " a demande des infos sur : "+ obj.getName());
                  }
                  if(command.equals(allInfo)) {
                	  
                	  String info= placeMarchande.afficherInfoAll();
                	  ma_sortie.format("\n\r Voici les informations pour le produit cherche :  %s \n\r",info);
                	  System.out.println(identifiant+ " a demande des infos sur tous les produits");
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
		    if (ma_connection != null){
			    try{
			    	ma_connection.close(); 
			    	System.out.format("Socket fermee \n"); 
			    }catch ( IOException e )   { System.out.println("Socket non fermee\n ");}     
			}
		  }
	  
}