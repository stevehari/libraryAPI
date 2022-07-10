package stepDefinitions;


import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import resources.LibrarySource;
import resources.bodyData;
import resources.utils;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

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

	static String Book_ID;
	

	
	@Given("add a book by giving payload with details name, isbn, aisle and author")
	public void add_a_book_by_giving_payload_with_details_name_isbn_aisle_and_author() throws IOException {
		
	
		
		RES1= given().spec(requestSpec()).body(body.getAddBookBody());
	}
	
	@When("user calls {string} with {string} HTTP request")
	
	public void user_calls_with_http_request(String Path,String method) 
	{
		
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
		
		Book_ID=getJsonPath(RES2,"ID");
		
		System.out.println(Book_ID);
		
		if(Status.equalsIgnoreCase("successfully added"))
		{
			assertEquals(Status, Actual_Value);
		}
		
		else if(Status.equalsIgnoreCase("book is successfully deleted"))
		{
			assertEquals(Status, Actual_Value);
			
		}
	   
	}
	
	@Given("Using the book ID delete the book")
	public void using_the_book_id_delete_the_book() throws IOException {
		
		RES1= given().spec(requestSpec()).body(body.getDeleteBookBody(Book_ID));		

	}


}
