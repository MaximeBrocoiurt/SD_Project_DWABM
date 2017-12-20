package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {
	
	Socket socket;
	int port;
	String name="default";
	


	public Client(String host, int port, String name) throws IOException, IOException {
		 //String serverAddress = JOptionPane.showInputDialog("Enter IP Address of a machine that is\n" +"running the date service on port 12000:");
		this.socket = new Socket(host, port);
		this.port=port;
		this.name=name;
	}

	
	    /**
	     * Runs the client as an application.  First it displays a dialog
	     * box asking for the IP address or hostname of a host running
	     * the date server, then connects to it and displays the date that
	     * it serves.
	     */
	   

		@Override
		public void run() {
		
			        BufferedReader input;
			        PrintWriter writer;
					try {
						writer = new PrintWriter(socket.getOutputStream(), true);
						 //Thread currentThread = Thread.currentThread();
						/* currentThread.sleep(3000);
						writer.println("Panda");
						 currentThread.sleep(3000);
						writer.println("Poisson");
						 currentThread.sleep(3000);
						writer.println("Finish");*/
						
						while(true)
						{
							Thread currentThread = Thread.currentThread();
							 currentThread.sleep(5000);
							 writer.println("Panda");
						}
						
						
						//input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						//String answer = input.readLine();
					    //JOptionPane.showMessageDialog(null, answer);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			       
			        System.exit(0);
			
		}
	
}