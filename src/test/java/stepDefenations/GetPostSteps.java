package stepDefenations;

import java.util.HashMap;

import org.junit.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utilities.RestAssuredExtension;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class GetPostSteps {
	public ResponseOptions<Response> response1;
	@When("^I perform \"([^\"]*)\" operation for \"([^\"]*)\"$")
	public void i_perform_operation_for(String method, String uri) throws Throwable {
		RestAssuredExtension req1 = new RestAssuredExtension(uri+ "/{value}",method);
		
		HashMap<String,String> pathParams = new HashMap<String, String>();
		pathParams.put("value", "2");
		
		response1 = req1.executeWithPathParam(pathParams);
		System.out.println(response1.getBody().jsonPath().get("title").toString());
	    
	}

	@Then("^I should be able to see title as \"([^\"]*)\"$")
	public void i_should_be_able_to_see_title_as(String title){
		assertThat(response1.getBody().jsonPath().get("title").toString(),equalTo(title));
		String titleGet = response1.getBody().jsonPath().get("title");
		System.out.println("Testing - " + titleGet);
		//Assert.assertEquals(titleGet, title);
	  
	}
	
	@When ("^I perform \"(.*)\" operation with body for \"(.*)\"$")
	public void perfor_post_with_body(String method,String uri){
		RestAssuredExtension res= new RestAssuredExtension(uri, method);
		
		HashMap<String,String> body = new HashMap<String, String>();
		body.put("id", "102");
		body.put("name", "Sanyam");
		body.put("email", "scsanyam52@gmail.com");
		
		response1 = res.executeWithBody(body);
		System.out.println(response1.getBody().jsonPath().prettify());
	}


}
