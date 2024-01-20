package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson3;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class LoggingTest extends AbstractTest
{

    @Test
    void getRequestLogTest()
    {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information")
                .then().statusCode(200);

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information")
                .then().statusCode(201);;
    }

    @Test
    void getResponseLogTest()
    {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information")
                .then().statusCode(200);;
    }

    @Test
    void getErrorTest()
    {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information")
                .then().statusCode(201);
    }
}