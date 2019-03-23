/*
 * 
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
/*
 * Klasa koja obradjuje zahtjeve korisnika
 */

import com.google.gson.Gson;

import RestPojo.GsonScr;

// TODO: Auto-generated Javadoc
/**
 * The Class ObradaZahtjeva.
 */
public class ObradaZahtjeva extends Thread{
	
	/** The grad. */
	String grad;
	
	/** The socket. */
	Socket socket;
	
	/**
	 * Instantiates a new obrada zahtjeva.
	 *
	 * @param socket the socket
	 */
	public ObradaZahtjeva(Socket socket) {
			
			this.socket = socket;
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 * poziva web servis i vraca odredjeni odgovor u json formatu
	 */
	@Override
	public void run() {
		RestAPi rest= new RestAPi();
		String response = "";
		
		try {
			InputStream inputStream = socket.getInputStream();
			int znak;
			StringBuffer buffer = new StringBuffer();
			while (true) {
				znak = inputStream.read();
				if (znak == -1) {
					break;
				}
				buffer.append((char) znak);
			}
			String podaci = buffer.toString();

			String[] split = podaci.split(":");
			
			grad = split[0];
			String type = split[1];
			

			rest.setGrad(grad);
			response = rest.getRequest(type);

			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		odgovorServera(response, socket);
		


	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#start()
	 */
	@Override
	public synchronized void start() {

		super.start();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#interrupt()
	 */
	@Override
	public void interrupt() {

		super.interrupt();
	}
	
	/**
	 * Odgovor servera.
	 *
	 * @param odgovor the odgovor
	 * @param s the s
	 */
	/*
	 * metoda kojom server vraca odgovor poziva se u run metodi
	 */
	private void odgovorServera(String odgovor, Socket s) {
        try (
                InputStream inputStream = s.getInputStream();
                OutputStream outputStream = s.getOutputStream();) {

            int znak;
            StringBuffer buffer = new StringBuffer();
            while (true) {
                znak = inputStream.read();
                if (znak == -1) {
                    break;
                }
                buffer.append((char) znak);
            }
            outputStream.write(odgovor.getBytes());
        } catch (IOException ex) {
        }
    }


}
