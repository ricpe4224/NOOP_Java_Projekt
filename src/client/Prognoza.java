package client;

import RestPojo.GsonScr;

/*
 * Singleton pattern za spremanje i cuvanje podataka za trazenj orignoze
 */
public class Prognoza {
	private static Prognoza instance = null;
	private GsonScr prognoza;

	private String errorMsg;

	private Prognoza() {

	}

	public static Prognoza getInstance() {
		if (instance == null) {
			instance = new Prognoza();
		}
		return instance;
	}

	public GsonScr getPrognoza() {
		return prognoza;
	}

	public void setPrognoza(GsonScr prognoza) {
		this.prognoza = prognoza;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
