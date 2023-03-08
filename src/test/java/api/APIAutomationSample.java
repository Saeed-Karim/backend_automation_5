package api;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

public class APIAutomationSample {
    public static void main(String[] args) {

        /**
         * Response is an interface coming from the RestAssured Library
         * The Response variable "response" stores all the components of API calls
         * including the request and response
         * RestAssured is written with BDD flow
         *
         */
        Response response;

        Faker faker = new Faker();

        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 82e648c9184caa17b36b3591357864868e4bcdcae3faf9adf2c53ffbe7c29c11")
                .body("{\n" +
                        "    \"name\": \"" + faker.name().fullName() + "\",\n" +
                        "    \"gender\": \"male\",\n" +
                        "    \"email\": \"" + faker.internet().emailAddress() + "\",\n" +
                        "    \"status\": \"active\"\n" +
                        "}")
                .when().post("https://gorest.co.in/public/v2/users")
                .then().log().all().extract().response();

       // System.out.println(response.asString());

        int postId = response.jsonPath().getInt("id");
       // String name = response.jsonPath().getString("name");

        System.out.println("Id is coming from response " + postId);
       // System.out.println("+++++");
       // System.out.println(name);

        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 82e648c9184caa17b36b3591357864868e4bcdcae3faf9adf2c53ffbe7c29c11")
                .when().get("https://gorest.co.in/public/v2/users/" + postId)
                .then().log().all().extract().response();

     //   response = RestAssured
      //          .given().log().all()
      //          .header("Content-Type", "application/json")
       //         .header("Authorization", "Bearer 82e648c9184caa17b36b3591357864868e4bcdcae3faf9adf2c53ffbe7c29c11")
       //         .when().get("https://gorest.co.in/public/v2/users")
       //         .then().log().all().extract().response();


        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 82e648c9184caa17b36b3591357864868e4bcdcae3faf9adf2c53ffbe7c29c11")
                .body("{\n" +
                        "    \"name\": \"" + faker.name().fullName() + "\",\n" +
                        "    \"gender\": \"female\",\n" +
                        "    \"email\": \"" + faker.internet().emailAddress() + "\",\n" +
                        "    \"status\": \"active\"\n" +
                        "}")
                .when().put("https://gorest.co.in/public/v2/users/" + postId)
                .then().log().all().extract().response();


        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 82e648c9184caa17b36b3591357864868e4bcdcae3faf9adf2c53ffbe7c29c11")
                .body("{\"gender\": \"male\"}")
                .when().patch("https://gorest.co.in/public/v2/users/" + postId)
                .then().log().all().extract().response();

//        response = RestAssured
//                .given().log().all()
//                .header("Content-Type", "application/json")
//                .header("Authorization", "Bearer 82e648c9184caa17b36b3591357864868e4bcdcae3faf9adf2c53ffbe7c29c11")
//                .when().delete("https://gorest.co.in/public/v2/users/" + postId)
//                .then().log().all().extract().response();

      //  int patchId = response.jsonPath().getInt("id");
        int patchId = 5;

        Assert.assertEquals(postId, patchId, "Expected id " + postId+ " we found " + patchId);


    }




    }





