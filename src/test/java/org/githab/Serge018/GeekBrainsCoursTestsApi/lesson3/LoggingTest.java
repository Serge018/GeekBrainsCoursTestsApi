package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson3;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;

public class LoggingTest extends AbstractTest
{

    @Test
    @Disabled
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
                .then().statusCode(200);
    }

    @Test
    @Disabled
    void getResponseLogTest()
    {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information")
                .then().statusCode(200);
    }

    @Test
    @Disabled
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