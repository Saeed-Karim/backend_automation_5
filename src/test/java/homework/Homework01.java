package homework;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

public class Homework01 {
    public static void main(String[] args) {


        Response response;

        Faker faker = new Faker();

        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 269a3a53528ae2d33ce8ddbe5f3706f3fc56fa962fd6d43e559d01f07bd6b1d4")
                .body("{\n" +
                        "    \"firstName\": \"" + faker.name().firstName() + "\",\n" +
                        "    \"lastName\": \"" + faker.name().lastName() + "\",\n" +
                        "    \"email\": \"" + faker.internet().emailAddress() + "\",\n" +
                        "    \"dob\": \"2000-01-01\"\n" +
                        "}")
                .when().post("https://tech-global-training.com/students")
                .then().log().all().extract().response();


          int postId = response.jsonPath().getInt("id");


        System.out.println("Id is coming from response " + postId);


        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 269a3a53528ae2d33ce8ddbe5f3706f3fc56fa962fd6d43e559d01f07bd6b1d4")
                .when().get("https://tech-global-training.com/students/" + postId)
                .then().log().all().extract().response();


        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 269a3a53528ae2d33ce8ddbe5f3706f3fc56fa962fd6d43e559d01f07bd6b1d4")
                .when().get("https://tech-global-training.com/students")
                .then().log().all().extract().response();





        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 269a3a53528ae2d33ce8ddbe5f3706f3fc56fa962fd6d43e559d01f07bd6b1d4")
                .body("{\n" +
                        "    \"firstName\": \"" + faker.name().firstName() + "\",\n" +
                        "    \"lastName\": \"" + faker.name().lastName() + "\",\n" +
                        "    \"email\": \"" + faker.internet().emailAddress() + "\",\n" +
                        "    \"dob\": \"2000-01-01\"\n" +
                        "}")
                .when().put("https://tech-global-training.com/students/" + postId)
                .then().log().all().extract().response();



        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 269a3a53528ae2d33ce8ddbe5f3706f3fc56fa962fd6d43e559d01f07bd6b1d4")
                .body("{\"dob\": \"2000-01-02\"}")
                .when().patch("https://tech-global-training.com/students/" + postId)
                .then().log().all().extract().response();

       response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 269a3a53528ae2d33ce8ddbe5f3706f3fc56fa962fd6d43e559d01f07bd6b1d4")
                .when().delete("https://tech-global-training.com/students/" + postId)
                .then().log().all().extract().response();




    }


    }






