package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static io.restassured.RestAssured.given;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import java.io.IOException;

public class ClassifyCuisineTest  extends AbstractTest
{

    @Test
    @DisplayName("Получение принадлежности рецепта к национальной кухни при запросе по его нименованию")
    void getRecipeBelongNationalCuisineByRecipeNameTest() throws IOException
    {
        Response response = given()
            .contentType("application/x-www-form-urlencoded")
            .formParam("title", "Thai-Style Mussels")
            .formParam("ingredientList", "mussels")
            .queryParam("apiKey", getApiKey())
            .queryParam("language", "en")
            .when()
            .post(getBaseUrl() + getClassifyCuisineURLPath())
            .then()
            .statusCode(200)
            .extract()
            .response();

        String responseBody = response.getBody().asString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(responseBody);

        String cuisine = node.get("cuisine").asText();
        Assertions.assertEquals("Thai", cuisine);
    }
}
