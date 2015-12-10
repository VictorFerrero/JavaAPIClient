import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;


public class APIClient {

	private final String USER_AGENT = "Mozilla/5.0";
	
	public APIClient() {
		
	}
	
	public HttpPostResponse sendPost(HashMap<String, String> hashMap, String route)
			throws ClientProtocolException, IOException {
		
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(route);

		// Request parameters and other properties.
		List<NameValuePair> params = this.initializePostData(hashMap);
		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		//Execute and get the response.
		HttpResponse response = httpclient.execute(httppost);
		int responseCode = response.getStatusLine().getStatusCode();
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		String strResponse = "";
		while ((line = rd.readLine()) != null) {
			strResponse = strResponse + line;
		}
		rd.close();
		return new HttpPostResponse(responseCode, strResponse, params, response, route);
	}
	
	public HttpGetResponse sendGet(String url) throws ClientProtocolException, IOException {
	//	String url = "http://www.google.com/search?q=developer";

		//HttpClient client = new DefaultHttpClient();
		HttpClient httpclient = HttpClients.createDefault();
		HttpGet request = new HttpGet(url);

		// add request header
		request.addHeader("User-Agent", USER_AGENT);

		HttpResponse response = httpclient.execute(request);
		
		int responseCode = response.getStatusLine().getStatusCode();
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		String strResponse = result.toString();
		return new HttpGetResponse(responseCode, strResponse, url, response);
	}
	
	private List<NameValuePair> initializePostData(HashMap<String, String> postData) {
		List<NameValuePair> toReturn = new ArrayList<NameValuePair>();
		for(String key : postData.keySet()) {
			String value = postData.get(key);
			toReturn.add(new BasicNameValuePair(key,value));
		}
		return toReturn;
	}
}
