/*
 * 
 */
package client;

import RestPojo.GsonScr;

// TODO: Auto-generated Javadoc
/**
 * The Class Prognoza.
 */
/*
 * Singleton pattern za spremanje i cuvanje podataka za trazenj orignoze
 */
public class Prognoza {
	
	/** The instance. */
	private static Prognoza instance = null;
	
	/** The prognoza. */
	private GsonScr prognoza;

	/** The error msg. */
	private String errorMsg;

	/**
	 * Instantiates a new prognoza.
	 */
	private Prognoza() {

	}

	/**
	 * Gets the single instance of Prognoza.
	 *
	 * @return single instance of Prognoza
	 */
	public static Prognoza getInstance() {
		if (instance == null) {
			instance = new Prognoza();
		}
		return instance;
	}

	/**
	 * Gets the prognoza.
	 *
	 * @return the prognoza
	 */
	public GsonScr getPrognoza() {
		return prognoza;
	}

	/**
	 * Sets the prognoza.
	 *
	 * @param prognoza the new prognoza
	 */
	public void setPrognoza(GsonScr prognoza) {
		this.prognoza = prognoza;
	}

	/**
	 * Gets the error msg.
	 *
	 * @return the error msg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * Sets the error msg.
	 *
	 * @param errorMsg the new error msg
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
