import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
/*
 * Klasa koja salje zahtjev prema REST servisu
 * poziva se u klasi obrada zahtjeva
 */

public class RestAPi {

	String grad;
	String jsonResponse = "";

	@SuppressWarnings("deprecation")
	public String getRequest(String type) {

		try {
			HttpClient httpClient = HttpClientBuilder.create().build();

			String url = "";
			if (type.equals("1")) {
				url = "http://api.openweathermap.org/data/2.5/weather?q=" + grad
						+ "&APPID=c5f3aff2417a5090d65fe44c9bfdce1e";
			} 
			HttpGet getRequest = new HttpGet(url);
			getRequest.addHeader("accept", "application/json");

			HttpResponse response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				jsonResponse = "Ne mogu pronaci grad";
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;

			while ((output = br.readLine()) != null) {

				jsonResponse += output;
			}

			httpClient.getConnectionManager().shutdown();
			return jsonResponse;

		} catch (ClientProtocolException e) {
			jsonResponse = "Ne mogu pronaci grad";
			e.printStackTrace();

		} catch (IOException e) {
			jsonResponse = "Ne mogu pronaci grad";
			e.printStackTrace();
		}
		return jsonResponse;

	}

	public void setGrad(String grad) {
		this.grad = grad;

	}
}