package Helpers;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;

import org.apache.http.util.Asserts;

import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty.Type;
import com.fasterxml.jackson.databind.ObjectMapper;

import Constants.EndPoint;
import Utils.ConfigManager;
//import groovyjarjarantlr.collections.List;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojoModel.PojoforPost;
import pojoModel.UserAddress;

public class ServiceHelpers {
	private static String BASE_URL = ConfigManager.getInstance().getString("BASE_URL");
    private static  String USERNAME = ConfigManager.getInstance().getString("USERNAME");
	private static  String PASSWORD = ConfigManager.getInstance().getString("PASSWORD");
	private static String end_point;
	PojoforPost pop = new PojoforPost();
	UserAddress uad = new UserAddress();
	
public 	ServiceHelpers() {
	RestAssured.baseURI = BASE_URL;
}

public Response getAllUsers(String endPoint){
	System.out.println("Calling API: " + BASE_URL + endPoint);
	Response response = RestAssured.given()
			.contentType(ContentType.JSON)
			.when().auth().basic(USERNAME, PASSWORD).baseUri(BASE_URL)
			.get(endPoint);
   return response;

}
//public List<PojoforPost> getAllUsers(String endpoint){
//	Response response = RestAssured.given()
//			.contentType(ContentType.JSON)
//			.when().auth().basic(USERNAME, PASSWORD)
//			.get(end_point);
//
//   Type type = new TypeReference<List<PojoforPost>>(){}.getType();
//   List<PojoforPost> usersList = response.as(type);
//   System.out.println(usersList);
//   return usersList;
//   
//}

public Response CreateUser(String endPoint) {
	pop.setUserFirstName("Anu");
	pop.setUserLastName("sophia");
	pop.setUserContactNumber("9998908908");
	pop.setUserEmailId("new_donaldll21208@gmail.com");
	uad.setPlotNumber("12-tre");
	uad.setStreet("laurel ways");
	uad.setState("VAS");
	uad.setCountry("canada");
	uad.setZipCode("20198");
	pop.setUserAddress(uad);

	System.out.println(pop);
	Response response = RestAssured.given()
			.contentType(ContentType.JSON)
			.when().auth().basic(USERNAME, PASSWORD)
			.body(pop).baseUri(BASE_URL)
			.post(endPoint);

	String jsonString = response.asString();
	String email = response.jsonPath().get("user_email_id");
	String id =  response.jsonPath().getString("user_id");
	System.out.println(jsonString);
	System.out.println(email + id);
	return response;
	
}

public Response updateUser(String endPoint, String userID) {
	PojoforPost pop = new PojoforPost();
	UserAddress uad = new UserAddress();
	pop.setUserFirstName("AnuSophia");
	pop.setUserLastName("karthi");
	pop.setUserContactNumber("4998908981");
	pop.setUserEmailId("change_dnal9l21281@gmail.com");
	uad.setPlotNumber("12-ABCD1");
	uad.setStreet("laurel changechanges");
	uad.setState("VAA");
	uad.setCountry("usa");
	uad.setZipCode("20971");
	pop.setUserAddress(uad);
	endPoint = endPoint + "/" + userID;

	System.out.println(pop);
	Response response = RestAssured.given()
			.contentType(ContentType.JSON)
			.when().auth().basic(USERNAME, PASSWORD)
			.pathParam("User_id", userID)
			.body(pop).baseUri(BASE_URL)
			.put(endPoint);

	String jsonString = response.asString();
	String email = response.jsonPath().get("user_email_id");
	String plot_num =  response.jsonPath().getString("userAddress.plotNumber");
	String street =  response.jsonPath().getString("userAddress.street");
	System.out.println(jsonString);
	System.out.println(email + plot_num + street);
	return response;
	
}

public Response deleteUser(String endPoint, String userID) {
	PojoforPost pop = new PojoforPost();
	UserAddress uad = new UserAddress();
	pop.setUserFirstName("Anu");
	pop.setUserLastName("sophia");
	pop.setUserContactNumber("4908908981");
	pop.setUserEmailId("change_dnaldll21282@gmail.com");
	uad.setPlotNumber("12-ABC");
	uad.setStreet("6laurel chane");
	uad.setState("VA");
	uad.setCountry("usa");
	uad.setZipCode("20171");
	pop.setUserAddress(uad);
	endPoint = endPoint + "/" + userID;

	System.out.println(pop);
	Response response = RestAssured.given()
			.contentType(ContentType.JSON)
			.when().auth().basic(USERNAME, PASSWORD)
			.pathParam("User_id", userID)
			.body(pop).baseUri(BASE_URL)
			.delete(endPoint);

	String jsonString = response.asString();
	String email = response.jsonPath().get("user_email_id");
	String plot_num =  response.jsonPath().getString("userAddress.plotNumber");
	String street =  response.jsonPath().getString("userAddress.street");
	System.out.println(jsonString);
	System.out.println(email + plot_num + street);
	return response;
	
}
	
}
