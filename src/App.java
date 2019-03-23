// TODO: Auto-generated Javadoc
/**
 * The Class App.
 */
public class App {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	/* 
	 * 
	 * Klasa koja pokrece server
	 */
	public static void main(String[] args) {
		int port = 50000;
		RunServer server = new RunServer(port);
		server.takeControl();		

	}
}
