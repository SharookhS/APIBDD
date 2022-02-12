package parallelapi.api;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class PlaceValidations extends Utils {
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data =new TestDataBuild();
	static String place_id;
	
	@Given("Add Place Payload with {string}  {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {
		
//		RestAssured.baseURI="https://rahulshettyacademy.com";

//		AddPlace p =new AddPlace();
//		p.setAccuracy(50);
//		p.setAddress("29, side layout, cohen 09");
//		p.setLanguage("French-IN");
//		p.setPhone_number("(+91) 983 893 3937");
//		p.setWebsite("https://rahulshettyacademy.com");
//		p.setName("Frontline house");
//		List<String> myList =new ArrayList<String>();
//		myList.add("shoe park");
//		myList.add("shop");
//
//		p.setTypes(myList);
//		Location l =new Location();
//		l.setLat(-38.383494);
//		l.setLng(33.427362);
//		p.setLocation(l);

		
		 
//		 Response res=given().log().all().queryParam("key", "qaclick123")
//					.body(p)
//					.when().post("/maps/api/place/add/json").
//					then().assertThat().statusCode(200).extract().response();
//		
//		String responseString=res.asString();
//		System.out.println(responseString);
		
//		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
//				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

//		ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200)
//				.expectContentType(ContentType.JSON).build();
		
//		RequestSpecification res = given().log().all().spec(req).body(p);
//		RequestSpecification res = given().log().all().spec(req).body(data.addPlacePayLoad(name,language,address));


		res=given().spec(requestSpecification())
				.body(data.addPlacePayLoad(name,language,address));

	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
	    
//		Response response = res.when().post("/maps/api/place/add/json").then().spec(resspec).extract().response();
	
		//constructor will be called with value of resource which you pass
				APIResources resourceAPI=APIResources.valueOf(resource);
				System.out.println(resourceAPI.getResource());
				
				
				resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
				
				if(method.equalsIgnoreCase("POST"))
				 response =res.when().post(resourceAPI.getResource());
				else if(method.equalsIgnoreCase("GET"))
					 response =res.when().get(resourceAPI.getResource());
	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
	    
		assertEquals(response.getStatusCode(),200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
		
		
//		String resp = response.asString();
//		System.out.println(resp);
//		JsonPath js = new JsonPath(resp);
//		assertEquals(js.get(keyValue).toString(),Expectedvalue);
		
		assertEquals(getJsonPath(response,keyValue),Expectedvalue);
	    
	}

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {
	    
		 // requestSpec
	     place_id=getJsonPath(response,"place_id");
		 res=given().spec(requestSpecification()).queryParam("place_id",place_id);
		 user_calls_with_http_request(resource,"GET");
		  String actualName=getJsonPath(response,"name");
		  assertEquals(actualName,expectedName);
		  
	}

	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		res =given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}



}
