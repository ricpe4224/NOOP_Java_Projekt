/*
 * 
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// TODO: Auto-generated Javadoc
/**
 * The Class RunServer.
 */
/*
 * Klasa za pokretanje servera
 */
public class RunServer {
	
	/** The port. */
	int port;
	
	/**
	 * Instantiates a new run server.
	 *
	 * @param port the port
	 */
	public RunServer(int port) {
		this.port = port;
		// TODO Auto-generated constructor stub
	}
	/*
	 * prihvaca korisnika na socket proljedjuje njegov zahtjev niti (obrada zahtjeva)
	 */
	
	/**
	 * Take control.
	 */
	public void takeControl() {
		ServerSocket serverSocket = null;
		 try {
			 serverSocket = new ServerSocket(port,1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		while(true) {
            System.out.println("usao");
            try {
				Socket socket = serverSocket.accept();
				ObradaZahtjeva obradaZahtjeva = new ObradaZahtjeva(socket); 
				obradaZahtjeva.start();
				
				//odgovorServera("blabla", socket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	 

}
