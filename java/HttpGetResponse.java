import org.apache.http.HttpResponse;


public class HttpGetResponse {
	private int responseCode;
	private String strResponse;
	private String urlForTheCall;
	private HttpResponse response;
	
	public HttpGetResponse(int rc, String r, String urlForTheCall, HttpResponse response) {
		this.responseCode = rc;
		this.response = response;
		this.strResponse = r;
		this.urlForTheCall = urlForTheCall;
	}
	
	public HttpResponse getHttpResponse() {
		return this.response;
	}
	
	public String getUrlForTheCall() {
		return this.urlForTheCall;
	}
	
	public int getResponseCode() {
		return this.responseCode;
	}

	public String toString() {
		return this.strResponse;
	}
	
}