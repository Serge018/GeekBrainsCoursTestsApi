package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson3;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import io.restassured.RestAssured;


public abstract class AbstractTestClassifyCuisine
{
    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String baseUrl;
    private static String classifyCuisineURLPath;
    private static String apiKey;
    private static String language = "en";

    static RequestSpecification requestSpecification = null;
    static ResponseSpecification responseSpecification = null;


    @BeforeAll
    static void setUp()
    {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @BeforeAll
    static void initTest() throws IOException, FileNotFoundException
    {
        configFile = new FileInputStream("src/main/resources/my.properties");
        prop.load(configFile);

        apiKey = prop.getProperty("apiKey");
        baseUrl = prop.getProperty("base_url");

        classifyCuisineURLPath = prop.getProperty("classify_cuisine_url_path");

        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("apiKey", apiKey)
                .addQueryParam("language", language)
                .setContentType("application/x-www-form-urlencoded")
                .log(LogDetail.ALL)
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();
    }


    public static String getApiKey()
    {
        return apiKey;
    }

    public static String getBaseUrl()
    {
        return baseUrl;
    }

    public static String getClassifyCuisineURLPath()
    {
        return classifyCuisineURLPath;
    }

    public static RequestSpecification getRequestSpecification()
    {
        return requestSpecification;
    }

    public static ResponseSpecification getResponseSpecification()
    {
        return responseSpecification;
    }
}
