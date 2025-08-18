package com.stripe.system.centralPoint.http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;


public class HttpRequest {
	private HttpMethod method;
	private String url;
	private HttpHeaders headers;
	private Object requestBody;
	
	
	
	public HttpRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HttpRequest(HttpMethod method, String url, HttpHeaders headers, Object requestBody) {
		super();
		this.method = method;
		this.url = url;
		this.headers = headers;
		this.requestBody = requestBody;
	}
	public HttpMethod getMethod() {
		return method;
	}
	public void setMethod(HttpMethod method) {
		this.method = method;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public HttpHeaders getHeaders() {
		return headers;
	}
	public void setHeaders(HttpHeaders headers) {
		this.headers = headers;
	}
	public Object getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(Object requestBody) {
		this.requestBody = requestBody;
	}
	
	
	
	

}
