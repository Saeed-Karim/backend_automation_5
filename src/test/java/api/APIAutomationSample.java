package api;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

public class APIAutomationSample {
    public static void main(String[] args) {

        Response response;

        Faker faker = new Faker();


        //Creating A User
        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 28ca0f699d6aa5c709a9b1d0e0a67861e1bf57d3d7ad524d715d22b9e7d8b018")
                .body("{\n" +
                        "    \"name\": \"" + faker.name().fullName() + "\",\n" +
                        "    \"gender\": \"male\",\n" +
                        "    \"email\": \""+ faker.internet().emailAddress() +"\",\n" +
                        "    \"status\": \"active\"\n" +
                        "}")
                .when().post("https://gorest.co.in/public/v2/users")
                .then().log().all().extract().response();

        System.out.println(response.asString());

        int postId = response.jsonPath().getInt("id");

        //Getting A Specific User
        /*response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 28ca0f699d6aa5c709a9b1d0e0a67861e1bf57d3d7ad524d715d22b9e7d8b018")
                .when().get("https://gorest.co.in/public/v2/users/" + postId)
                .then().log().all().extract().response();

        System.out.println("\n Id is coming from response \n");
        System.out.println(response.asString());*/

        /*
        System.out.println("\n________________Getting All Users_________________\n");

        // Getting all The Users
        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 28ca0f699d6aa5c709a9b1d0e0a67861e1bf57d3d7ad524d715d22b9e7d8b018")
                .when().get("https://gorest.co.in/public/v2/users" )
                .then().log().all().extract().response();



        System.out.println(response.asString());*/


        //Updating a User
        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 28ca0f699d6aa5c709a9b1d0e0a67861e1bf57d3d7ad524d715d22b9e7d8b018")
                .body("{\n" +
                        "    \"name\": \"" + faker.name().fullName() + "\",\n" +
                        "    \"gender\": \"male\",\n" +
                        "    \"email\": \""+ faker.internet().emailAddress() +"\",\n" +
                        "    \"status\": \"active\"\n" +
                        "}")
                .when().put("https://gorest.co.in/public/v2/users/" + postId )
                .then().log().all().extract().response();


        System.out.println("\n________________Updating A User_________________\n");

        System.out.println(response.asString());


        int patchId = response.jsonPath().getInt("id");

        Assert.assertEquals(postId, patchId, "Expected id " + patchId + "we found " + postId);



        System.out.println("\n________________Deleting A User_________________\n");

        //Deleting a User
        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 28ca0f699d6aa5c709a9b1d0e0a67861e1bf57d3d7ad524d715d22b9e7d8b018")
                .when().delete("https://gorest.co.in/public/v2/users/" + postId )
                .then().log().all().extract().response();

        System.out.println(response.asString());
    }
}
