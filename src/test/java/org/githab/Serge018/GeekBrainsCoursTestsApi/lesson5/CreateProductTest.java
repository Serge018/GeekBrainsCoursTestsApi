package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.api.ProductService;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.dto.Product;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.utils.RetrofitUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import okhttp3.ResponseBody;

import java.io.IOException;

// TODO вынести теств CRUD в разные тестовые классы
public class CreateProductTest
{
    static ProductService productService;
    Product product;
    Faker faker = new Faker();
    static int id;


    @BeforeAll
    static void beforeAll() throws IOException
    {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }


    @BeforeEach
    void setUp()
    {
        product = new Product()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 10000));
    }


    @Test
    @DisplayName("Тест создания продукта")
    void createProductInFoodCategoryTest() throws IOException
    {
        Response<Product> response = productService.createProduct(product).execute();
        // Сохраняем идентификатор созданного продукта для его удаления по завершению тестов
        id = response.body().getId();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }


    @SneakyThrows
    @AfterEach
    void tearDown()
    {
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
