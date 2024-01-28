package org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5;

import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.CoreMatchers;
import okhttp3.ResponseBody;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.api.ProductService;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.utils.ConfigUtils;
import org.githab.Serge018.GeekBrainsCoursTestsApi.lesson5.utils.RetrofitUtils;
import org.junit.jupiter.api.*;
import retrofit2.Response;
import java.io.IOException;


public class DeleteProductTest
{
    static ProductService productService;
    static int productIdToBeDeleted;


    @BeforeAll
    static void beforeAll() throws IOException
    {
        productService = RetrofitUtils.getRetrofit().create(ProductService.class);
        productIdToBeDeleted = ConfigUtils.getProductIdToBeDeleted();
    }


    @Test
    @DisplayName("Удаление продукта по его идентификатору")
    void deleteProductById() throws IOException
    {
        Response<ResponseBody> response = productService.deleteProduct(productIdToBeDeleted).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
    }
}
