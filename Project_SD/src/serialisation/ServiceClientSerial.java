package serialisation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import enchere.ObjetEnVente;
import enchere.PlaceMarchande;

public class ServiceClientSerial implements Runnable{
	private static final CharSequence finish = "finish";
	private static final CharSequence add = "add";
	private static final CharSequence select = "select";
	private static final CharSequence info = "info";
	private static final CharSequence allInfo = "allInfo";
	Socket socket;
	int port;
	private PlaceMarchande placeMarchande;
	private ObjetEnVente objetEnVente;
	
	InputStream inputStream;
	ObjectInputStream objectInputStream;
  
	OutputStream outputStream;
	ObjectOutputStream objectOutputStream;
	

	public ServiceClientSerial(Socket socket, int port, PlaceMarchande placeMarchande) throws IOException {
		this.socket=socket;
		this.port=port;
		this.placeMarchande=placeMarchande;
		
		 inputStream = socket.getInputStream();
		 objectInputStream = new ObjectInputStream(inputStream);
      
		 outputStream = socket.getOutputStream();
		 objectOutputStream = new ObjectOutputStream(outputStream);
	}
	
	public Message lire() {
		Message message=null;
		try {
			//inputStream = socket.getInputStream();
			objectInputStream = new ObjectInputStream(inputStream);
			objectInputStream.close();
			//inputStream.close();
			
			while((Message)objectInputStream.readObject()==null) {
				 message = (Message)objectInputStream.readObject();
			  }
			message = (Message)objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		return message;
	}
	
	public void ecrire(String texte) {
		MessageServeur messageS= new MessageServeur(texte, placeMarchande);
		 try {
			//outputStream = socket.getOutputStream();
			objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(messageS);
			
			objectOutputStream.close();
			//outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	  private Boolean Service_Client(Socket la_connection) throws IOException, ClassNotFoundException {
		  System.out.println("Service_client attaché");

		  Message message=lire();
			if(message.getObjetEnVente()==null) {
				ecrire("Envoi etat place Marchande");
			}else {
				objetEnVente=message.getObjetEnVente();
			}
  
			message=lire();
			System.out.println(message.toString());
			boolean operationFini=false;
         // ma_sortie.format("Bonjour %s j attends tes donnees  \n \r",clientName);
          while  ( operationFini==false)  {
        	
                  
        	
                  
                  if (message.getCommand().equals(finish)) {
                         
                  }
                  if (message.getCommand().equals(add)) {
                		 // String chaine=Traitement.oneSpace(message_lu.toCharArray(),0,message_lu.length()).toString();
                	  ObjetEnVente obj= getPlaceMarchande().getWithName(objetEnVente.getName());
                	  //this.setObjetEnVente(obj);
                		 
                		  if(obj.add(message.getValue(), message.getId())) {
                			  System.out.println(message.getId()+" a rencheri a " + message.getValue() +" pour l'objet "+objetEnVente.getName());
                			  String reponse="\n\r Vous avez rencheri de "+message.getValue()+" pour l'objet "+objetEnVente.getName()+" \n\r";
                			  ecrire(reponse);
                		  }else {
                			  String reponse="\n\r Vous ne pouvez pas rencherir "+message.getValue()+" \n\r";
                			  ecrire(reponse);
                		  }
                  }
                  
                  /*if(message.getCommand().equals(select)) {

            		  ObjetEnVente obj= getPlaceMarchande().getWithName(value);
            		//  this.setObjetEnVente(obj);
            		  ma_sortie.format("\n\r Vous avez choisi d'encherir sur :  %s \n\r",obj.getName());
            		  System.out.println(identifiant+ " a choisi d'encherir sur : "+ obj.getName());
                  }
                  if(message.getCommand().equals(info)) {
                	  
                	  ObjetEnVente obj= getPlaceMarchande().getWithName(value);
                	  String info= obj.afficherInfo();
                	  ma_sortie.format("\n\r Voici les informations pour le produit cherche :  %s \n\r",info);
                	  System.out.println(identifiant+ " a demande des infos sur : "+ obj.getName());
                  }
                  if(message.getCommand().equals(allInfo)) {
                	  
                	  String info= placeMarchande.afficherInfoAll();
                	  ma_sortie.format("\n\r Voici les informations pour le produit cherche :  %s \n\r",info);
                	  System.out.println(identifiant+ " a demande des infos sur tous les produits");
                  }*/
                  
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
