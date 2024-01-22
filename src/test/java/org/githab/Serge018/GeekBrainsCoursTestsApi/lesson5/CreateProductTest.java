package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5;

import com.github.javafaker.Faker;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.api.ProductService;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.dto.GetProductResponse;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.utils.RetrofitUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;
import static org.hamcrest.MatcherAssert.assertThat;


import java.io.IOException;


public class CreateProductTest
{
    static ProductService productService;
    GetProductResponse product;
    Faker faker = new Faker();
    int id;


    @BeforeAll
    static void beforeAll() throws IOException
    {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }


    @BeforeEach
    void setUp()
    {
        product = new GetProductResponse()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice(777);
    }


    @Test
    void createProductInFoodCategoryTest() throws IOException
    {
        Response<GetProductResponse> response = productService.createProduct(product).execute();
        id =  response.body().getId();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }

}
