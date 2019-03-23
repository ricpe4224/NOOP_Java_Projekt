
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Klasa koja sluzi za povezivanje na bazu
 */

public class Login {

	private static final String DB_URL = "jdbc:derby:database;create=true";
	private static Connection conn = null;
	private static Statement statement = null;

	/*
	 * Spajanje na bazu
	 */

	public Login() throws InstantiationException, IllegalAccessException {

		
		createCon();
		setUpTable();

	}

	public void createCon() throws InstantiationException, IllegalAccessException {
		try {

			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			conn = DriverManager.getConnection(DB_URL);
			System.out.println("connected...");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Zatvaranje konekcije prema bazi
	 */
	public void closeConnection() {
		try {
			conn.close();
			statement = null;
		} catch (SQLException ex) {
			Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/*
	 * provjerava postoji li korisnik u bazi
	 */
	public boolean userExists(String username, String password) {

		try {
			String query = "SELECT COUNT(TABLICA.username) as available FROM TABLICA WHERE TABLICA.username='"
					+ username + "' AND TABLICA.password='" + password + "'";
			ResultSet rs = statement.executeQuery(query);
			
			
			if (rs.next()) {
				int num = rs.getInt("available");
				if (num != 0) {
					return true;
				}
			}

		} catch (SQLException ex) {
			Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
		}

		return false;
	}

	/*
	 * Dodavanje korisnika u bazu
	 */
	public void createNewUser( String username, String password) {
		try {
			String query = "INSERT INTO TABLICA VALUES (DEFAULT,'" + username + "','" + password + "')";
			statement.execute(query);
			
		} catch (SQLException ex) {
			Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void setUpTable() {
		String TABLE_NAME = "TABLICA";

		try {
			statement = conn.createStatement();

			DatabaseMetaData dbm = conn.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);

			if (tables.next()) {
				System.out.println("TAblica" + TABLE_NAME);
			} else {
				statement.execute("CREATE TABLE " + TABLE_NAME + "("
						+ "			id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 0, INCREMENT BY 1),\n"
						+ "			username varchar(200),\n" + "			password varchar(200),"
						+ "			CONSTRAINT primary_key PRIMARY KEY (id)" + " )");

				System.out.println("Tablica napravljena");
			}

		} catch (SQLException e) {
System.out.println("blyafeaef");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

	}

}
