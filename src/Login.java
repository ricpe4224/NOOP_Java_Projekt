/*
 * 
 */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

// TODO: Auto-generated Javadoc
/*
 * Klasa koja sluzi za povezivanje na bazu
 */

/**
 * The Class Login.
 */
public class Login {

	/** The Constant DB_URL. */
	private static final String DB_URL = "jdbc:derby:database;create=true";
	
	/** The conn. */
	private static Connection conn = null;
	
	/** The statement. */
	private static Statement statement = null;

	/*
	 * Spajanje na bazu
	 */

	/**
	 * Instantiates a new login.
	 *
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	public Login() throws InstantiationException, IllegalAccessException {

		
		createCon();
		setUpTable();

	}

	/**
	 * Creates the con.
	 *
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	public void createCon() throws InstantiationException, IllegalAccessException {
		try {

			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			conn = DriverManager.getConnection(DB_URL);
			System.out.println("connected...");

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Close connection.
	 */
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

	/**
	 * User exists.
	 *
	 * @param username the username
	 * @param password the password
	 * @return true, if successful
	 */
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

	/**
	 * Creates the new user.
	 *
	 * @param username the username
	 * @param password the password
	 */
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

	/**
	 * Sets the up table.
	 */
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

			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}

	}

}
