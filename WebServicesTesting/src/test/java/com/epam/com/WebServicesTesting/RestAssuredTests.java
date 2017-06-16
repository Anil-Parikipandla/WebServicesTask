package com.epam.com.WebServicesTesting;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestAssuredTests {

	@BeforeTest
	public void InitialSetup(){
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
	}
	
	@Test
	public void verifyStatus(){
		Response response = RestAssured.given().get("/users").andReturn();
		String actualStatusCode  = response.getStatusLine();
		Assert.assertTrue(actualStatusCode.contains("200 OK"), "Response Status code is not matching the expected Status");
	}
	
	@Test
	public void verifyResponseHeader(){
		Response response = RestAssured.given().get("/users").andReturn();	
		String contentTypeHeader = response.getHeader("content-type");
		Assert.assertNotNull(contentTypeHeader, "Header content-type doesnot exists.");
		Assert.assertEquals(contentTypeHeader, "application/json; charset=utf-8", "Content Type header is not matching with the expected Header");
	}
	
	@Test
	public void verifyReponseBody(){
		Response response = RestAssured.given().get("/users").andReturn();
		User[] responseBodyUserObj = response.as(User[].class);
		int numberOfUsers = responseBodyUserObj.length;
		Assert.assertEquals(numberOfUsers, 10, "Number of Users is not equal to 10 Users");
	}
	
	
}
