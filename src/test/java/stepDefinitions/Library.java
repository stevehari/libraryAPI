package stepDefinitions;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import resources.LibrarySource;
import resources.bodyData;
import resources.utils;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.io.IOException;

import org.junit.runner.RunWith;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;


@RunWith(Cucumber.class)

public class Library extends utils {
	
	bodyData body = new bodyData();
	
	RequestSpecification RES1;
	Response RES2;
	
	@Given("add a book by giving payload with details name, isbn, aisle and author")
	public void add_a_book_by_giving_payload_with_details_name_isbn_aisle_and_author() throws IOException {
		
		ArrayList<String> d = getData("Library","1");
		
		RES1= given().spec(requestSpec()).body(body.getAddBookBody(d.get(1),d.get(2),d.get(3),d.get(4)));
	}
	@When("user calls {string} with {string} HTTP request")
	public void user_calls_with_http_request(String Path, String method) {
		
		LibrarySource path= LibrarySource.valueOf(Path);
		
		if(method.equalsIgnoreCase("POST"))
		{
			RES2=RES1.when().post(path.resourceReturn()).then().log().all().extract().response();	
		}
	}
	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
		
		int Actual_value= int1;
		
		assertEquals(RES2.statusCode(), Actual_value);
	
	}
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String MSG, String Status) {
	   
		String Actual_Value=getJsonPath(RES2,MSG);
		
	Assert.assertEquals(Actual_Value, Status);
	
	}


}
