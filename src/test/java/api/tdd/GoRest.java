package api.tdd;

import api.pojo_classes.go_rest.CreateGoRestUser;
import api.pojo_classes.go_rest.UpdateGoRestUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.ConfigReader;

import static org.hamcrest.core.IsEqual.equalTo;

public class GoRest {

    Response response;
    /**
     * ObjectMapper is a class coming form fasterxml to convert Java object to Json
     */
    ObjectMapper objectMapper = new ObjectMapper();

    Faker faker = new Faker();

    int expectedGoRestId;
    String expectedGoRestName;
    String expectedGoRestEmail;
    String expectedGoRestGender;
    String expectedGoRestStatus;

    @BeforeTest
    public void beforeTest() {
        System.out.println("Starting the API test");
        // By having RestAssured URI set implicitly in to rest assured
        // we just add path to the post call
        RestAssured.baseURI = ConfigReader.getProperty("GoRestBaseURL");
    }

    @Test
    public void goRestCRUD() throws JsonProcessingException {
        // Creating a POJO (Bean) object
        CreateGoRestUser createGoRestUser = new CreateGoRestUser();

        // assigned the values to the attributes
        createGoRestUser.setName("Tom");
        createGoRestUser.setGender("male");
        createGoRestUser.setEmail(faker.internet().emailAddress());
        createGoRestUser.setStatus("active");

        System.out.println("======= Creating the user with POST request =========");

        response = RestAssured
                .given().log().all()
//                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer cd6f43f79e931dc381c5c228f3e80c9f6990ec23e970e0325e206b9d241e551e")
                .body(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(createGoRestUser))
//                .when().post("https://gorest.co.in/public/v2/users")
                .when().post("/public/v2/users")
                .then().log().all()
                //validating the status code with rest assured
                .and().assertThat().statusCode(201)
                //validating the response time is less than the specified one
                //.time(Matchers.lessThan(4000L))
                //validating the value from the body with hamcrest
                .body("name", equalTo("Tom"))
                //validating the response content type
                .contentType(ContentType.JSON)
                .extract().response();


        System.out.println("======= Fetching the user with GET request =========");

        expectedGoRestId = response.jsonPath().getInt("id");

        response = RestAssured
                .given().log().all()
//                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer cd6f43f79e931dc381c5c228f3e80c9f6990ec23e970e0325e206b9d241e551e")
                .when().get("/public/v2/users/"+ expectedGoRestId)
                .then().log().all()
                //validating the status code with rest assured
                .and().assertThat().statusCode(200)
                //validating the response time is less than the specified one
                // .time(Matchers.lessThan(4000L))
                //validating the value from the body with hamcrest
                .body("name", equalTo("Tom"))
                //validating the response content type
                .contentType(ContentType.JSON)
                .extract().response();


        System.out.println("-==========Updating a User with PUT request============-");


        UpdateGoRestUser updateGoRestUser = new UpdateGoRestUser();
        updateGoRestUser.setName("TGlobal");
        updateGoRestUser.setEmail(faker.internet().emailAddress());

        response = RestAssured
                .given().log().all()
//                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer cd6f43f79e931dc381c5c228f3e80c9f6990ec23e970e0325e206b9d241e551e")
                .body(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(updateGoRestUser))
//                .when().post("https://gorest.co.in/public/v2/users")
                .when().put("/public/v2/users/" + expectedGoRestId)
                .then().log().all()
                //validating the status code with rest assured
                .and().assertThat().statusCode(200)
                //validating the response time is less than the specified one
                //.time(Matchers.lessThan(6000L))
                //validating the value from the body with hamcrest
                .body("name", equalTo("TGlobal"))
                //validating the response content type
                .contentType(ContentType.JSON)
                .extract().response();

        expectedGoRestName = updateGoRestUser.getName();
        expectedGoRestEmail = updateGoRestUser.getEmail();
        expectedGoRestGender = createGoRestUser.getGender();
        expectedGoRestStatus = createGoRestUser.getStatus();

        //"id" in the getInt is the name of the attribute in the response body
        int actualGoRestId = response.jsonPath().getInt("id");
        String actualGoRestName = response.jsonPath().getString("name");
        String actualGoRestEmail = response.jsonPath().getString("email");
        String actualGoRestGender = response.jsonPath().getString("gender");
        String actualGoRestStatus = response.jsonPath().getString("status");

        Assert.assertEquals(actualGoRestId, expectedGoRestId);
        Assert.assertEquals(actualGoRestName, expectedGoRestName);
        Assert.assertEquals(actualGoRestEmail, expectedGoRestEmail);
        Assert.assertEquals(actualGoRestGender, expectedGoRestGender);
        Assert.assertEquals(actualGoRestStatus, expectedGoRestStatus);

        System.out.println("-==========Deleting a User with DELETE request============-");

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization","Bearer cd6f43f79e931dc381c5c228f3e80c9f6990ec23e970e0325e206b9d241e551e")
                .when().delete("/public/v2/users/"+ expectedGoRestId)
                .then().log().all()
                .and().assertThat().statusCode(204)
                //.time(Matchers.lessThan(6000L))
                .extract().response();

    }
}
