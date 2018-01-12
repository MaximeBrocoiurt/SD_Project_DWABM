package serialisation;

import java.io.EOFException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer  {
	public static void main(String args[]) {
		int port = 2002;
		try {
			
		ServerSocket ss = new ServerSocket(port);
		Socket s = ss.accept();
		InputStream is = s.getInputStream();
		ObjectInputStream ois = new ObjectInputStream(is);
		Message to = (Message)ois.readObject();
		
		if (to!=null){System.out.println(to.toString());}
		String ligne;
		
		//while(!((String)ois.readObject()).equals("finish")) {
		
		while(ois.read()!=0) { //OK
			
			try {
				System.out.println((String)ois.readObject());
			} catch (EOFException e)
			{
				is.close();
				s.close();
				ss.close();
			}
		}
		
		
		
	
		
		}catch(Exception e){System.out.println(e);}
	}
}