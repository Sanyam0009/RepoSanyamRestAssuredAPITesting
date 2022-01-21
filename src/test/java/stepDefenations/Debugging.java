package stepDefenations;

import java.util.HashMap;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utilities.RestAssuredExtension;

public class Debugging {
	public static void main(String[] args){
		RestAssuredExtension req = new RestAssuredExtension("posts","GET","");
		HashMap<String,Integer> query = new HashMap<String, Integer>();
		query.put("id", 2);
		
	ResponseOptions<Response> response = req.executeWithQueryParam(query);
	System.out.println(response.getBody().jsonPath().get("title"));
		
	RestAssuredExtension req1 = new RestAssuredExtension("posts/{value}","GET","");
	HashMap<String,String> pathParams = new HashMap<String, String>();
	pathParams.put("value", "2");
	ResponseOptions<Response> response1 = req1.executeWithPathParam(pathParams);
	System.out.println(response1.getBody().jsonPath().get("title"));
	
	RestAssuredExtension req2 = new RestAssuredExtension("posts","POST","");
	HashMap<String,String> body = new HashMap<String,String>();
	body.put("name", "Sanyam");
	body.put("email", "scsanyam52@gmail.com");
	ResponseOptions<Response> response2 = req2.executeWithBody(body);
	System.out.println(response2.getBody().jsonPath().prettify());
	}

}
