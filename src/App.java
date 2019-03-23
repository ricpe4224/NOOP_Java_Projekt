public class App {
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
