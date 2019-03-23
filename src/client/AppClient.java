/*
 * 
 */
package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;

import RestPojo.GsonScr;

// TODO: Auto-generated Javadoc
/**
 * The Class AppClient.
 */
/*
 * Klasa koja salje zahtjev serveru
 */
public class AppClient {
	
	/** The grad. */
	private String grad = "";
	
	/** The adresa. */
	final String adresa = "127.0.0.1";
	
	/** The port. */
	final int port = 50000;

	/** The mapper. */
	ObjectMapper mapper = new ObjectMapper();

	/**
	 * Take control.
	 */
	/*
	 * Spajanje na soket server i obradjuje odgovor servera
	 */
	public void takeControl() {

		try {

			Socket socket = new Socket(adresa, port);
			try (InputStream inputStream = socket.getInputStream();
					OutputStream outputStream = socket.getOutputStream();) {
				// this.grad =
				outputStream.write(grad.getBytes());
				outputStream.flush();
				socket.shutdownOutput();

				int znak;
				StringBuffer buffer = new StringBuffer();
				while (true) {
					znak = inputStream.read();
					if (znak == -1) {
						break;
					}
					buffer.append((char) znak);
				}
				System.out.println(buffer.toString());
				String buf = buffer.toString();
				Prognoza prognoza = Prognoza.getInstance();
				Gson gson = new Gson();
				GsonScr res = null;

				try {
					res = gson.fromJson(buffer.toString(), GsonScr.class);

				} catch (Exception e) {
					prognoza.setErrorMsg(buffer.toString());
					prognoza.setPrognoza(null);

				}

				if (res != null) {
					prognoza.setPrognoza(res);
					System.out.println(res);
					System.out.println("BBBBBBBBB");
				}

				outputStream.close();

			} catch (IOException ex) {

			}
			socket.close();
		} catch (IOException ex) {

		}

	}

	/**
	 * Gets the grad.
	 *
	 * @return the grad
	 */
	public String getGrad() {
		return grad;
	}

	/**
	 * Sets the grad.
	 *
	 * @param grad the new grad
	 */
	public void setGrad(String grad) {
		this.grad = grad;
	}

}
