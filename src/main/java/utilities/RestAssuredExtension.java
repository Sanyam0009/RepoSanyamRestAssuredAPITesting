package utilities;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

public class RestAssuredExtension {
	private RequestSpecBuilder builder = new RequestSpecBuilder();
	private String uri;
	private String method;

	public RestAssuredExtension(String uri, String method) {
		String url = "https://jsonplaceholder.typicode.com/";
		this.uri =  url + uri;
		this.method = method;
		//Alternet way to do this by RequestSpecBuilder // We will do this by RequestSpecification
		// builder.addHeader("Content-Type", "application/json");
		// builder.addHeader("Authorization","Bearer TokenName_211_2313_34");
		//builder.setContentType(ContentType.JSON);
		// builder.setBaseUri("https://reqres.in/api/");

	}

	private ResponseOptions<Response> executeAPI() {
		RequestSpecification requestSpecification = builder.build();
		RequestSpecification request = RestAssured.given().spec(requestSpecification);
		request.contentType(ContentType.JSON);
		//request.queryParams(query); // if want to do by RequestSpecification
		// request.pathParams(parameterNameValuePairs); // if we want to do by request specification
		
		if (this.method.equalsIgnoreCase("GET"))
			return request.get(this.uri);
		else if (this.method.equalsIgnoreCase("POST"))
			return request.post(this.uri);
		else if (this.method.equalsIgnoreCase("DELETE"))
			return request.delete(this.uri);
		return null;
		}
	
	public ResponseOptions<Response> executeWithQueryParam(HashMap<String,Integer> query){
		builder.addQueryParams(query);
		return executeAPI();
	}

	public ResponseOptions<Response> executeWithPathParam(HashMap<String, String> pathParams){
		builder.addPathParams(pathParams);
		return executeAPI();
	}
	
	public ResponseOptions<Response> executeWithBody (HashMap<String, String> body){
		//builder.addPathParams(pathParams);
		builder.setBody(body);
		return executeAPI();
	}
	
	public ResponseOptions<Response> executeWithBodyObj(Object body){
		builder.setBody(body);
		return executeAPI();
		
	}
	
	public ResponseOptions<Response> executeMethodOnly(){
		return executeAPI();
	}
}
