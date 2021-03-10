package stepDefenations;

import java.awt.image.ReplicateScaleFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import groovy.util.slurpersupport.ReplacementNode;
import groovyjarjarasm.asm.commons.InstructionAdapter;
import io.restassured.RestAssured;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import pojo.Comments;
import pojo.Photos;
import utilities.RestAssuredExtension;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class GetPostSteps {
	public ResponseOptions<Response> response1;

	@When("^I perform \"([^\"]*)\" operation for \"([^\"]*)\" with path param key \"([^\"]*)\" and \"([^\"]*)\"$")
	public void i_perform_operation_for_with_path_param_key_and(String method, String uri, String key, String value1)
			throws Throwable {
		RestAssuredExtension req1 = new RestAssuredExtension(uri + "/{" + key + "}", method);

		HashMap<String, String> pathParams = new HashMap<String, String>();
		pathParams.put(key, value1);

		response1 = req1.executeWithPathParam(pathParams);
	}

	@When("^I perform \"([^\"]*)\" operation for \"([^\"]*)\"$")
	public void i_perform_operation_for(String method, String uri) throws Throwable {

		RestAssuredExtension objmethodonly = new RestAssuredExtension(uri, method);
		response1 = objmethodonly.executeMethodOnly();
	}

	@Then("^I should be able to see title as \"([^\"]*)\"$")
	public void i_should_be_able_to_see_title_as(String title) {
		assertThat(response1.getBody().jsonPath().get("title").toString(),equalTo(title));
		// Assert.assertEquals(titleGet, title);

	}

	@Then("^I should be able to see ArrayList title as \"([^\"]*)\"$")
	public void i_should_be_able_to_see_ArrayList_title_as(String title) throws Throwable {
		ArrayList<String> titleGet = response1.getBody().jsonPath().get("title");
		System.out.println("Testing - " + titleGet.get(0));
		Assert.assertTrue(titleGet.get(0).equals(title));
	}

	@When("^I perform \"([^\"]*)\" operation for \"([^\"]*)\" with QueryParm key \"([^\"]*)\" and value (\\d+)$")
	public void i_perform_operation_for_with_QueryParm_key_and_value(String method, String uri, String key,
			Integer value) throws Throwable {
		RestAssuredExtension res = new RestAssuredExtension(uri, method);
		HashMap<String, Integer> queryparam = new HashMap<String, Integer>();
		queryparam.put(key, value);
		response1 = res.executeWithQueryParam(queryparam);
	}

	@When("^I perform \"(.*)\" operation with body for \"(.*)\"$")
	public void perfor_post_with_body(String method, String uri) {
		RestAssuredExtension res = new RestAssuredExtension(uri, method);

		HashMap<String, String> body = new HashMap<String, String>();
		body.put("id", "102");
		body.put("name", "Sanyam");
		body.put("email", "scsanyam52@gmail.com");

		response1 = res.executeWithBody(body);
		System.out.println(response1.getBody().jsonPath().prettify());
	}

	@Then("^I sould be able to get status code as \"([^\"]*)\"$")
	public void i_sould_be_able_to_get_status_code_as(String statuscode) {
		assertThat(String.valueOf(response1.getStatusCode()), equalTo(statuscode));
	}

	@And("^I should be able to covert JSON to POJO$")
	public void i_should_be_able_to_covert_JSON_to_POJO() {
		Comments comment = response1.getBody().as(Comments.class);
		System.out.println("Fatched from Pojo - " + comment.getEmail());
	}

	@When("^I perform \"([^\"]*)\" operation for \"([^\"]*)\" with POJO body$")
	public void i_perform_operation_for_with_POJO_body(String method, String uri, DataTable table) {
		RestAssuredExtension rae = new RestAssuredExtension(uri, method);

		List<List<String>> values = table.raw();
		Photos photoObj = new Photos();
		photoObj.setAlbumId(Integer.valueOf(values.get(1).get(0)));
		photoObj.setTitle(values.get(1).get(1));
		photoObj.setUrl(values.get(1).get(2));

		response1 = rae.executeWithBodyObj(photoObj);

	}

	@And("^should get expected body \"(.*)\" as response$")
	public void should_get_expected_body_as_response(String jsonfile) throws Throwable {
		String postResp = response1.getBody().asString();
		// System.out.println(postResp);
		assertThat(postResp, JsonSchemaValidator.matchesJsonSchemaInClasspath(jsonfile));
	}
	
}
