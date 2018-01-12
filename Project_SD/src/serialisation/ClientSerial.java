package serialisation;

import java.net.*;

import enchere.ObjetEnVente;

import java.io.*;

public class ClientSerial implements Runnable{
	Socket socket;
	InputStream inputStream;
	ObjectInputStream objectInputStream;
	OutputStream outputStream;
	ObjectOutputStream objectOutputStream;
	ObjetEnVente objetEnVente=null;
	
	
	public ClientSerial() throws IOException {
		try {
			socket = new Socket(InetAddress.getLocalHost(),12000);
			this.objetEnVente=objetEnVente;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void setObjetEnVente(ObjetEnVente obj) {objetEnVente=obj;}
	
	@Override
	public void run() {
		
		//Envoi un message pour demander la placeMarchande
		System.out.println("Envoi un message pour demander la placeMarchande\n ");
		float x= 500;
		ecrire("kuchtad","add",x, objetEnVente);
	
		
		MessageServeur messageRecu=null;
		//attend la réponse
		System.out.println("attend la réponse de la place marchande\n ");
		MessageServeur mess=lire();
		System.out.println(mess.toString());
					 
		
		//le client choisi l'enchere de la lambo
		setObjetEnVente(messageRecu.getPlaceMarchande().getWithName("lambo"));
		
		//envoi le message
		System.out.println("le client choisi l'enchere de la lambo. envoi le message \n ");
		float x1= 500;
		ecrire("kuchtad","add",x1, objetEnVente);
		
		
		terminer(socket);

	}
	
	public MessageServeur lire() {
		MessageServeur message=null;
		try {
			//inputStream = socket.getInputStream();
			//objectInputStream = new ObjectInputStream(inputStream);
			
			while((MessageServeur)objectInputStream.readObject()==null) {
				 message = (MessageServeur)objectInputStream.readObject();
			  }
			message = (MessageServeur)objectInputStream.readObject();
			objectInputStream.close();
			//inputStream.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		return message;
	}
	
	public void ecrire(String id, String command, Float value, ObjetEnVente objet) {
		Message message= new Message(id, command, value, objet );
		 try {
			//outputStream = socket.getOutputStream();
			//objectOutputStream = new ObjectOutputStream(outputStream);
			objectOutputStream.writeObject(message);
			
			objectOutputStream.close();
			//outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	  private  void terminer(Socket ma_connection){
		  System.out.format("Socket fermee \n"); 
		    if (socket != null){
			    try{
			    	socket.close(); 
			    	System.out.format("Socket fermee \n"); 
			    }catch ( IOException e )   { System.out.println("Socket non fermee\n ");}     
			}
		  }
	
	
}