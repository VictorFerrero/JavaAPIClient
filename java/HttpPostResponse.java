import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;


public class HttpPostResponse {
	private int responseCode;
	private String strResponse;
	private List<NameValuePair> postData;
	private HttpResponse response;
	private String route;
	
	public HttpPostResponse
	(int rc, String strResponse, List<NameValuePair> postData, HttpResponse response, String route) {
		this.responseCode = rc;
		this.response = response;
		this.strResponse = strResponse;
		this.postData = postData;
		this.route = route;
	}
	
	public List<NameValuePair> getPostData() {
		return this.postData;
	}
	
	public String getRoute() {
		return this.route;
	}
	
	public HttpResponse getHttpResponse() {
		return this.response;
	}
	
	public int getResponseCode() {
		return this.responseCode;
	}

	public String toString() {
		return this.strResponse;
	}
}