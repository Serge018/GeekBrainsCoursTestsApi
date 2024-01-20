package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson3;


import org.junit.jupiter.api.BeforeAll;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import io.restassured.RestAssured;


public abstract class AbstractTest
{
    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String baseUrl;
    private static String classifyCuisineURLPath;
    private static String apiKey;

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
}
