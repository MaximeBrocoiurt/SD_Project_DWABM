package serialisation;

import java.io.IOException;

public class LanceurClient {

	public static void main(String[] args) throws IOException {
		 System.out.println("Creation du Client lancé");
		ClientSerial  noe= new ClientSerial();
		 Thread threadNoe= new Thread(noe);
		 threadNoe.start();
		 System.out.println("Thread du Client lancé");
	}
}
