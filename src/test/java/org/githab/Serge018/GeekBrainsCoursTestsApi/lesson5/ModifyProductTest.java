package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5;

import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;

import com.github.javafaker.Faker;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.api.ProductService;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.dto.Product;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.utils.ConfigUtils;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.utils.RetrofitUtils;
import org.junit.jupiter.api.*;
import retrofit2.Response;
import java.io.IOException;


public class ModifyProductTest
{
    static ProductService productService;
    static int productIdToBeModify;
    static Faker faker = new Faker();
    // Данные для обновления
    static String categoryTitle = "Electronic";
    static String newTitle;
    static int newPrice;


    @BeforeAll
    static void beforeAll() throws IOException
    {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
        productIdToBeModify = ConfigUtils.getProductIdToBeModify();
    }


    @BeforeEach
    void setUp()
    {
        // Данные для обновления
        newTitle = faker.commerce().productName();
        newPrice = (int) (Math.random() * 100000000);
    }


    @Test
    @DisplayName("Тест обновления данных продукта")
    void updateProductData() throws IOException
    {
        Product productToBeModify = new Product()
                .withId(productIdToBeModify)
                .withCategoryTitle(categoryTitle)
                .withTitle(newTitle)
                .withPrice(newPrice);

        Response<Product> response = productService.modifyProduct(productToBeModify).execute();
        Product body = response.body();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(body.getTitle(), Matchers.equalTo(newTitle));
        assertThat(body.getPrice(), Matchers.equalTo(newPrice));
    }
}
