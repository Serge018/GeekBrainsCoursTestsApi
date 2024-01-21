package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson3;

import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson4.dto.ClassifyCuisineResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static io.restassured.RestAssured.given;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

public class ClassifyCuisineTest  extends AbstractTestClassifyCuisine
{

    @Test
    @DisplayName("Тест получения данных о принадлежности рецепта к определённой национальной кухни по его нименованию")
    void getRecipeBelongNationalCuisineByRecipeNameTest() throws IOException
    {
        given()
            .spec(getRequestSpecification())
            .formParam("title", "Thai-Style Mussels")
            .when()
            .post(getBaseUrl() + getClassifyCuisineURLPath())
            .then()
            .spec(getResponseSpecification());
    }

    @Test
    @DisplayName("Тест получения данных о принадлежности рецепта к определённой национальной кухни по его нименованию и ингридиенту")
    void getRecipeBelongNationalCuisineByRecipeNameAndIbnTest() throws IOException
    {
        // Проверка осуществляется с использованеим dto класса
        ClassifyCuisineResponse response = given()
            .spec(getRequestSpecification())
            .formParam("title", "Thai-Style Mussels")
            .formParam("ingredientList", "mussels")
            .when()
            .post(getBaseUrl() + getClassifyCuisineURLPath())
            .then()
            .extract()
            .response()
            .body()
            .as(ClassifyCuisineResponse.class);

        assertThat(response.getCuisine(), containsString("Asian"));
    }
}
