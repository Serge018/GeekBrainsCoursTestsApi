package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.api.ProductService;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.dto.GetProductResponse;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.utils.RetrofitUtils;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.utils.ConfigUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.*;
import retrofit2.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import okhttp3.ResponseBody;

import java.io.IOException;


public class CreateProductTest
{
    static ProductService productService;
    GetProductResponse product;
    Faker faker = new Faker();
    static int id;


    @BeforeAll
    static void beforeAll() throws IOException
    {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
    }


    @Test
    @DisplayName("Тест создания продукта")
    void createProductInFoodCategoryTest() throws IOException
    {
        // Формируем объект с дланными для теста создания продукта
        product = new GetProductResponse()
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice(777);

        Response<GetProductResponse> response = productService.createProduct(product).execute();
        // Сохраняем идентификатор созданного продукта для его удаления по завершению тестов
        id = response.body().getId();

        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }


    @Test
    @DisplayName("Тест получения продукта по его идентификатору")
    void getProductById() throws IOException
    {
        int productIdToBeRead = ConfigUtils.getProductIdToBeRead();
        Response<GetProductResponse> response = productService.getProductById(productIdToBeRead).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }


    @Test
    @DisplayName("Тест получения всех продуктв")
    void getProducts() throws IOException
    {
        Response<ResponseBody> response = productService.getProducts().execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }


    @Test
    @DisplayName("Удаление продукта по его идентификатору")
    void deleteProductById() throws IOException
    {
        int productIdToBeDeleted = ConfigUtils.getProductIdToBeDeleted();
        Response<ResponseBody> response = productService.deleteProduct(productIdToBeDeleted).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }


    @Test
    @DisplayName("Тест обновления данных продукта")
    void updateProductData() throws IOException
    {
        int productIdToBeModify = ConfigUtils.getProductIdToBeModify();
        GetProductResponse productToBeModify = new GetProductResponse()
                .withId(productIdToBeModify)
                .withTitle("Apple Watch M3")
                .withCategoryTitle("Electronic")
                .withPrice(10000);

        Response<GetProductResponse> response = productService.modifyProduct(productToBeModify).execute();

        // TODO Добавить проверку обновляемых полей
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }


    @SneakyThrows
    @AfterAll
    static void tearDown()
    {
        // Удаляем созданный в ходе тестирования продукт
        Response<ResponseBody> response = productService.deleteProduct(id).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
